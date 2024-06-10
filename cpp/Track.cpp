#include "Track.h"
#include <fstream>
#include <iostream>

Track::Track(const json &jTrack)
{
    trackId = jTrack["trackId"];
    trackName = jTrack["trackName"];

    // Initialize data here
    data = dataStruct();

    getTrackData(trackPath + trackName);
}

void Track::writeCSV() const {
    const std::string filename = "output.csv";
    std::ofstream file(filename);
    if (!file.is_open()) {
        std::cerr << "Could not open the file: " << filename << std::endl;
        return;
    }

    // Write data to the CSV file
    for (size_t i = 0; i < data.distance.size(); ++i) {
        file << data.yCoord.at(i);
        file << ",";
        file << data.xCoord.at(i);
        file << ",";
        file << data.distance.at(i);
        file << "\n"; // Add newline after each row
    }

    file.close();
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

    int k = 0;
    double distance, xCoord, yCoord;
    while (std::getline(file, line)) {
        std::stringstream line_stream(line);
        std::string cell;
        std::vector<double> row;
        k = 0;
        while (std::getline(line_stream, cell, ',') && (k<= 2)) {
            if (k==0) {
                distance = std::stod(cell);
            } else if (k==1) {
                xCoord = std::stod(cell);
            } else if (k==2) {
                yCoord = std::stod(cell);
            };
            k++;
        }

        data.distance.push_back(distance);
        data.xCoord.push_back(xCoord);
        data.yCoord.push_back(yCoord);
    }

    file.close();

}
