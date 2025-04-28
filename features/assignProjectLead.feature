@project
Feature: Assign project lead

  Johannes wrote this feature

  Scenario: Successfully assign project lead
    Given a project called "22001" exists
    And a user called "hga" exists
    And Project "22001" has no project lead
    When The user "hga" is assigned as project lead on project "22001"
    Then the project "hga" has project lead "22001"

  Scenario: Assigning non-existing user as project lead to project
    Given a project called "22001" exists
    And there exists no user "unk"
    When The user "unk" is assigned as project lead on project "00000"
    Then the "UserNotFound" error message is given

  Scenario: Assigning project lead to non-existing project
    Given a user called "hga" exists
    And there exists no project called "00000"
    When The user "hga" is assigned as project lead on project "00000"
    Then the "ProjectNotFound" error message is given

  Scenario: Assigning project lead to project which already has project lead
    Given a project called "22001" exists
    And a user called "hga" exists
    And a user called "kem" exists
    And user "kem" is project lead on project "22001"
    When The user "hga" is assigned as project lead on project "00000"
    Then the project lead "hga" is replaced with new project lead "kem"

