@assignProjectLead
Feature: Assign project lead

  Johannes wrote this feature

  Scenario: Successfully assign project lead
    Given there is a project with the name "existingProject"
    And there is a user with the name "huba"
    And Project with the name "existingProject" has no project lead
    When The user "huba" is assigned as project lead on project "existingProject"
    Then the project "existingProject" has project lead "huba"

  Scenario: Assigning non-existing user as project lead to project
    Given there is a project with the name "existingProject"
    And there is no user with the name "huba"
    When The user "huba" is assigned as project lead on project "existingProject"
    Then the "Error: User not found" error message is given

  Scenario: Assigning project lead to non-existing project
    Given there is a user with the name "huba"
    And there is no project with the name "00000"
    When The user "huba" is assigned as project lead on project "00000"
    Then the "Error: Project not found" error message is given

  Scenario: Assigning project lead to project which already has project lead
    Given there is a project with the name "existingProject"
    And there is a user with the name "huba"
    And there is a user with the name "kem"
    And user "kem" is project lead on project "existingProject"
    When The user "huba" is assigned as project lead on project "existingProject"
    Then the project lead "kem" is replaced with new project lead "huba"

