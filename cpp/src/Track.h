#ifndef TRACK_H
#define TRACK_H

#include "nlohmann/json.hpp"
#include <vector>
#include <Eigen/Dense>


class Track {
public:
    std::string m_trackId;
    std::string m_trackName;
    std::string m_trackPath;

    // Declaration of dataStruct
    struct dataStruct {
        Eigen::ArrayXd distance;
        Eigen::ArrayXd xCoord;
        Eigen::ArrayXd yCoord;
        Eigen::ArrayXd curvature;
    };

    dataStruct m_data;

public:
    explicit Track(const nlohmann::json &jTrack);

    void calcCurvature() ;

private:
    void getTrackData(const std::string &trackPath);

};

//Auxiliary functions
Eigen::ArrayXd diff(const Eigen::ArrayXd &vec);

Eigen::ArrayXd movingAvg(Eigen::ArrayXd &vec, const int& window, const double& curvStraightThresh);

#endif //TRACK_H
