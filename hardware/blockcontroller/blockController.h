//
// Created by shawn on 01/06/16.
//

#ifndef BLOCKCONTROLLER_BLOCKCONTROLLER_H
#define BLOCKCONTROLLER_BLOCKCONTROLLER_H

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

const float DETECTION_MULTIPLIER = 1.1;
const char ESC = 27;

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

    void setSignal();
    float readCurrent(float adc_zero);
    int determineQV();
    float determineCQ(float aqv);
    void determineOccupied(float adc_zero, float threshold);
    void printAQV();
    void printAQC();
public:
    blockController(int pBlockNumber, uint8_t pBlockInput);
    void init();
    void setNotifier(Notifier& pNotifier);
    bool isOccupied();
    void checkBlock();
};


#endif //BLOCKCONTROLLER_BLOCKCONTROLLER_H
