cmake_minimum_required(VERSION 3.2)
set(CMAKE_TOOLCHAIN_FILE /home/shawn/development/arduino-cmake/cmake/ArduinoToolchain.cmake)
set(ARDUINO_DEFAULT_BOARD nano328)
set(ARDUINO_DEFAULT_PORT /dev/ttyUSB0)
set(PROJECT_NAME blockcontroller)
set(BOARD nano)
project(${PROJECT_NAME})
set(${CMAKE_PROJECT_NAME}-nano_PORT /dev/ttyUSB0)
set(${CMAKE_PROJECT_NAME}-nano_SERIAL minicom -D @SERIAL_PORT@ -b 9600)
set(${CMAKE_PROJECT_NAME}-uno_PORT /dev/ttyACM0)
set(${CMAKE_PROJECT_NAME}-uno_SERIAL minicom -D @SERIAL_PORT@ -b 9600)

set(${CMAKE_PROJECT_NAME}-nano_SKETCH blockcontroller.ino)
set(${CMAKE_PROJECT_NAME}-uno_SKETCH blockcontroller.ino)
#generate_arduino_firmware(${CMAKE_PROJECT_NAME}-nano BOARD nano328 PORT /dev/ttyUSB0)
generate_arduino_firmware(${CMAKE_PROJECT_NAME}-uno BOARD uno PORT /dev/ttyACM0)

if(NOT DEFINED CMAKE_SUPPRESS_DEVELOPER_WARNINGS)
    set(CMAKE_SUPPRESS_DEVELOPER_WARNINGS 1 CACHE INTERNAL "No dev warnings")
endif()