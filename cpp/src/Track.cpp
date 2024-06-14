#include "Track.h"
#include <fstream>
#include <iostream>
#include <cmath>
#include <numeric>

Track::Track(const nlohmann::json &jTrack)
    :trackId(jTrack["trackId"]),
    trackName(jTrack["trackName"]),
    trackPath("resources/tracks/")
{
    getTrackData(trackPath + trackName);
    calcCurvature();
    //data.curvature = Eigen::ArrayXd::Ones(200);
    data.curvature = movingAvg(data.curvature, 25);
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
    //Resize Eigen Vectors
    //data.distance.resize(distance.size());
    //data.xCoord.resize(distance.size());
    //data.yCoord.resize(distance.size());
    //data.curvature.resize(distance.size());

    //Assign
    data.distance = Eigen::Map<Eigen::ArrayXd>(distance.data(), distance.size());
    data.xCoord = Eigen::Map<Eigen::ArrayXd>(xCoord.data(), xCoord.size());
    data.yCoord = Eigen::Map<Eigen::ArrayXd>(yCoord.data(), yCoord.size());

    file.close();
}

void Track::calcCurvature() {

    const Eigen::ArrayXd dx = diff(data.xCoord);
    const Eigen::ArrayXd ddx = diff(dx);
    const Eigen::ArrayXd dy = diff(data.yCoord);
    const Eigen::ArrayXd ddy = diff(dy);

    const Eigen::ArrayXd num = (dx * ddy - dy * ddx).abs();
    const Eigen::ArrayXd den = (dx * dx + dy * dy).pow(1.5);

    data.curvature = (den.array() == 0).select(0, num/den);

    //std::vector<double> curvature(data.distance.size());

    /*const Eigen::ArrayXd dx = diff(data.xCoord);
    const Eigen::ArrayXd ddx = diff(dx);
    const Eigen::ArrayXd dy = diff(data.yCoord);
    const Eigen::ArrayXd ddy = diff(dy);

    const Eigen::ArrayXd num2 = (dx * ddy - dy * ddx).abs();

    for(size_t i=0; i<dx.size(); ++i){
        double num = std::abs(dx[i] * ddy[i] - dy[i] * ddx[i]);
        double den = std::pow(dx[i] * dx[i] + dy[i] * dy[i], 1.5);
        curvature[i] = den == 0 ? 0 : num/den;
    }

    data.curvature = Eigen::Map<Eigen::ArrayXd>(curvature.data(), curvature.size());*/

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

Eigen::ArrayXd movingAvg(const Eigen::ArrayXd &vec, const int& window) {
    Eigen::VectorXd vecFilt(vec.size());
    const int halfWindow = window / 2;

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


