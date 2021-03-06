//
// Created by shawn on 12/11/16.
//

#ifndef BLOCK_CONTROLLER_NOTIFIER_H
#define BLOCK_CONTROLLER_NOTIFIER_H

#include "Ethernet.h"
#include "MACAddress.h"

#ifdef WITH_LEDS
#include <ChainableLED.h>
#endif

//Use #include <Ethernet.h> to use arduino ether net shield.

const uint16_t serverPort = 8080;
#ifdef WITH_LEDS
const int clockPin = 8;
const int dataPin = 9;
#endif

#ifdef WITH_SD
const int SSD_SELECT = 4;
#endif

class Notifier {
private:
    MACAddress macAddress;
    EthernetClient client;
    boolean networkReady;

    byte getPage(char *page);

#ifdef WITH_LEDS
    void setLEDOn(byte ledNumber);
    void setLEDOff(byte ledNumber);
    void setLEDInit(byte ledNumber);
    void setLED(byte ledNumber, bool state);
    void printSetLED(byte ledNo, const char* colour);
#endif

public:
    void init();
    void sendWebNotification(byte blockNumber, bool occupied);

};


#endif //BLOCK_CONTROLLER_NOTIFIER_H
