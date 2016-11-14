//
// Created by shawn on 12/11/16.
//

#ifndef BLOCKCONTROLLER_NOTIFIER_H
#define BLOCKCONTROLLER_NOTIFIER_H

#include <ChainableLED.h>
#include <Ethernet.h>

const char serverName[]  = "192.168.0.19";
const int serverPort = 8080;

class Notifier {
private:
    ChainableLED* chainableLED;
    byte getPage(IPAddress ipBuf,int thisPort, char *page);
    void setLEDOn(byte ledNumber);
    void setLEDOff(byte ledNumber);
    void setLED(byte ledNumber, bool state);

public:
    void init();
    void setChainableLED(ChainableLED& pChainableLED);
    void sendWebNotification(byte blockNumber, bool occupied);
};


#endif //BLOCKCONTROLLER_NOTIFIER_H
