@change
Feature: Change end date of activity

  Nikolaj wrote this feature

  Background:
    Given a user is logged in
    And there is a project with the name "existingProject"
    And there is an activity with the name "existingActivity" for project "existingProject"

  Scenario: Employee changes end week
    When the user updates the end week to "12"
    Then the end week is updated
    And the end week is "12"

  Scenario: Fail to change the end date to an invalid date
    When the user updates the end week to "invalidDate"
    Then the "Error: Invalid week format. Please use: WW" error message is given

  Scenario: Fail to change the end date when the user is not logged in
    Given the user is logged out
    When the user updates the end week to "12"
    Then the "Error: No user is logged in." error message is given

  Scenario: Fail to change the end date for a non-existent activity
    Given there is no activity with the name "nonExistentActivity" for project "existingProject"
    When the user updates the end week to "12"
    Then the "Error: Activity not found" error message is given
