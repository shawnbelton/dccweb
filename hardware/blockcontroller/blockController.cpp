//
// Created by shawn on 01/06/16.
//

#include "blockController.h"

blockController::blockController(int pBlockNumber, uint8_t pBlockInput) {
    blockNumber = pBlockNumber;
    blockInput = pBlockInput;
    occupied = true;
    blockChanged = false;
    aqv = -1;
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
        aqc = -1.0f;
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
    printFloat(aqc);
    Serial.println("");
}

void blockController::printAQV() {
    char buffer[100];
    sprintf(buffer, "Block %i AQV %i", blockNumber, aqv);
    Serial.println(buffer);
}

void blockController::printCurrent(float current) {
    char buffer[100];
    float aqcThresh = aqc * DETECTION_MULTIPLIER;
    sprintf(buffer, "Block %i Current ", blockNumber);
    Serial.print(buffer);
    printFloat(current);
    Serial.println("");
    Serial.print("Difference from AQC ");
    if (current>aqcThresh) {
        printFloat(current-aqcThresh);
    } else {
        printFloat(aqcThresh-current);
    }
    Serial.println("");
}

void blockController::printFloat(float fl) {
    int intPart = (int)fl;
    Serial.print(intPart);
    Serial.print(".");
    float pres = fl - intPart;
    for(int ind = 0; ind < PRECISION; ind++) {
        pres *= 10.0f;
        intPart = (int)pres;
        Serial.print(intPart);
        pres -= intPart;
    }
}

void blockController::determineOccupied(float adc_zero, float threshold) {
    float current = readCurrent(adc_zero);
    bool block_occupied = current > threshold;
    if (occupied != block_occupied) {
        if (blockChanged) {
            occupied = block_occupied;
            blockChanged = false;
            if (occupied) {
                printCurrent(current);
            }
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
        delayMicroseconds(10);
    }
    CQ /= reps;

    return CQ + 0.0004f;
}

int blockController::determineQV() {
    float VQ = 0;
    for (int i = 0; i < CALIBRATION_READS; i++) {
        VQ += analogRead(blockInput);
        delayMicroseconds(10);
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
        notifier->sendWebNotification((byte)blockNumber, occupied);
        last_occupied = occupied;
    }
}

