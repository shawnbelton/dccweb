//
// Created by shawn on 14/03/17.
//

#ifndef BLOCK_CONTROLLER_MAC_ADDRESS_H
#define BLOCK_CONTROLLER_MAC_ADDRESS_H

static const int MAC_POS1 = 0xDE;
static const int MAC_POS2 = 0xAD;
static const int MAC_POS3 = 0xBE;

#include <EEPROM.h>
#include <Arduino.h>


class MACAddress {
private:
    void copyAddress();
    void saveAddress();
    void createAddress();
    char toHexValue(char value);
public:
    void init();
    uint8_t* readAddress();
    char* macString();
};


#endif //BLOCK_CONTROLLER_MAC_ADDRESS_H
