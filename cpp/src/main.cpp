#include <iostream>
#include <fstream>
#include "nlohmann/json.hpp"
#include <Eigen/Dense>
#include <vector>
#include <string>
#include <cmath>
#include <memory>
#include <complex>
#include <chrono>

#include "Vehicle.h"
#include "Track.h"


using json = nlohmann::json;

Eigen::MatrixXd read_csv(const std::string& filename);

Eigen::ArrayXd calcVLim(const Vehicle*,Track*);

void writeCSV(const Eigen::ArrayXd &vec);

double calcVMax(const Vehicle*);

Eigen::ArrayXd solveForward(const Vehicle* veh,Track* trk, Eigen::ArrayXd &vLim);

int main(int argc, char* argv[]) {

    auto tStart = std::chrono::high_resolution_clock::now();

    // Check if the file name is provided
    if (argc < 2) {
        std::cerr << "Usage: " << argv[0] << " <file.json>" << std::endl;
        return 1;
    }

    // Open the JSON file
    std::ifstream file(argv[1]);
    if (!file.is_open()) {
        std::cerr << "Could not open the file: " << argv[1] << std::endl;
        return 1;
    }

    // Split json info
    json j = json::parse(file);
    json jVeh = j["vehicle"];
    json jTrk = j["track"];
    json jSim = j["sim"];

    /*// Loop over the fields in the JSON object
    std::cout << "Vehicle Parameters: " << std::endl;
    for (json::iterator it = jVeh.begin(); it != jVeh.end(); ++it) {
        std::cout << it.key() << " : " << it.value() << std::endl;
    }*/

    auto veh = new Vehicle(jVeh);
    //std::cout << veh->gears.at(0) << std::endl;

    auto trk = new Track(jTrk);
    //std::cout << trk->data.xCoord[0] << std::endl;

    std::cout << "\n--- Starting Simulation ---" << std::endl;
    std::cout << "Simulation ID: " << j["sim"]["simulationId"] << std::endl;
    std::cout << "Vehicle ID: " << veh->vehicleId << std::endl;
    std::cout << "Track ID: " << trk->trackId << std::endl;

    // VLim calculation
    double vMax = calcVMax(veh);
    std::cout << "\nVehicle Top Speed: " << std::endl;
    std::cout << vMax << " m/s" << std::endl;

    Eigen::ArrayXd vLim = calcVLim(veh, trk);
    Eigen::ArrayXd vForw = solveForward(veh, trk, vLim);

    std::cout << "\nWriting Results ..." << std::endl;
    try {
        writeCSV(vForw);
        std::cout << ".csv file created." << std::endl;
    } catch (...) {
        std::cout << "Couldn't create .csv file" << std::endl;

    }



    delete veh;
    delete trk;

    // Get the ending timepoint
    auto tEnd = std::chrono::high_resolution_clock::now();

    // Calculate the duration
    std::chrono::duration<double> duration = tEnd - tStart;

    // Output the duration
    std::cout << "\nSimulation time: " << duration.count() << " seconds" << std::endl;

    std::cout << "\n--- Simulation completed ---" << std::endl;

    return 0;
}

Eigen::MatrixXd read_csv(const std::string& filename) {
    std::ifstream file(filename);
    if (!file.is_open()) {
        std::cerr << "Could not open the file: " << filename << std::endl;
        return Eigen::MatrixXd();
    }

    std::string line;

    // Ignore the first two lines
    if (!std::getline(file, line) || !std::getline(file, line)) {
        std::cerr << "File has less than two lines to skip: " << filename << std::endl;
        return Eigen::MatrixXd();
    }

    std::vector<std::vector<double>> data;

    while (std::getline(file, line)) {
        std::stringstream line_stream(line);
        std::string cell;
        std::vector<double> row;

        while (std::getline(line_stream, cell, ',')) {
            row.push_back(std::stod(cell));
        }
        data.push_back(row);
    }

    file.close();

    if (data.empty()) {
        std::cerr << "No data read from the file: " << filename << std::endl;
        return Eigen::MatrixXd();
    }

    size_t rows = data.size();
    size_t cols = data[0].size();
    Eigen::MatrixXd matrix(rows, cols);

    for (size_t i = 0; i < rows; ++i) {
        for (size_t j = 0; j < cols; ++j) {
            matrix(i, j) = data[i][j];
        }
    }

    return matrix;
}

void writeCSV(const Eigen::ArrayXd &vec) {
    const std::string filename = "output.csv";
    std::ofstream file(filename);
    if (!file.is_open()) {
        std::cout << "Could not open the file: " << filename << std::endl;
        return;
    }

    // Write data to the CSV file
    for (const double val : vec) {
        file << val << "\n";
    }

    file.close();
}

double calcVMax(const Vehicle* veh) {
    const double rhoAir = 1.3;

    return std::pow(2 * veh->PEngMax / (rhoAir * - veh->sCx), static_cast<double>(1)/3);
}

Eigen::ArrayXd calcVLim(const Vehicle* veh,Track* trk){

    const double rhoAir = 1.3;
    Eigen::ArrayXd vLim(trk->data.curvature.size());
    const double g = 9.81;

    //Calculate Top Speed from power balance
    const double vMax = calcVMax(veh);
    int k = 0;
    for(const double c : trk->data.curvature) {

        const double num = -veh->muy0 * veh->mCar * g;
        const double den = 0.5 * veh->muy0 * veh->sCz * rhoAir - veh->mCar * c;

        auto base = std::complex<double>(num/den, 0.0);

        std::complex<double> result = std::pow(base, 0.5);

        if (result.imag() == 0.0 && result.real() < vMax)
            vLim[k] = result.real();
        else
            vLim[k] = vMax;
        ++k;
    }

    return vLim;
}

Eigen::ArrayXd solveForward(const Vehicle* veh,Track* trk, Eigen::ArrayXd &vLim) {

    int minIdx;
    double vCurr = vLim.minCoeff(&minIdx);
    const double g {9.81}, rhoAir{1.3};

    Eigen::ArrayXd vForw(vLim.size());
    vForw(minIdx) = vCurr;

    double muy_av {0}, mux_av {0};
    double Fz0 = veh->mCar * g;
    double ds {0};

    for(size_t i=0; i<vLim.size(); ++i) {
        // Calculate current and next index
        unsigned short k = (minIdx + i) % (vLim.size() - 1);
        unsigned short kp1 = (minIdx + i + 1) % (vLim.size() - 1);
        // Aerodynamic load
        double FzAero = 0.5 * veh->sCz * rhoAir * vForw[k] * vForw[k];

        // Total Vertical Load
        double Fz = FzAero + Fz0;

        // Lateral Acceleration at Current Point
        double Ay = vForw[k] * vForw[k] * trk->data.curvature[k];

        //Lateral Force
        double Fy = Ay * veh->mCar;

        //Used muy
        double muy_req = Fy / Fz;

        veh->getAvailableGrip(0,muy_req,mux_av,muy_av);

        // Use available longitudinal grip to accelerate
        double Fx = mux_av * Fz;
        double Ax = Fx / veh->mCar;

        // Calculate speed at the next point
        if (trk->data.distance(kp1) - trk->data.distance(k) > 0)
            ds = trk->data.distance(kp1) - trk->data.distance(k);
        else
            ds = trk->data.distance(kp1);

        double vNext = std::pow(2 * Ax * ds + vCurr*vCurr, 0.5);

        vForw[kp1] = vNext < vLim[kp1] ? vNext : vLim[kp1];
    }

    return vForw;



















    }


