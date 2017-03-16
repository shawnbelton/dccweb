//
// Created by shawn on 01/06/16.
//

#include "blockController.h"

blockController::blockController(int pBlockNumber, uint8_t pBlockInput) {
    blockNumber = pBlockNumber;
    blockInput = pBlockInput;
}

void blockController::setNotifier(Notifier& pNotifier) {
    notifier = &pNotifier;
}

void blockController::init() {
    aqv = 0;
    numReads = 0;
    runningTotal = 0.0f;
    occupied = true;
    blockChanged = false;
    last_occupied = false;
    state = DETERMINE_NORMAL;
    setSignal();
}

void blockController::checkBlock() {
    switch(state) {
        case DETERMINE_NORMAL:
            determineZero();
            break;
        case DETERMINE_NOISE:
            determineNoise();
            break;
        default:
            determineOccupied(aqc * DETECTION_MULTIPLIER);
            break;
    }
}

void blockController::determineZero() {
    runningTotal += (float)analogRead(blockInput);
    if (numReads < CALIBRATION_READS) {
        numReads++;
    } else {
        runningTotal /= CALIBRATION_READS;
        aqv = int(runningTotal);
        runningTotal = 0.0f;
        numReads = 0;
        state = DETERMINE_NOISE;
        printZeroValue();
    }
}

void blockController::determineNoise() {
    runningTotal += readCurrent();
    int reps = (CALIBRATION_READS / numSamples);
    if (numReads < (CALIBRATION_READS / numSamples)) {
        numReads++;
    } else {
        runningTotal /= reps;
        aqc = runningTotal + 0.0004f;
        state = DETERMINE_OCCUPIED;
        printNoiseValue();
    }
}

void blockController::determineOccupied(float threshold) {
    float current = readCurrent();
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

float blockController::readCurrent()
{
    float currentAcc = 0;
    unsigned int count = 0;
    unsigned long prevMicros = micros() - sampleInterval;
    while (count < numSamples)
    {
        if (micros() - prevMicros >= sampleInterval)
        {
            float adc_raw = (float) analogRead(blockInput) - aqc;
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

void blockController::printNoiseValue() {
    char buffer[100];
    sprintf(buffer, "Block %i AQC ", blockNumber);
    Serial.print(buffer);
    printFloat(aqc);
    Serial.println("");
}

void blockController::printZeroValue() {
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
