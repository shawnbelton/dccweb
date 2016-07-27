Feature: Programming on the Program Track
  Scenario: Read Decoder
    Given a train on the program track fitted with a decoder with CV Values
    | 8 | 151 |
    | 7 | 34 |
    | 1 | 3  |
    | 17 | 1 |
    | 18 | 101 |
    When decoder is read
    Then decoder manufacture is 'Electronic Solutions Ulm GmbH'
    And version is 34
