@viewProject
Feature: View Project

  Johannes wrote this feature

  Scenario: View all projects
    Given there are multiple projects in the system
    When the user views all projects
    Then a list of all projects with their names and IDs is displayed

  Scenario: View details of an existing project
    Given there is a project with the name "existingProject"
    And there is a user with the name "huba"
    And user "huba" is project lead on project "existingProject"
    And there is an activity with the name "Design Phase" for project "existingProject"
    And there is an activity with the name "Development Phase" for project "existingProject"
    And the project has activities
    When the user views the project with the name "existingProject"
    Then the project details are displayed
    And the project lead "huba" is displayed
    And the activities related to the project are displayed
    And the time registrations related to the project are displayed



