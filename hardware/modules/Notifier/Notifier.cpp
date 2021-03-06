//
// Created by shawn on 12/11/16.
//

#include "Notifier.h"

#ifdef WITH_LEDS
ChainableLED LEDChain = ChainableLED(clockPin, dataPin, 4);
#endif

void Notifier::init() {

    macAddress = MACAddress();
    macAddress.init();

#ifdef WITH_LEDS
    LEDChain.init();
    for(byte index = 0; index < 8 ; index++) {
        setLEDInit(index);
    }
#endif

    networkReady = false;

#ifdef WITH_SD
    pinMode(SSD_SELECT, OUTPUT);
    digitalWrite(SSD_SELECT, HIGH);
#endif

    Serial.print(F("Starting Ethernet with MAC Address("));
    Serial.print(macAddress.fullMacString());
    Serial.print(")...");

    if (!Ethernet.begin(macAddress.readAddress())) {
        Serial.println("Fail");
    } else {
        Serial.print("Started with IP:");
        Serial.println(Ethernet.localIP());
        Serial.println(Ethernet.gatewayIP());
        Serial.println(Ethernet.subnetMask());
        Serial.println(Ethernet.dnsServerIP());
        networkReady = true;
        delay(50);
    }

    Serial.println(F("Ready"));
}

#ifdef WITH_LEDS
void Notifier::setLED(byte ledNumber, bool state) {
    if (state) {
        setLEDOn(ledNumber);
    } else {
        setLEDOff(ledNumber);
    }
}

void Notifier::setLEDOn(byte ledNumber) {
    LEDChain.setColorRGB(ledNumber, 1, 0, 0);
    printSetLED(ledNumber, "red");
}

void Notifier::setLEDOff(byte ledNumber) {
    LEDChain.setColorRGB(ledNumber, 0, 1, 0);
    printSetLED(ledNumber, "green");
}

void Notifier::setLEDInit(byte ledNumber) {
    LEDChain.setColorRGB(ledNumber, 0, 0, 1);
    printSetLED(ledNumber, "blue");
}

void Notifier::printSetLED(byte ledNumber, const char* colour) {
    char ledInfo[64];
    sprintf(ledInfo, "Setting %i to %s.", ledNumber, colour);
    Serial.println(ledInfo);
}
#endif

void Notifier::sendWebNotification(byte blockNumber, bool occupied) {
    char params[64];
#ifdef WITH_LEDS
    setLED(blockNumber - 1, occupied);
#endif
    sprintf(params, "/api/block/%s/%i/occupied/%s", macAddress.macString(), blockNumber, occupied ? "true" : "false");
    Serial.println(params);
    if (networkReady) {
        while(!getPage(params)) {
            Serial.println("Failed");
            delay(1000);
        }
    }
}

byte Notifier::getPage(char *page) {
    char outBuf[128];
    long timeout;
    bool notTimedOut;
    Ethernet.maintain();

    byte retVal = 0;
    Serial.print(F("Connecting...."));
    Serial.println(Ethernet.gatewayIP());
    client.setTimeout(100l);
    if (client.connect(Ethernet.gatewayIP(), serverPort)) {
        Serial.println(F("Connected"));
        sprintf(outBuf,"GET %s HTTP/1.1",page);
        client.println(outBuf);
        client.print("Host: ");
        client.println(Ethernet.gatewayIP());
        client.println(F("Connection: close\r\n"));
        timeout = millis() + 5000l;
        notTimedOut = true;
        while(client.available()==0 && notTimedOut)
        {
            if (millis() > timeout) {
                notTimedOut = false;
            }
        }
        if (notTimedOut) {
            int size;
            while ((size = client.available()) > 0) {
                uint8_t *msg = (uint8_t *) malloc(size);
                size = client.read(msg, size);
                Serial.write(msg, size);
                free(msg);
            }
            Serial.println();
        }
        Serial.println("Disconnecting");
        client.stop();
        retVal = 1;
    }
    return retVal;
}
