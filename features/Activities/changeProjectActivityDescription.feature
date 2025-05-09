@projectDesc
Feature: Change project activity description

  Nikolaj wrote this feature

  

  Scenario: Project leader changes activity description
    Given there is a project with the name "test_project"
    And there is a user with the name "keme"
    And user "keme" is logged in
    And user "keme" is project lead on project "test_project"
    And there is an activity with the name "existingActivity" for project "test_project"
    And the activity description is "" 
    When the user "keme" changes the description of the activity to "Valid Description"
    Then the activity description is updated to "Valid Description"

  Scenario: User updates activity description for leaderless project
    Given there is a project with the name "test_project"
    And there is a user with the name "keme"
    And user "keme" is logged in
    And Project with the name "test_project" has no project lead
    And there is an activity with the name "existingActivity" for project "test_project"
    And the activity description is ""
    When the user "keme" changes the description of the activity to "test"
    Then the activity description is updated to "test"

  Scenario: User who is not project lead fails to update project activity description
    Given there is a project with the name "test_project"
    And there is a user with the name "hgaa"
    And user "hgaa" is logged in
    And user "hgaa" is not project leader for "test_project"
    And the project has a project leader for "test_project"
    And there is an activity with the name "existingActivity" for project "test_project"
    And the activity description is ""
    When the user "hgaa" changes the description of the activity to "test"
    Then the "Error: You are not project lead." error message is given