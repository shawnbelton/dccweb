#include <Arduino.h>
#include "Block.h"
#include "Notifier.h"


const char* app_name = APP_NAME;
const int LOOP_DELAY = 10;
const int NUM_BLOCKS = 8;
const uint8_t POWER_PIN = 7;

Notifier notifier = Notifier();
blockController blocks[NUM_BLOCKS];
bool powerOn;

void setup() {
    Serial.begin(9600);
    while (!Serial) { ;
    }
    Serial.print(ESC);
    Serial.print("[0m");
    Serial.print(ESC);
    Serial.print("[2J");
    Serial.print(app_name);
    Serial.println(":");
    pinMode(POWER_PIN, OUTPUT);
    digitalWrite(POWER_PIN, LOW);
    Serial.println("Turning power off.");
    powerOn = false;
    notifier.init();
    for(int ind = 0; ind < NUM_BLOCKS; ind++) {
        blocks[ind].setBlockNumber(ind + 1);
        blocks[ind].setNotifier(notifier);
        blocks[ind].setBlockInput(A0 + ind);
        blocks[ind].init();
    }
}

void loop() {
    bool allConfigured = true;
    for(int ind = 0; ind < NUM_BLOCKS; ind++) {
        blocks[ind].checkBlock();
        allConfigured &= blocks[ind].isConfigured();
    }
    if (allConfigured && !powerOn) {
        Serial.println("Turning power on.");
        digitalWrite(POWER_PIN, HIGH);
        powerOn = true;
    }
    delayMicroseconds(LOOP_DELAY);
}
