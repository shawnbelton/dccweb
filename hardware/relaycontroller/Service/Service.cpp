//
// Created by shawn on 06/06/17.
//

#include "Service.h"

EthernetServer server(80);

void Service::init(void) {
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
    server.begin();
}

void Service::postInfo(void) {
    char info[256];
    char ipString[20];
    int dataLen;
    long timeout;
    bool notTimedOut;
    HttpResponse httpResponse;

    displayAddress(ipString, Ethernet.localIP());
    dataLen = sprintf(info, "{\"controllerId\":\"%s\",\"ipAddress\":\"%s\"}",macAddress.macString(),
            ipString);
    if (httpClient.connect(Ethernet.gatewayIP(), serverPort)) {
        httpClient.println("POST /api/relay-controller/update HTTP/1.1");
        httpClient.print("Host:  ");
        httpClient.println(Ethernet.gatewayIP());
        httpClient.println("User-Agent: Arduino/1.0");
        httpClient.println("Connection: close");
        httpClient.println("Content-Type: application/json;");
        httpClient.print("Content-Length: ");
        httpClient.println(dataLen);
        httpClient.println();
        httpClient.println(info);
        timeout = millis() + 5000l;
        notTimedOut = true;
        while(httpClient.available()==0 && notTimedOut)
        {
            if (millis() > timeout) {
                notTimedOut = false;
            }
        }
        if (notTimedOut) {
            int size;
            while ((size = httpClient.available()) > 0) {
                uint8_t *msg = (uint8_t *) malloc(size);
                size = httpClient.read(msg, size);
                httpResponse.processMessage(msg, size);
                free(msg);
            }
        }
        httpClient.stop();
        Serial.print("Value: ");
        Serial.println(httpResponse.getContent());
        setRelays(httpResponse.getContent().toInt());
    } else {
        Serial.println("connection failed");
    }
}

void Service::displayAddress(char *str, IPAddress ipAddress) {
    sprintf(str, "%i.%i.%i.%i",ipAddress[0], ipAddress[1], ipAddress[2], ipAddress[3]);
}

void Service::service(void) {
    EthernetClient client = server.available();
    if (client) {
        String header = readHeader(client);
        String method = header.substring(0, header.indexOf(' '));
        String url = header.substring(method.length() + 1, header.indexOf(' ', method.length() + 2));
        String value = url.substring(url.lastIndexOf('/')+1);
        String content = "";
        int contentLengthPos = header.indexOf(content_length);
        if (contentLengthPos >= 0) {
            int contentLength = header.substring(contentLengthPos + 15).toInt();
            content = readContent(client, contentLength);
        }
        Serial.print("Headers: ");
        Serial.println(header);
        Serial.print("Method: ");
        Serial.println(method);
        Serial.print("Url: ");
        Serial.println(url);
        Serial.print("Value: ");
        Serial.println(value);
        setRelays(value.toInt());
        if (0<content.length()) {
            Serial.print("Content: ");
            Serial.println(content);
        }
        writeOK(client);
        delay(1);
        client.stop();
        Ethernet.maintain();
    }
}

String Service::readHeader(EthernetClient client) {
    String header = "";
    boolean endOfHeader = true;
    boolean lineEmpty = true;
    do {
        char c = client.read();
        header += c;
        if (c == 13) {
            //
        } else if (c == 10) {
            if (lineEmpty) {
                endOfHeader = false;
            } else {
                lineEmpty = true;
            }
        } else {
            lineEmpty = false;
        }
    } while(endOfHeader);
    return header;
}

String Service::readContent(EthernetClient client, int length) {
    String content = "";
    for(int read = 0; read < length; read++) {
        content += (char)client.read();
    }
    return content;
}

void Service::writeOK(EthernetClient client) {
    client.println("HTTP/1.1 200 OK");
    client.println("Content-Type: text/html");
    client.println("Connection: close");
    client.println();
    client.println("<!DOCTYPE HTML>");
    client.println("<html>");
    client.println("<body>");
    client.println("<p>Status: OK</p>");
    client.println("</body>");
    client.println("</html>");
}

void Service::setRelays(long value) {
    long testValue = 1;
    for(int index = 0; index < 8; index++) {
        if ((testValue & value)>0) {
            digitalWrite(index, HIGH);
        } else {
            digitalWrite(index, LOW);
        }
        testValue *= 2;
    }
}
