//
// Created by shawn on 01/06/16.
//

#ifndef BLOCK_CONTROLLER_BLOCK_CONTROLLER_H
#define BLOCK_CONTROLLER_BLOCK_CONTROLLER_H

#include <stdint.h>
#include <stdint-gcc.h>
#include "Notifier.h"

#define SENSITIVITY 185
#define CALIBRATION_READS 5000

const unsigned long sampleTime = 58000UL; // sample over 100ms
const unsigned long numSamples = 200UL; // the number of samples divides sampleTime exactly,
// but low enough for the ADC to keep up
const unsigned long sampleInterval = sampleTime/numSamples;  // the sampling interval
//  must be longer than then ADC conversion time

const float DETECTION_MULTIPLIER = 1.5;
const char ESC = 27;

static const int PRECISION = 10;


class blockController {


private:
    int blockNumber;
    uint8_t blockInput;
    bool occupied;
    bool last_occupied;
    bool blockChanged;
    int aqv;
    float aqc;
    Notifier* notifier;
    int numReads;
    float runningTotal;
    enum State { DETERMINE_NORMAL, DETERMINE_NOISE, DETERMINE_OCCUPIED } state;

    void setSignal();
    float readCurrent();
    void determineZero();
    void determineNoise();
    void determineOccupied(float threshold);
    void printZeroValue();
    void printNoiseValue();
    void printCurrent(float current);
    void printFloat(float fl);
public:
    blockController(int pBlockNumber, uint8_t pBlockInput);
    void init();
    void setNotifier(Notifier& pNotifier);
    void checkBlock();
};


#endif //BLOCK_CONTROLLER_BLOCK_CONTROLLER_H
