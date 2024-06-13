#include "Vehicle.h"

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
     gears(jsonVeh["gears"].get<std::vector<double>>()),
     mux0(jsonVeh["mux0"]),
     muy0(jsonVeh["muy0"]),
     rrTyre(jsonVeh["rrTyre"])

{
}

void Vehicle::getAvailableGrip(const double & mux_used, const double & muy_used, double & mux_av, double & muy_av) const {

    if(mux_used > mux0) {
        std::cout << "mux_used (" << mux_used << ") cannot be greater than mux0 (" << mux0 << ")" << std::endl;
        return;
    }
    if (muy_used > muy0) {
        std::cout << "mux_used (" << mux_used << ") cannot be greater than mux0 (" << mux0 << ")" << std::endl;
        return;
    }

    std::complex<double> res;

    res = std::pow(1 - (muy_used * muy_used) / (muy0 * muy0), 0.5) * mux0;
    mux_av = res.real();

    res = std::pow(1 - (mux_used * mux_used) / (mux0 * mux0), 0.5) * muy0;
    muy_av = res.real();
}
