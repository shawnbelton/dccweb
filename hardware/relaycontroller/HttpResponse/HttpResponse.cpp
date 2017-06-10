//
// Created by shawn on 09/06/17.
//

#include "HttpResponse.h"

HttpResponse::HttpResponse(void) {
    readState = HEADERS;
    header = "";
}

void HttpResponse::processMessage(uint8_t *msg, int size) {
    uint8_t *msgPtr = msg;
    for (int index = 0; index < size; index++) {
        char current = (char)*(msgPtr++);
        switch(readState) {
            case HEADERS:
                headerChar(current);
                break;
            case CONTENT_LENGTH:
                readContentLength(current);
                break;
            case CONTENT:
                readContent(current);
                break;
            default:
                break;
        }
    }
}

void HttpResponse::headerChar(char readChar) {
    if (10==readChar) {
        if (0==header.length()) {
            readState = CONTENT_LENGTH;
            contentSize = 0;
        } else {
            Serial.print("Header: \"");
            Serial.print(header);
            Serial.println("\"");
        }
        header = "";
    } else {
        if (13!=readChar) {
            header += readChar;
        }
    }
}

void HttpResponse::readContentLength(char readChar) {
    if (10==readChar) {
        readState = CONTENT;
        contentRead = 0;
        header = "";
        Serial.print("Content Size: ");
        Serial.println(contentSize);
    } else {
        if (13!=readChar) {
            contentSize *= 16;
            if (readChar >= '0' && readChar <= '9') {
                contentSize += (long) (readChar - '0');
            } else if (readChar >= 'a' && readChar <= 'f') {
                contentSize += (long) (readChar - 'a' + 10);
            } else if (readChar >= 'A' && readChar <= 'F') {
                contentSize += (long) (readChar - 'A' + 10);
            }
        }
    }
}

void HttpResponse::readContent(char readChar) {
    if (contentRead<contentSize) {
        header += readChar;
        contentRead++;
    } else {
        Serial.print("Content: \"");
        Serial.print(header);
        Serial.println("\"");
        content = header;
        readState = END;
    }
}

String HttpResponse::getContent(void) {
    return content;
}