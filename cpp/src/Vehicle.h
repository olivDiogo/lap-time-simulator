#ifndef VEHICLE_H
#define VEHICLE_H

#include "nlohmann/json.hpp"
#include <complex>
#include <cmath>
#include <iostream>
#include <Eigen/Dense>

using json = nlohmann::json;

struct engPoint {
    int gear;
    double MEngineMax;
    double MEngineMin;
};

class Vehicle {

public:
    std::string vehicleId;
    std::string vehicleName;
    double sCz, sCx;                             // Aerodynamic Parameters
    double rBrkF2P;                              // Brake Force to Pressure Ratio
    double mCar;                                 // Car Mass
    double PEngMax, MEngMax, nEngPMax, nEngMMax; // Engine Parameters
    Eigen::ArrayXd gears;
    double rrTyre;                               // Tyre Rolling Radius;
    std::vector<double> engCoeffs;
    double finalDriveRatio;
    double mux0, muy0;                           // Tyre Grip Parameters


public:
    explicit Vehicle(const json &jsonVeh);

    void getAvailableGrip(const double&, const double&, double&, double&) const;

    void getEnginePoint(const double& vCar, int &gear, double &FxEngineMax) const;

};

#endif //VEHICLE_H
