cmake_minimum_required(VERSION 3.5)
set(CMAKE_TOOLCHAIN_FILE /home/shawn/development/arduino-cmake/cmake/ArduinoToolchain.cmake)
set(ARDUINO_DEFAULT_BOARD nano328)
set(ARDUINO_DEFAULT_PORT /dev/ttyUSB0)
set(PROJECT_NAME BlockController)
project(${PROJECT_NAME})
include_directories("${PROJECT_BINARY_DIR}")
execute_process(COMMAND echo -ne "Block Controller" OUTPUT_VARIABLE APP_NAME)
message(${APP_NAME})
add_definitions(-DAPP_NAME="${APP_NAME}")
add_definitions(-DARDUINO_ARCH_AVR=ARDUINO_ARCH_AVR)
#set(${CMAKE_PROJECT_NAME}-nano_PORT /dev/ttyUSB0)
#set(${CMAKE_PROJECT_NAME}-nano_SERIAL minicom -D @SERIAL_PORT@ -b 9600)
#set(${CMAKE_PROJECT_NAME}-uno_PORT /dev/ttyACM0)
#set(${CMAKE_PROJECT_NAME}-uno_SERIAL minicom -D @SERIAL_PORT@ -b 9600)

#set(${CMAKE_PROJECT_NAME}-nano_SKETCH BlockController.ino)
#set(${CMAKE_PROJECT_NAME}-uno_SKETCH BlockController.ino)

#generate_arduino_library(nano328_MACAddress
#        SRCS libraries/MACAddress/MACAddress.cpp
#        HDRS libraries/MACAddress/MACAddress.h
#        BOARD nano328)

#generate_arduino_library(nano328_Notifier
#        SRCS Notifier/Notifier.cpp
#        HDRS Notifier/Notifier.h
#        BOARD nano328)

generate_arduino_firmware(${CMAKE_PROJECT_NAME}-nano
        SKETCH BlockController
        PROGRAMMER usbtinyisp
        BOARD nano328
        PORT /dev/ttyUSB0
        SERIAL minicom -D @SERIAL_PORT@ -b 9600
        )

generate_arduino_firmware(${CMAKE_PROJECT_NAME}-uno
        SKETCH BlockController
        PROGRAMMER usbtinyisp
        BOARD uno
        PORT /dev/ttyACM0
        SERIAL minicom -D @SERIAL_PORT@ -b 9600
        )

#generate_arduino_firmware(${CMAKE_PROJECT_NAME}-nano BOARD nano328 PORT /dev/ttyUSB0)
#generate_arduino_firmware(${CMAKE_PROJECT_NAME}-uno BOARD uno PORT /dev/ttyACM0)

TARGET_COMPILE_DEFINITIONS(uno_Notifier PUBLIC -DWITH_LEDS=1 -DWITH_SD=1)

if(NOT DEFINED CMAKE_SUPPRESS_DEVELOPER_WARNINGS)
    set(CMAKE_SUPPRESS_DEVELOPER_WARNINGS 1 CACHE INTERNAL "No dev warnings")
endif()
