//
// Created by shawn on 12/11/16.
//

#ifndef BLOCK_CONTROLLER_NOTIFIER_H
#define BLOCK_CONTROLLER_NOTIFIER_H

#include <ChainableLED.h>
//Use #include <Ethernet.h> to use arduino ether net shield.
#include <UIPEthernet.h>

#include "MACAddress.h"

const char serverName[]  = "192.168.0.19";
const uint16_t serverPort = 8080;
const int clockPin = 8;
const int dataPin = 9;
const int SSD_SELECT = 4;


class Notifier {
private:
    MACAddress macAddress;
    EthernetClient client;
    boolean networkReady;

    byte getPage(char *page);
    void setLEDOn(byte ledNumber);
    void setLEDOff(byte ledNumber);
    void setLED(byte ledNumber, bool state);

public:
    void init();
    void sendWebNotification(byte blockNumber, bool occupied);
};


#endif //BLOCK_CONTROLLER_NOTIFIER_H
