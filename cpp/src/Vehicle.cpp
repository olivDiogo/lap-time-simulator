#include "Vehicle.h"
#include <float.h>
#include <Eigen/src/Core/functors/TernaryFunctors.h>

constexpr double RPM2RADPS {0.10472};

Vehicle::Vehicle(const nlohmann::json &jsonVeh)
    :m_vehicleId(jsonVeh["vehicleId"]),
     m_vehicleName(jsonVeh["vehicleName"]),
     m_rBrkF2P(jsonVeh["rBrkF2P"]),
     m_mCar(jsonVeh["mCar"]),
     m_sCz(jsonVeh["sCz"]),
     m_sCx(jsonVeh["sCx"]),
     m_type(jsonVeh["type"]),
     m_PEngMax(jsonVeh["PEngMax"]),
     m_MEngMax(jsonVeh["MEngMax"]),

     //gears(jsonVeh["gears"].get<Eigen::ArrayXd>),
     m_engCoeffs(3),
     m_finalDriveRatio(jsonVeh["finalDriveRatio"]),
     m_mux0(jsonVeh["mux0"]),
     m_muy0(jsonVeh["muy0"]),
     m_rrTyre(jsonVeh["rrTyre"])
{
    if(m_type == "combustion") {
        m_nEngPMax = jsonVeh["nEngPMax"];
        m_nEngMMax = jsonVeh["nEngMMax"];

        m_gears = Eigen::Map<Eigen::ArrayXd>(jsonVeh["gears"].get<std::vector<double>>().data(), jsonVeh["gears"].get<std::vector<double>>().size());

        //const double MEngPMax = m_PEngMax / (m_nEngPMax * RPM2RADPS);

        // New Formulation
        m_engCoeffs[0] = 0;
        m_engCoeffs[1] = 2 * m_MEngMax/(m_nEngMMax * RPM2RADPS);
        m_engCoeffs[2] = -m_MEngMax/(m_nEngMMax*RPM2RADPS * m_nEngMMax*RPM2RADPS);
    }
    else if (m_type == "electric") {
        //m_gears = {1,1,1,1};
    }
}

void Vehicle::getAvailableGrip(const double & mux_used, const double & muy_used, double & mux_av, double & muy_av) const {

    std::cout << std::fixed;
    std::cout << std::setprecision(3);
    if(mux_used > (m_mux0 + 0.0001)) {
        std::cout << "mux_used (" << mux_used << ") cannot be greater than mux0 (" << m_mux0 << ")" << std::endl;
        return;
    }
    if (muy_used > (m_muy0 + 0.0001)) {
        std::cout << "muy_used (" << muy_used << ") cannot be greater than muy0 (" << m_muy0 << ")" << std::endl;
        return;
    }

    std::complex<double> res = std::pow(1 - (muy_used * muy_used) / (m_muy0 * m_muy0), 0.5) * m_mux0;
    mux_av = res.real();

    res = std::pow(1 - (mux_used * mux_used) / (m_mux0 * m_mux0), 0.5) * m_muy0;
    muy_av = res.real();
}

void Vehicle::getEnginePoint(const double &vCar, int &gear, double &FxEngineMax) const {
    if (m_type == "combustion"){

        Eigen::ArrayXd nEngineVec = vCar / m_rrTyre / m_finalDriveRatio / m_gears; //calculate back engine speed for all gears
        Eigen::ArrayXd MEngineVec = m_engCoeffs[0] + nEngineVec * m_engCoeffs[1] + nEngineVec * nEngineVec * m_engCoeffs[2];
        Eigen::ArrayXd FEngineVec = MEngineVec / m_gears / m_finalDriveRatio / m_rrTyre;
        FxEngineMax = FEngineVec.maxCoeff(&gear);

    } else if (m_type == "electric") {

        gear = 1;
        double nEngine = vCar / m_rrTyre / m_finalDriveRatio;
        double MEngine = std::min(m_MEngMax, m_PEngMax/nEngine);
        FxEngineMax = MEngine / m_finalDriveRatio / m_rrTyre;
    }

}

double Vehicle::calcVMax(const double &rhoAir) const {

    return std::pow(2 * m_PEngMax / (rhoAir * - m_sCx), static_cast<double>(1)/3);
}


