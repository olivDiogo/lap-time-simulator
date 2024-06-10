#ifndef VEHICLE_H
#define VEHICLE_H

#include "nlohmann/json.hpp"

using json = nlohmann::json;

class Vehicle {
public:
    std::string vehicleId;
    std::string vehicleName;
    double sCz, sCx;                             //Aerodynamic Parameters
    double rBrkF2P;                              //Brake Force to Pressure Ratio
    double mCar;                                 // Car Mass
    double PEngMax, MEngMax, nEngPMax, nEngMMax; //Engine Parameters
    std::vector<double> gears;
    double mux, muy;                             // Tyre Grip Parameters
    double rrTyre;                               // Tyre Rolling Radius;

public:
    explicit Vehicle(const json &jsonVeh);
};

#endif //VEHICLE_H
