#include "Track.h"
#include <fstream>
#include <iostream>
#include <numeric>

Track::Track(const nlohmann::json &jTrack)
    :m_trackId(jTrack["trackId"]),
     m_trackName(jTrack["trackName"]),
     m_trackPath("resources/tracks/")
{
    getTrackData(m_trackPath + m_trackName);
    calcCurvature();
    //data.curvature = Eigen::ArrayXd::Ones(200);

    m_data.curvature = movingAvg(m_data.curvature, 2, 0.0025);
}

void Track::getTrackData(const std::string &trackPath) {

    std::ifstream file(trackPath);
    if (!file.is_open()) {
        std::cerr << "Could not open the file: " << trackPath << std::endl;
        return;
    }

    std::string line;
    // Ignore the first two lines
    if (!std::getline(file, line) || !std::getline(file, line)) {
        std::cerr << "File has less than two lines to skip: " << trackPath << std::endl;
        return;
    }

    std::vector<double> distance, xCoord, yCoord;
    int j = 0;

    while (std::getline(file, line)) {
        std::stringstream line_stream(line);
        std::string cell;
        std::vector<double> row;
        int k = 0;
        while (std::getline(line_stream, cell, ',') && (k<= 2)) {
            switch (k){
                case 0: distance.push_back(std::stod(cell)); break;
                case 1: xCoord.push_back(std::stod(cell)); break;
                case 2: yCoord.push_back(std::stod(cell)); break;
            };
            ++k;
        }

    }

    //Assign
    m_data.distance = Eigen::Map<Eigen::ArrayXd>(distance.data(), distance.size());
    m_data.xCoord = Eigen::Map<Eigen::ArrayXd>(xCoord.data(), xCoord.size());
    m_data.yCoord = Eigen::Map<Eigen::ArrayXd>(yCoord.data(), yCoord.size());

    file.close();
}

void Track::calcCurvature() {

    const Eigen::ArrayXd dx = diff(m_data.xCoord);
    const Eigen::ArrayXd ddx = diff(dx);
    const Eigen::ArrayXd dy = diff(m_data.yCoord);
    const Eigen::ArrayXd ddy = diff(dy);

    const Eigen::ArrayXd num = (dx * ddy - dy * ddx).abs();
    const Eigen::ArrayXd den = (dx * dx + dy * dy).pow(1.5);

    m_data.curvature = (den.array() == 0).select(0, num/den);
}

Eigen::ArrayXd diff(const Eigen::ArrayXd &vec) {

    Eigen::ArrayXd diffVec(vec.size());

    for(size_t i = 1; i < vec.size()-1; ++i) {
        diffVec[i] = (vec[i+1] - vec[i-1])/2;
    }
    diffVec[0] = (vec[1] - vec[vec.size() - 1]) / 2;
    diffVec[vec.size()-1] = (vec[0]- vec[vec.size()-2]) / 2;

    return diffVec;
}

Eigen::ArrayXd movingAvg(Eigen::ArrayXd &vec, const int& window, const double& curvStraightThresh) {
    Eigen::VectorXd vecFilt(vec.size());
    const int halfWindow = window / 2;

    /*for(auto &i : vec) {
        if (i < curvStraightThresh)
            i = 0.0f;
    }*/

    int start, end;
    double sum;
    for (size_t i = 0; i < vec.size(); ++i) {

        //Starting element
        if(static_cast<int>(i) - halfWindow < 0)
            start = vec.size() + (static_cast<int>(i) - halfWindow);
        else
            start = static_cast<int>(i) - halfWindow;

        if((static_cast<int>(i) + halfWindow) > (static_cast<int>(vec.size()) - 1))
            end = (static_cast<int>(i) + halfWindow) - static_cast<int>(vec.size());
        else
            end = static_cast<int>(i) + halfWindow;

        if(start>end) {
            sum = std::accumulate(vec.begin() + start, vec.end(), 0.0);
            sum = std::accumulate(vec.begin(), vec.begin() + end + 1, sum);
        } else
            sum = std::accumulate(vec.begin() + start, vec.begin() + end + 1, 0.0);

        vecFilt[i] = sum / (window+1);
    }

    return vecFilt;
}


