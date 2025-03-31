Feature: Change start date of activity

  Scenario: Employee changes start date
    Given user is logged in
    And an activity exists
    And text field ”Start date”is not empty
    When button ”Update information”is clicked
    Then Start date is set to ”Start date”text field value

  Scenario: Employee fails to change start date
    Given user is logged in
    And an activity exists
    And text field ”Start date”is empty
    When button ”Update information”is clicked
    Then the TextfieldIsEmpty error message is printed