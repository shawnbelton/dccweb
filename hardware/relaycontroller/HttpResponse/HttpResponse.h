//
// Created by shawn on 09/06/17.
//

#ifndef RELAYCONTROLLER_HTTPRESPONSE_H
#define RELAYCONTROLLER_HTTPRESPONSE_H

#include "WString.h"
#include <Arduino.h>

enum State {
    HEADERS,
    CONTENT_LENGTH,
    CONTENT,
    END
};

class HttpResponse {
private:
    State readState;
    String header;
    String content;
    long contentSize;
    long contentRead;
    void headerChar(char readChar);
    void readContentLength(char readChar);
    void readContent(char readChar);
public:
    HttpResponse(void);
    void processMessage(uint8_t *msg, int size);
    String getContent(void);
};


#endif //RELAYCONTROLLER_HTTPRESPONSE_H
