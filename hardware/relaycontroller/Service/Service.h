//
// Created by shawn on 06/06/17.
//

#ifndef RELAYCONTROLLER_SERVICE_H
#define RELAYCONTROLLER_SERVICE_H

#include "Ethernet.h"
#include "MACAddress.h"
#include "HttpResponse.h"
#include "WString.h"

const uint16_t serverPort = 8080;
static const char *const content_length = "Content-Length:";

class Service {
private:
    MACAddress macAddress;
    EthernetClient httpClient;
    boolean networkReady;
    void postInfo(void);
    void displayAddress(char* str, IPAddress ipAddress);
    String readHeader(EthernetClient client);
    String readContent(EthernetClient client, int length);
    void writeOK(EthernetClient client);
    void setRelays(long value);
public:
    void init(void);
    void service(void);
};

#endif //RELAYCONTROLLER_SERVICE_H
