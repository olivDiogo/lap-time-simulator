#ifndef TRACK_H
#define TRACK_H

#include "nlohmann/json.hpp"
#include <vector>
#include <Eigen/Dense>


class Track {
public:
    std::string trackId;
    std::string trackName;
    std::string trackPath;

    // Declaration of dataStruct
    struct dataStruct {
        Eigen::ArrayXd distance;
        Eigen::ArrayXd xCoord;
        Eigen::ArrayXd yCoord;
        Eigen::ArrayXd curvature;
    };

    dataStruct data;

public:
    explicit Track(const nlohmann::json &jTrack);

    void calcCurvature() ;

private:
    void getTrackData(const std::string &trackPath);


};

//Auxiliary functions
Eigen::ArrayXd diff(const Eigen::ArrayXd &vec);

Eigen::ArrayXd movingAvg(const Eigen::ArrayXd &vec, const int& window);

#endif //TRACK_H
