Feature: Assign project lead

  Scenario: Successfully assign project lead
    Given a project called ”22001” exists
    And a user called ”hga” exists.
    And Project "22001" has no project lead
    When The user ”hga” is assigned as project lead on project ”22001”
    Then the project ”22001” has project lead ”hga”.

  Scenario: Assigning non-existing user as project lead to project
    Given a project called ”22001” exists
    And there exists no user ”unk”.
    When the user ”unk” is assigned as project lead on project ”22001”
    Then the UserNotFound error message is given.

  Scenario: Assigning project lead to non-existing project
    Given a user called ”hga” exists.
    And there exists no project called "00000"
    When the user ”hga” is assigned as project lead on project "00000"
    Then the ProjectNotFound error message is given.

  Scenario: Assigning project lead to project which already has project lead
    Given a project called ”22001” exists
    And a user called ”hga” exists
    And a user called ”kem” exists
    And user ”kem” is project lead on project "22001"
    When the user ”hga” is assigned as project lead on project ”22001”
    Then the project lead "hga" is replaced with new project lead "kem"