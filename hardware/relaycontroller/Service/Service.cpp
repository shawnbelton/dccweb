//
// Created by shawn on 06/06/17.
//

#include "Service.h"

void Service::init() {
    macAddress = MACAddress();
    macAddress.init();

    Serial.print(F("Starting Ethernet with MAC Address("));
    Serial.print(macAddress.fullMacString());
    Serial.print(")...");

    if (!Ethernet.begin(macAddress.readAddress())) {
        Serial.println("Fail");
    } else {
        Serial.print("Started with IP:");
        Serial.println(Ethernet.localIP());
        Serial.println(Ethernet.gatewayIP());
        Serial.println(Ethernet.subnetMask());
        Serial.println(Ethernet.dnsServerIP());
        networkReady = true;
        delay(50);
    }

    Serial.println(F("Ready"));
    postInfo();
}

void Service::postInfo() {
    char info[256];
    char ipString[20];
    int dataLen;
    long timeout;
    bool notTimedOut;

    displayAddress(ipString, Ethernet.localIP());
    dataLen = sprintf(info, "{\"controllerId\":\"%s\",\"ipAddress\":\"%s\"}",macAddress.macString(),
            ipString);
    if (client.connect(Ethernet.gatewayIP(), serverPort)) {
        Serial.println("connected");
        // Next should ne client
        client.println("POST /api/relay-controller/update HTTP/1.1");
        client.print("Host:  ");
        client.println(Ethernet.gatewayIP());
        client.println("User-Agent: Arduino/1.0");
        client.println("Connection: close");
        client.println("Content-Type: application/json;");
        client.print("Content-Length: ");
        client.println(dataLen);
        client.println();
        client.println(info);
        timeout = millis() + 5000l;
        notTimedOut = true;
        while(client.available()==0 && notTimedOut)
        {
            if (millis() > timeout) {
                notTimedOut = false;
            }
        }
        if (notTimedOut) {
            int size;
            while ((size = client.available()) > 0) {
                uint8_t *msg = (uint8_t *) malloc(size);
                size = client.read(msg, size);
                Serial.write(msg, size);
                free(msg);
            }
            Serial.println();
        }
        Serial.println("Disconnecting");
        client.stop();
    } else {
        Serial.println("connection failed");
    }
}

void Service::displayAddress(char *str, IPAddress ipAddress) {
    sprintf(str, "%i.%i.%i.%i",ipAddress[0], ipAddress[1], ipAddress[2], ipAddress[3]);
}