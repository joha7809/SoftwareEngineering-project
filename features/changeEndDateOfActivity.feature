Feature: Change end date of activity

  Scenario: Employee changes end date
    Given user is logged in
    And an activity exists
    And text field ”end date” is not empty
    When the user updates the end date
    Then the end date is updated

  Scenario: Employee fails to change end date
    Given user is logged in
    And an activity exists
    And text field ”end date” is empty
    When the user updates the end date to "" 
    Then a error message is printed