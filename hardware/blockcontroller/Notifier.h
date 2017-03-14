//
// Created by shawn on 12/11/16.
//

#ifndef BLOCK_CONTROLLER_NOTIFIER_H
#define BLOCK_CONTROLLER_NOTIFIER_H

#include <ChainableLED.h>
#include <Ethernet.h>
#include "MACAddress.h"

const char serverName[]  = "192.168.0.19";
const uint16_t serverPort = 8080;

class Notifier {
private:
    ChainableLED* LEDChain;
    byte getPage(IPAddress ipBuf,uint16_t thisPort, char *page);
    void setLEDOn(byte ledNumber);
    void setLEDOff(byte ledNumber);
    void setLED(byte ledNumber, bool state);

public:
    void init();
    void setLEDChain(ChainableLED& pLEDChain);
    void sendWebNotification(byte blockNumber, bool occupied);
};


#endif //BLOCK_CONTROLLER_NOTIFIER_H
