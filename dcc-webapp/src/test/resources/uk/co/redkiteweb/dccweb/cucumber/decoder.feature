Feature: Programming on the Program Track
  Scenario: Read Decoder
    Given a loco on the program track fitted with a decoder with CV Values
    | 8 | 151 |
    | 7 | 255 |
    | 1 | 3  |
    | 17 | 1 |
    | 18 | 101 |
    | 29 | 0   |
    When decoder is read
    Then decoder manufacture is 'Electronic Solutions Ulm GmbH'
    And version is 255
