//
// Created by shawn on 14/03/17.
//

#include "MACAddress.h"

void MACAddress::init() {
    randomSeed((unsigned int)analogRead(A7));
    copyAddress();
}

uint8_t* MACAddress::readAddress() {
    return mac;
}

void MACAddress::copyAddress() {
    if (((uint8_t)MAC_POS1) != mac[0]) {
        if (MAC_POS1 == EEPROM.read(0)) {
            Serial.println("Copy MAC Address");
            for (int address = 0; address < 6; address++) {
                mac[address] = EEPROM.read(address);
            }
        } else {
            createAddress();
            saveAddress();
        }
    }
}

void MACAddress::saveAddress() {
    Serial.println("Saving MAC Address");
    for(int address = 0; address < 6; address++) {
        EEPROM.write(address, mac[address]);
    }
}

void MACAddress::createAddress() {
    Serial.println("Creating MAC Address");
    mac[0] = MAC_POS1;
    mac[1] = MAC_POS2;
    mac[2] = MAC_POS3;
    for(int pos = 3; pos < 6; pos++) {
        mac[pos] = (uint8_t)random(255);
    }
}

char* MACAddress::macString() {
    return macString(false);
}

char* MACAddress::fullMacString() {
    return macString(true);
}

char* MACAddress::macString(boolean withColons) {
    uint8_t macValue;
    char* buffer = macStringBuffer;

    for(int ind = 0; ind < 6; ind++) {
        macValue = mac[ind];
        *buffer++ = toHexValue((char)(macValue / 16));
        *buffer++ = toHexValue((char)(macValue % 16));
        if (withColons && ind<5) {
            *buffer++ = ':';
        }
    }
    *buffer = 0;
    return macStringBuffer;
}

char MACAddress::toHexValue(char value) {
    char hexChar = '0' + value;
    if (hexChar > '9') {
        hexChar += 7;
    }
    return hexChar;
}