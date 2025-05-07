Feature: Change project activity description

  Nikolaj wrote this feature

  

  Scenario: Project leader changes activity description
    Given there is a project with the name "22001"
    And user "kem" is project lead on project "22001"
    And there is an activity with the name "existingActivity"
    And the activity description is "" 
    When the user "kem" changes the description of the activity
    Then the activity description is updated

  Scenario: Project leader fails to change activity description
    Given there is a project with the name "22001"
    And user "kem" is project lead on project "22001"
    And there is an activity with the name "existingActivity"
    And the activity description is ""
    When the project leader changes the description to ""
    Then the "" error message is given

  Scenario: User updates activity description for leaderless project
    Given there is a project with the name "22001"
    And Project with the name "22001" has no project lead
    And there is an activity with the name "existingActivity"
    And the activity description is not empty
    When the user "kem" changes the description of the activity
    Then the description is updated

  Scenario: User who is not project lead fails to update project activity description
    Given there is a project with the name "22001"
    And the project has a project lead
    And user ”hga” is not project leader for ”22001”
    And there is an activity with the name "existingActivity"
    And the activity description is not empty
    When When the project leader changes the description to ""
    Then the "" error message is given