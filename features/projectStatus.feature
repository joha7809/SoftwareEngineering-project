@projectStatus
Feature: Project status

  Adam wrote this feature

  Scenario: Project lead receives status report
    Given there is a project with the name "existingProject"
    And there is a user with the name "huba"
    And user "huba" is logged in
    And user "huba" is project lead on project "existingProject"
    And there is an activity with the name "code" for project "existingProject"
    And the project has activities
    And the project has timeRegistrations
    When the user gets the report
    Then "Time spent:" is printed
    And the sum of timeRegistrations is printed
    And "Time budgetted:" is printed
    And the sum of estimatedRemainingHours is printed

  Scenario: Project lead fails to receive status report
    Given there is a project with the name "existingProject"
    And there is a user with the name "huba"
    And user "huba" is logged in
    And user "huba" is project lead on project "existingProject"
    And the project has no activities
    When the user gets the report
    Then returns a string "No activities has been created yet"

  Scenario: Project lead receives status report without time registrations
    Given there is a project with the name "existingProject"
    And there is a user with the name "huba"
    And user "huba" is logged in
    And user "huba" is project lead on project "existingProject"
    And the project has activities
    And the project has no timeRegistrations
    When the user gets the report
    Then "No time has been registred for activities related to this project" is printed
    And "Time budgetted:" is printed
    And the sum of estimatedRemainingHours is printed