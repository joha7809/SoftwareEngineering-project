Feature: Change start date of activity

  Scenario: Employee changes start date
    Given a user is logged in
    And an activity exists
    And start date is set
    When user updates the start date
    Then the start date is updated

  Scenario: Employee fails to change start date
    Given a user is logged in
    And an activity exists
    And start date is not set
    When the user updates the start date to ""
    Then a error message is printed