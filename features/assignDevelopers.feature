@developer2
Feature: Assign developers


  Adam wrote this feature 
  //https://learn.microsoft.com/en-us/training/browse/?resource_type=learning%20path

  Scenario: Assign developer
    Given there is a user with the name "kem"
    And there is a user with the name "Bob"
    And there is a project with the name "existingProject"
    And user "kem" is project lead on project "existingProject"
    And user "Bob" is available
    And there is an activity with the name "Sleep" for project "existingProject"
    When project leader assigns "Bob" to an activity
    Then "Bob" is assigned to the activity

  Scenario: failing to assign a developer
    Given there is a user with the name "kem"
    And there is a user with the name "Bob"
    And there is a project with the name "existingProject"
    And user "kem" is project lead on project "existingProject"
    And user "Bob" is unavailable
    And there is an activity with the name "Sleep" for project "existingProject"
    When project leader assigns "Bob" to an activity
    Then "Bob" is not assigned to the activity

  #Scenario: assign a different developer
    #And user "kem" is project lead on project "existingProject"
    #And user "Bob" is unavailable
    #When project leader assigns "Bob" to an activity
    #Then "Bob" status is unchanged

  