Feature: Change end date of activity

  Nikolaj wrote this feature

  Scenario: Employee changes end date
    Given user is logged in
    And an activity exists
    And the end date is set
    When the user updates the end date
    Then the end date is updated

  Scenario: Employee fails to change end date
    Given user is logged in
    And an activity exists
    And the end date is not set
    When the user updates the end date to "" 
    Then a error message is printed