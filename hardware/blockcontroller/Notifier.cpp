//
// Created by shawn on 12/11/16.
//

#include "Notifier.h"

const char* macAddress = MAC_ADDRESS;

byte mac[6];

IPAddress server(192,168,1,1);

EthernetClient client;
boolean networkReady;

void Notifier::init() {

    networkReady = false;

    pinMode(4, OUTPUT);
    digitalWrite(4, HIGH);

    buildMacAddress();

    Serial.print(F("Starting Ethernet with MAC Address("));
    printMAC();
    Serial.print(")...");

    if (!Ethernet.begin(mac)) {
        Serial.println("Fail");
    } else {
        Serial.print("Started with IP:");
        Serial.println(Ethernet.localIP());
        networkReady = true;
    }

    delay(2000);
    Serial.println(F("Ready"));
}

void Notifier::buildMacAddress() {
    byte macValue = 0;
    byte hexValue;
    bool firstByte = true;
    byte* macPtr = mac;
    char* macAddressPtr = strdup(macAddress);
    char readValue = *macAddressPtr++;
    while(readValue != 0) {
        hexValue = (byte)(readValue - '0');
        if (hexValue > 9) {
            hexValue -= 7;
        }
        macValue += hexValue;
        if (firstByte) {
            macValue *= 16;
        } else {
            *macPtr++ = macValue;
            macValue = 0;
        }
        firstByte = !firstByte;
        readValue = *macAddressPtr++;
    }
}

void Notifier::printMAC() {
    int index;
    byte* macAdd = mac;
    for(index = 0; index < 6; index++) {
        if (index > 0) {
            Serial.print(", ");
        }
        Serial.print(*macAdd++);
    }
}

void Notifier::setChainableLED(ChainableLED &pChainableLED) {
    chainableLED = &pChainableLED;
}

void Notifier::setLED(byte ledNumber, bool state) {
    if (state) {
        setLEDOn(ledNumber);
    } else {
        setLEDOff(ledNumber);
    }
}

void Notifier::setLEDOn(byte ledNumber) {
    chainableLED->setColorRGB(ledNumber, 16, 0, 0);
}

void Notifier::setLEDOff(byte ledNumber) {
    chainableLED->setColorRGB(ledNumber, 0, 16, 0);
}

void Notifier::sendWebNotification(byte blockNumber, bool occupied) {
    char params[32];
    setLED(blockNumber - 1, occupied);
    sprintf(params, "/block/%i/occupied/%s", blockNumber, occupied ? "true" : "false");
    Serial.println(params);
    if (networkReady) {
        if (!getPage(server, serverPort, params)) {
            Serial.println("Fail");
        } else {
            Serial.println("Pass");
        }
    }
}

byte Notifier::getPage(IPAddress ipBuf, int thisPort, char *page) {
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
