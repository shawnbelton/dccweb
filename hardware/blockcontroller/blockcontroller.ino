#include <Arduino.h>
#include <ChainableLED.h>
#include "blockController.h"
#include "Notifier.h"

const int clockPin = 8;
const int dataPin = 9;

Notifier notifier = Notifier();
ChainableLED chainableLED = ChainableLED(clockPin, dataPin, 4);
blockController block1 = blockController(1, A0);
blockController block2 = blockController(2, A1);
blockController block3 = blockController(3, A2);
blockController block4 = blockController(4, A3);

void setup() {
    Serial.begin(9600);
    while (!Serial) { ;
    }
    Serial.print(ESC);
    Serial.print("[0m");
    Serial.print(ESC);
    Serial.println("[2JBlock Controller:");
    chainableLED.init();
    for(int index = 1; index <=4 ; index++) {
        chainableLED.setColorRGB(index, 0,0,0);
    }
    notifier.init();
    notifier.setChainableLED(chainableLED);
    block1.setNotifier(notifier);
    block2.setNotifier(notifier);
    block3.setNotifier(notifier);
    block4.setNotifier(notifier);
    block1.init();
    block2.init();
    block3.init();
    block4.init();
}

void loop() {
    block1.checkBlock();
    block2.checkBlock();
    block3.checkBlock();
    block4.checkBlock();
    delay(50);
}
