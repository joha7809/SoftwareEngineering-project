@change
Feature: Change start date of activity

  Background:
    Given a user is logged in
    And there is a project with the name "existingProject"
    And there is an activity with the name "existingActivity" for project "existingProject"

  Scenario: Employee changes start date
    When the user updates the start date to "02022025"
    Then the start date is updated
    And the start date is "02022025"

  Scenario: Fail to change the start date to an invalid date
    When the user updates the start date to "invalidDate"
    Then the "Error: Invalid date format. Please use YYYY-MM-DD." error message is given

  Scenario: Fail to change the start date when the user is not logged in
    Given the user is logged out
    When the user updates the start date to "02022025"
    Then the "Error: No user is logged in." error message is given

  Scenario: Fail to change the start date for a non-existent activity
    Given there is no activity with the name "nonExistentActivity" for project "existingProject"
    When the user updates the start date to "02022025"
    Then the "Error: Activity not found" error message is given