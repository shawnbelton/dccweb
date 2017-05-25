# Connecting a XCSource W5100 to an arduino

In order to keep the footprint of the
build small a XCSource W5100 Ethernet 
module or similar should be used for 
network connections. 

Pins
- GND – Ground from the Arduino
- VIN – +5V from Arduino
- NSS – Pin 10 from Arduino
- MO – Pin 11 from Arduino (MOSI)
- MI – Pin 12 from Arduino (MISO)
- SOK – Pin 13 from Arduino (SCK)

If using an IDC connector and ribbon cable the pins are numbered as follows

- 1 GND
- 2 VIN +5V
- 3 RST
- 4 NSS     Pin 10 Arduino
- 5 SCK     Pin 13 Arduino
- 6 MOSI    Pin 11 Arduino
- 7 MISO    Pin 12 Arduino
- 8 GND
- 9 POE+
- 10 POE-

from https://www.jdohnalek.com/arduino-nano-and-wiznet-w5100-red/
