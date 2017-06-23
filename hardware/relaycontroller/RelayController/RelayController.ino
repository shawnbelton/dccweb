#include <Arduino.h>
#include "Service.h"

const char* app_name = APP_NAME;
const int LOOP_DELAY = 10;
const char ESC = 27;

Service service = Service();

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
    service.init();
    for (uint8_t pin = 2;pin < 10; pin++) {
        pinMode(pin, OUTPUT);
        digitalWrite(pin, LOW);
    }
}

void loop() {
    service.service();
    delayMicroseconds(LOOP_DELAY);
}
