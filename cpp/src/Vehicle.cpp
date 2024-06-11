
#include "Vehicle.h"

Vehicle::Vehicle(const json &jsonVeh)
{
    vehicleId = jsonVeh["vehicleId"];
    vehicleName = jsonVeh["vehicleName"];
    sCz = jsonVeh["sCz"];
    sCx = jsonVeh["sCx"];
    rBrkF2P = jsonVeh["rBrkF2P"];
    mCar = jsonVeh["mCar"];
    PEngMax = jsonVeh["PEngMax"];
    MEngMax = jsonVeh["MEngMax"];
    nEngPMax = jsonVeh["nEngPMax"];
    nEngMMax = jsonVeh["nEngMMax"];
    gears = jsonVeh["gears"].get<std::vector<double>>();
    mux = jsonVeh["mux"];
    muy = jsonVeh["muy"];
    rrTyre = jsonVeh["rrTyre"];
};
