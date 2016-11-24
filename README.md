# Digital Command Control Web Application

The DCC Web App is designed to be a web interface to control and configure locos on a Digital Command Control enabled layout.

The application is built using the following components:

- Maven
- Java
- NPM
- Spring Boot
- Angular 2
- Bootstrap
- RXTX Serial Communication

Pre-requisites for building the application are that the following are installed.

- Maven Minimum version 3.2.3
- Java JDK 1.8
- NPM Minimum version 3.5.2
- RXTX binary libraries
    
Build the application by using the following maven command
> mvn clean package

Setup the application by creating a config directory at the same directory level as the jar file.

Into the config directory copy the following files:

- application.properties
- dcc-manufacturers.csv
- decoderDefaults.csv

Install the RXTX libraries

To run the build application use
> java -jar dcc-webapp-VERSION.jar

