 Feature: Listing, Creating and Editing Locos
   Scenario: Creating a loco
     Given new train
     When details are added
     | 4711 | Flying Scotsman |
     | 56025 | Smokey Joe |
     Then 2 locos are listed

