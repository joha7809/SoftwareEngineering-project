Feature: Change end date of activity

  Scenario: Employee changes end date
    Given user is logged in
    And an activity exists
    And text field ”End date”is not empty
    When button ”Update information”is clicked
    Then end date is set to ”End date”textfield value

  Scenario: Employee fails to change end date
    Given user is logged in
    And an activity exists
    And text field ”End date”is empty
    When button ”Update information”is clicked
    Then the TextfieldIsEmpty error message is printed