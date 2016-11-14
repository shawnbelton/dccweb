//
// Created by shawn on 01/06/16.
//

#include "blockController.h"
#include <math.h>
#include <Arduino.h>
#include "Notifier.h"

blockController::blockController(int pBlockNumber, uint8_t pBlockInput) {
    blockNumber = pBlockNumber;
    blockInput = pBlockInput;
    occupied = true;
    blockChanged = false;
    aqv = -1;
    aqc = -1;
    last_occupied = false;
}

bool blockController::isOccupied() {
    return occupied;
}

void blockController::init() {
    setSignal();
}

void blockController::setNotifier(Notifier& pNotifier) {
    notifier = &pNotifier;
}

void blockController::checkBlock() {
    if (aqv < 0) {
        aqv = determineQV();
        printAQV();
    }  else if (aqc < 0) {
        aqc =  determineCQ(aqv);
        printAQC();
    } else {
        determineOccupied(aqv, aqc * DETECTION_MULTIPLIER);
    }
}

void blockController::printAQC() {
    char buffer[100];
    sprintf(buffer, "Block %i AQC ", blockNumber);
    Serial.print(buffer);
    Serial.println(aqc);
}

void blockController::printAQV() {
    char buffer[100];
    sprintf(buffer, "Block %i AQV %i", blockNumber, aqv);
    Serial.println(buffer);
}

void blockController::determineOccupied(float adc_zero, float threshold) {
    float current = readCurrent(adc_zero);
    bool block_occupied = current > threshold;
    if (occupied != block_occupied) {
        if (blockChanged) {
            occupied = block_occupied;
            blockChanged = false;
        } else {
            blockChanged = true;
        }
    } else {
        blockChanged = false;
    }
    setSignal();
}

float blockController::determineCQ(float aqv) {
    float CQ = 0;
    int reps = (CALIBRATION_READS / numSamples);
    for (int i = 0; i < reps; i++) {
        CQ += readCurrent(aqv);
    }
    CQ /= reps;

    return CQ;
}

int blockController::determineQV() {
    float VQ = 0;
    for (int i = 0; i < CALIBRATION_READS; i++) {
        VQ += analogRead(blockInput);
        delayMicroseconds(100);
    }
    VQ /= CALIBRATION_READS;
    return int(VQ);
}

float blockController::readCurrent(float adc_zero)
{
    float currentAcc = 0;
    unsigned int count = 0;
    unsigned long prevMicros = micros() - sampleInterval;
    while (count < numSamples)
    {
        if (micros() - prevMicros >= sampleInterval)
        {
            float adc_raw = (float) analogRead(blockInput) - adc_zero;
            // convert to amperes
            adc_raw /= SENSITIVITY;
            // accumulate the sum of the squares of the readings
            currentAcc += (adc_raw * adc_raw);
            ++count;
            prevMicros += sampleInterval;
        }
    }
    //https://en.wikipedia.org/wiki/Root_mean_square
    // square root of the sum of the squares / number of samples
    return sqrt(currentAcc / (float)numSamples);
}

void blockController::setSignal() {
    if (occupied != last_occupied) {
        notifier->sendWebNotification(blockNumber, occupied);
        last_occupied = occupied;
    }
}

