@change
Feature: Change start week of activity

  Background:
    Given a user is logged in
    And there is a project with the name "existingProject"
    And there is an activity with the name "existingActivity" for project "existingProject"

  Scenario: Employee changes start week
    When the user updates the start week to "13"
    Then the start week is updated
    And the start week is "13"

  Scenario: Fail to change the start week to an invalid week
    When the user updates the start week to "invalidweek"
    Then the "Error: Invalid week format. Please use: WW" error message is given

  Scenario: Fail to change the start week when the user is not logged in
    Given the user is logged out
    When the user updates the start week to "13"
    Then the "Error: No user is logged in." error message is given

  Scenario: Fail to change the start week for a non-existent activity
    Given there is no activity with the name "nonExistentActivity" for project "existingProject"
    When the user updates the start week to "13"
    Then the "Error: Activity not found" error message is given