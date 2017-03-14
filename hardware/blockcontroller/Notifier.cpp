//
// Created by shawn on 12/11/16.
//

#include "Notifier.h"

MACAddress macAddress;
IPAddress server(192,168,1,1);

EthernetClient client;
boolean networkReady;

void Notifier::init() {

    macAddress = MACAddress();
    macAddress.init();

    networkReady = false;

    pinMode(4, OUTPUT);
    digitalWrite(4, HIGH);

    Serial.print(F("Starting Ethernet with MAC Address("));
    Serial.print(macAddress.macString());
    Serial.print(")...");

    if (!Ethernet.begin(macAddress.readAddress())) {
        Serial.println("Fail");
    } else {
        Serial.print("Started with IP:");
        Serial.println(Ethernet.localIP());
        networkReady = true;
    }

    //delay(2000);
    Serial.println(F("Ready"));
}

void Notifier::setLEDChain(ChainableLED &pLEDChain) {
    LEDChain = &pLEDChain;
}

void Notifier::setLED(byte ledNumber, bool state) {
    if (state) {
        setLEDOn(ledNumber);
    } else {
        setLEDOff(ledNumber);
    }
}

void Notifier::setLEDOn(byte ledNumber) {
    LEDChain->setColorRGB(ledNumber, 16, 0, 0);
}

void Notifier::setLEDOff(byte ledNumber) {
    LEDChain->setColorRGB(ledNumber, 0, 16, 0);
}

void Notifier::sendWebNotification(byte blockNumber, bool occupied) {
    char params[64];
    setLED(blockNumber - 1, occupied);
    sprintf(params, "/block/%s/%i/occupied/%s", macAddress.macString(), blockNumber, occupied ? "true" : "false");
    Serial.println(params);
    if (networkReady) {
        if (!getPage(server, serverPort, params)) {
            Serial.println("Fail");
        } else {
            Serial.println("Pass");
        }
    }
}

byte Notifier::getPage(IPAddress ipBuf, uint16_t thisPort, char *page) {
    int inChar;
    char outBuf[128];

    Ethernet.maintain();
    byte retVal = 0;
    Serial.print(F("Connecting...."));
    if (client.connect(ipBuf, thisPort)==1) {
        Serial.println(F("Connected"));
        sprintf(outBuf,"GET %s HTTP/1.1",page);
        client.println(outBuf);
        sprintf(outBuf,"Host: %s",serverName);
        client.println(outBuf);
        client.println(F("Connection: close\r\n"));
        int connectLoop = 0;
        while(client.connected()) {
            while(client.available()) {
                inChar = client.read();
                Serial.write(inChar);
                connectLoop = 0;
            }
            connectLoop++;
            if (connectLoop>10000) {
                Serial.println();
                Serial.println("Timeout");
                client.status();
            }
            delay(1);
        }
        Serial.println();
        Serial.println("Disconnecting");
        client.stop();
        retVal = 1;
    } else {
        Serial.println(F("Failed"));
    }
    return retVal;
}
