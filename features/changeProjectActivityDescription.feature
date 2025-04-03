Feature: Change project activity description

  Scenario: Project leader changes activity description
    Given a project called ”22001” exists
    And user ”hga” is project leader for ”22001”
    And an activity exists
    And the activity description is not empty
    When the project leader changes the description
    Then the description is updated

  Scenario: Project leader fails to change activity description
    Given a project called ”22001” exists
    And user ”hga” is project leader for ”22001”
    And an activity exists
    And the activity description is empty
    When the project leader changes the description to ""
    Then a error message is printed

  Scenario: User updates activity description for leaderless project
    Given a project called ”22001” exists
    And project ”22001” has no project lead
    And the activity description is not empty
    When the project leader changes the description
    Then the description is updated

  Scenario: User who is not project lead fails to update project activity description
    Given a project called ”22001” exists
    And the project has a project lead
    And user ”hga” is not project leader for ”22001”
    And the activity description is not empty
    When When the project leader changes the description to ""
    Then a error message is printed