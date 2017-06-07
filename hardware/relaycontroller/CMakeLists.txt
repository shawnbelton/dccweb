cmake_minimum_required(VERSION 3.5)
set(CMAKE_TOOLCHAIN_FILE /home/shawn/development/arduino-cmake/cmake/ArduinoToolchain.cmake)
set(ARDUINO_DEFAULT_BOARD nano328)
set(ARDUINO_DEFAULT_PORT /dev/ttyUSB0)
set(PROJECT_NAME RelayController)
project(${PROJECT_NAME})
link_directories("${PROJECT_SOURCE_DIR}/../modules")
execute_process(COMMAND echo -ne "Relay Controller" OUTPUT_VARIABLE APP_NAME)
message(${APP_NAME})
add_definitions(-DAPP_NAME="${APP_NAME}")
add_definitions(-DARDUINO_ARCH_AVR=ARDUINO_ARCH_AVR)

generate_arduino_firmware(${CMAKE_PROJECT_NAME}-nano
        SKETCH RelayController
        PROGRAMMER usbtinyisp
        BOARD nano328
        PORT /dev/ttyUSB0
        SERIAL minicom -D @SERIAL_PORT@ -b 9600
        )

generate_arduino_firmware(${CMAKE_PROJECT_NAME}-uno
        SKETCH RelayController
        PROGRAMMER usbtinyisp
        BOARD uno
        PORT /dev/ttyACM0
        SERIAL minicom -D @SERIAL_PORT@ -b 9600
        )

if(NOT DEFINED CMAKE_SUPPRESS_DEVELOPER_WARNINGS)
    set(CMAKE_SUPPRESS_DEVELOPER_WARNINGS 1 CACHE INTERNAL "No dev warnings")
endif()
