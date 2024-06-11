#include <iostream>
#include <fstream>
#include "nlohmann/json.hpp"
#include <vector>
#include <string>
#include "Vehicle.h"
#include "Track.h"

using json = nlohmann::json;


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
