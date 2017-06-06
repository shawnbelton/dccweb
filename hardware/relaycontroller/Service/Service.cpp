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
    displayAddress(ipString, Ethernet.localIP());
    dataLen = sprintf(info, "{\"controllerId\":\"%s\",\"ipAddress\":\"%s\"}",macAddress.macString(),
            ipString);
    //if (client.connect()) {
        Serial.println("connected");
        // Next should ne client
        Serial.println("POST /api/relay-controller/update HTTP/1.1");
        Serial.print("Host:  ");
        Serial.println(Ethernet.gatewayIP());
        Serial.println("User-Agent: Arduino/1.0");
        Serial.println("Connection: close");
        Serial.println("Content-Type: application/json;");
        Serial.print("Content-Length: ");
        Serial.println(dataLen);
        Serial.println();
        Serial.println(info);
//    } else {
//        Serial.println("connection failed");
//    }
}

void Service::displayAddress(char *str, IPAddress ipAddress) {
    sprintf(str, "%i.%i.%i.%i",ipAddress[0], ipAddress[1], ipAddress[2], ipAddress[3]);
}