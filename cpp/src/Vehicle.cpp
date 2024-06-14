#include "Vehicle.h"
#include <float.h>
#include <Eigen/src/Core/functors/TernaryFunctors.h>

constexpr double RPM2RADPS {0.10472};

Vehicle::Vehicle(const json &jsonVeh)
    :vehicleId(jsonVeh["vehicleId"]),
     vehicleName(jsonVeh["vehicleName"]),
     sCz(jsonVeh["sCz"]),
     sCx(jsonVeh["sCx"]),
     rBrkF2P(jsonVeh["rBrkF2P"]),
     mCar(jsonVeh["mCar"]),
     PEngMax(jsonVeh["PEngMax"]),
     MEngMax(jsonVeh["MEngMax"]),
     nEngPMax(jsonVeh["nEngPMax"]),
     nEngMMax(jsonVeh["nEngMMax"]),
     gears(Eigen::Map<Eigen::ArrayXd>(jsonVeh["gears"].get<std::vector<double>>().data(), jsonVeh["gears"].get<std::vector<double>>().size())),
     //gears(jsonVeh["gears"].get<Eigen::ArrayXd>),
     engCoeffs(3),
     finalDriveRatio(jsonVeh["finalDriveRatio"]),
     mux0(jsonVeh["mux0"]),
     muy0(jsonVeh["muy0"]),
     rrTyre(jsonVeh["rrTyre"])
{
    //2nd order polynomial
    const double MEngPMax = PEngMax / (nEngPMax * RPM2RADPS);

    // Old Formulation
    /*engCoeffs[0] = 0;
    engCoeffs[1] = (MEngPMax * nEngMMax*nEngMMax - MEngMax * nEngPMax*nEngPMax) / (nEngMMax * nEngPMax * (nEngMMax - nEngPMax));
    engCoeffs[2] = - (nEngMMax * MEngPMax - nEngPMax*MEngMax) / (nEngMMax * nEngPMax * (nEngMMax - nEngPMax));*/

    // New Formulation
    engCoeffs[0] = 0;
    engCoeffs[1] = 2 * MEngMax/(nEngMMax * RPM2RADPS);
    engCoeffs[2] = -MEngMax/(nEngMMax*RPM2RADPS * nEngMMax*RPM2RADPS);
}

void Vehicle::getAvailableGrip(const double & mux_used, const double & muy_used, double & mux_av, double & muy_av) const {

    std::cout << std::fixed;
    std::cout << std::setprecision(3);
    if(mux_used > (mux0 + 0.0001)) {
        std::cout << "mux_used (" << mux_used << ") cannot be greater than mux0 (" << mux0 << ")" << std::endl;
        return;
    }
    if (muy_used > (muy0 + 0.0001)) {
        std::cout << "muy_used (" << muy_used << ") cannot be greater than muy0 (" << muy0 << ")" << std::endl;
        return;
    }

    std::complex<double> res;

    res = std::pow(1 - (muy_used * muy_used) / (muy0 * muy0), 0.5) * mux0;
    mux_av = res.real();

    res = std::pow(1 - (mux_used * mux_used) / (mux0 * mux0), 0.5) * muy0;
    muy_av = res.real();
}

void Vehicle::getEnginePoint(const double &vCar, int &gear, double &FxEngineMax) const {

    Eigen::ArrayXd engSpeeds = vCar / rrTyre / finalDriveRatio / gears; //calculate back engine speed for all gears

    Eigen::ArrayXd engTorques = engCoeffs[0] + engSpeeds * engCoeffs[1] + engSpeeds * engSpeeds * engCoeffs[2];

    Eigen::ArrayXd engForces = engTorques / gears / finalDriveRatio / rrTyre;

    FxEngineMax = engForces.maxCoeff(&gear);
}


