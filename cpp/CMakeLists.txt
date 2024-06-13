cmake_minimum_required(VERSION 3.28)
project(cpp)

set(CMAKE_CXX_STANDARD 17)

add_executable(cpp src/main.cpp
        src/Vehicle.cpp
        src/Vehicle.h
        src/Track.cpp
        src/Track.h)

find_package(nlohmann_json CONFIG REQUIRED)
target_link_libraries(cpp PRIVATE nlohmann_json::nlohmann_json)

find_package(Eigen3 CONFIG REQUIRED)
target_link_libraries(cpp PRIVATE Eigen3::Eigen)


