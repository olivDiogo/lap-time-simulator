cmake_minimum_required(VERSION 3.28)
project(cpp)

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_TOOLCHAIN_FILE "D:/Programas/vcpkg/vcpkg/scripts/buildsystems/vcpkg.cmake")

find_package(nlohmann_json CONFIG REQUIRED)

include_directories(${nlohmann_json_INCLUDE_DIR})

add_executable(cpp
        src/main.cpp
        src/Vehicle.cpp
        src/Track.cpp
)

target_link_libraries(cpp PRIVATE nlohmann_json::nlohmann_json)
