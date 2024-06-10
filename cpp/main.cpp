#include <iostream>
#include <fstream>
#include "nlohmann/json.hpp"
#include <Eigen/Dense>
#include <vector>
#include <string>
#include "Vehicle.h"
#include "Track.h"

using json = nlohmann::json;

Eigen::MatrixXd read_csv(const std::string& filename);

void write_vector_to_csv(const std::string& filename, const Eigen::VectorXd& vec);

int main(int argc, char* argv[]) {

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

    // Loop over the fields in the JSON object
    std::cout << "Vehicle Parameters: " << std::endl;
    for (json::iterator it = jVeh.begin(); it != jVeh.end(); ++it) {
        std::cout << it.key() << " : " << it.value() << std::endl;
    }

    auto veh = new Vehicle(jVeh);
    std::cout << veh->gears.at(0) << std::endl;

    auto trk = new Track(jTrk);
    std::cout << trk->data.xCoord.at(0) << std::endl;

    trk->writeCSV();

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

void write_vector_to_csv(const std::string& filename, const Eigen::VectorXd& vec) {
    std::ofstream file(filename);
    if (!file.is_open()) {
        std::cerr << "Could not open the file: " << filename << std::endl;
        return;
    }

    for (int i = 0; i < vec.size(); ++i) {
        file << vec[i];
        if (i < vec.size() - 1) {
            file << ",";
        }
    }
    file << "\n";

    file.close();
}
