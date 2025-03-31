Feature: Create fixed activity

  Scenario: User creates fixed activity
    Given user exists
    When the user enters data for the fixed activity
    Then a fixed activity is created with the data passed
    
  Scenario: User fails to create a fixed activity
    Given user exists
    When the user enters an invalid data for the fixed activity
    Then a string code will state that the entered data are invalid
