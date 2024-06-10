#ifndef TRACK_H
#define TRACK_H

#include "Eigen/Dense"
#include "nlohmann/json.hpp"
#include <vector>

using json = nlohmann::json;

class Track {
public:
    std::string trackId;
    std::string trackName;
    std::string trackPath = "C:/Users/gonca/CLionProjects/TrackData/";

    // Declaration of dataStruct
    struct dataStruct {
        std::vector<double> distance;
        std::vector<double> xCoord;
        std::vector<double> yCoord;
    };

    dataStruct data;

public:
    explicit Track(const json &jTrack);

    void writeCSV() const;

private:
    void getTrackData(const std::string &trackPath);

};

#endif //TRACK_H
