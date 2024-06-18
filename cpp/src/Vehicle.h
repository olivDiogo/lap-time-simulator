#ifndef VEHICLE_H
#define VEHICLE_H

#include "nlohmann/json.hpp"
#include <complex>
#include <cmath>
#include <iostream>
#include <Eigen/Dense>


struct engPoint {
    int gear;
    double MEngineMax;
    double MEngineMin;
};

class Vehicle {

public:
    std::string m_vehicleId;
    std::string m_vehicleName;

    //General
    double m_rBrkF2P;
    double m_mCar;

    //Aerodynamic
    double m_sCz;
    double m_sCx;

    //Powertrain
    std::string m_type;
    double m_PEngMax;
    double m_MEngMax;
    double m_nEngPMax;
    double m_nEngMMax; // Engine Parameters
    Eigen::ArrayXd m_gears;
    std::vector<double> m_engCoeffs;
    double m_finalDriveRatio;

    //Tyre Parameters
    double m_mux0;
    double m_muy0;
    double m_dmux = 0;
    double m_dmuy = 0;
    double m_rrTyre;

public:
    explicit Vehicle(const nlohmann::json &jsonVeh);

    void getAvailableGrip(const double&, const double&, double&, double&) const;

    void getEnginePoint(const double& vCar, int &gear, double &FxEngineMax) const;

    double calcVMax(const double &rhoAir) const;

};

#endif //VEHICLE_H
