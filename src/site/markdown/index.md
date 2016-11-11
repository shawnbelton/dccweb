Digital Command Control Web Application
=======================================

The DCC Web App is designed to be a web interface to control and configure locos on a Digital Command Control enabled layout.

The application is built using the following components:

*  Maven
*  Java
*  Spring Boot
*  AngularJS
*  Bootstrap
*  RXTX Serial Communication
    
Build the application by using the following maven command
> mvn clean package

Setup the application by creating a config and logs directory at the same directory level as the jar file.

Into the config directory copy the following files:

*  application.properties
*  dcc-manufacturers.csv
*  decoderDefaults.csv

Install the RXTX libraries

To run the build application use
> java -jar dcc-webapp-VERSION.jar

