cmake_minimum_required(VERSION 3.28)
project(untitled11)

set(CMAKE_CXX_STANDARD 17)

add_executable(untitled11 main.cpp
        Vehicle.cpp
        Vehicle.h
        Track.cpp
        Track.h)

find_package(Eigen3 REQUIRED)
include_directories(${Eigen3_INCLUDE_DIR})
target_link_libraries(untitled11 PRIVATE ${Eigen3_LIBRARY})

find_package(nlohmann_json REQUIRED)
include_directories(${nlohmann_json_INCLUDE_DIR})
target_link_libraries(untitled11 PRIVATE Eigen3::Eigen nlohmann_json::nlohmann_json)

#[[find_package(Gnuplot REQUIRED)
include_directories(${Gnuplot_INCLUDE_DIR})
target_link_libraries(untitled11 PRIVATE Gnuplot::Gnuplot)]]

find_package(Matplot++ CONFIG REQUIRED)
include_directories(${Matplot++_INCLUDE_DIR})
target_link_libraries(untitled11 PRIVATE Matplot++::cimg  Matplot++::matplot)

project(MyProject)

