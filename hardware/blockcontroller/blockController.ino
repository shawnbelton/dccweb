#include <Arduino.h>
#include "blockController.h"
#include "Notifier.h"


const char* app_name = APP_NAME;

Notifier notifier = Notifier();
blockController blocks[4];
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
    Serial.println("Turning power off.");
    powerOn = false;
    notifier.init();
    blocks[0].setBlockInput(A0);
    blocks[1].setBlockInput(A1);
    blocks[2].setBlockInput(A2);
    blocks[3].setBlockInput(A3);
    for(int ind = 0; ind < 4; ind++) {
        blocks[ind].setBlockNumber(ind + 1);
        blocks[ind].setNotifier(notifier);
        blocks[ind].init();
    }
}

void loop() {
    bool allConfigured = true;
    for(int ind = 0; ind < 4; ind++) {
        blocks[ind].checkBlock();
        allConfigured &= blocks[ind].isConfigured();
    }
    if (allConfigured && !powerOn) {
        Serial.println("Turning power on.");
        powerOn = true;
    }
    delayMicroseconds(10);
}
