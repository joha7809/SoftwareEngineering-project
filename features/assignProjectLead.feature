Feature: Assign project lead

  Scenario: Successfully assign project lead
    Given a project called ”22001”exists
    And a user called ”hga”exists.
    When The user ”hga”is assigned as project lead on project ”22001”
    Then the project ”22001”has project lead ”hga”.

  Scenario: Assigning missing project lead to project
    Given a project called ”22001”exists
    And there exists no user ”hga”.
    When the user ”hga”is assigned as project lead on project ”22001”
    Then the TextfieldIsEmpty error message is given.

  Scenario: Assigning project lead to missing project
    Given a project called ”22001”exists
    And a user called ”hga”exists.
    When the user ”hga”is assigned as project lead on project x
    When The user ”hga”is assigned as project lead on project ”22001”
    Then the TextfieldIsEmpty error message is given.

  Scenario: Assigning existing project lead as project lead
    Given a project called ”22001”exists
    And a user called ”hga”exists.
    And user ”hga”is project lead on project ”22001”
    When the user ”hga”is assigned as project lead on project ”22001”
    Then the ItemAlreadyAssigned error message is given.

  Scenario: Assigning project lead to project which already has project lead
    Given a project called ”22001”exists
    And a user called ”hga”exists.
    And a user called ”kem”exists.
    And user ”kem”is project lead on project x
    When the user ”hga”is assigned as project lead on project ”22001”
    Then the project lead is replaced with new project lead.