//
// Created by shawn on 06/06/17.
//

#ifndef RELAYCONTROLLER_SERVICE_H
#define RELAYCONTROLLER_SERVICE_H

#include "Ethernet.h"
#include "MACAddress.h"

const uint16_t serverPort = 8080;

class Service {
private:
    MACAddress macAddress;
    EthernetClient client;
    boolean networkReady;
    void postInfo();
    void displayAddress(char* str, IPAddress ipAddress);
public:
    void init();
};

#endif //RELAYCONTROLLER_SERVICE_H
