Feature: Assign developers


  Adam wrote this feature 
  //https://learn.microsoft.com/en-us/training/browse/?resource_type=learning%20path

  Scenario: Assign developer
    Given user "kem" is project lead on project "existingProject"
    And user "Bob" is available
    When project leader assigns "Bob" to an activity
    Then "Bob" is assigned to the activity

  Scenario: failing to assign a developer
    Given user "kem" is project lead on project "existingProject"
    And user "Bob" is unavailable
    When project leader assigns "Bob" to an activity
    Then "Bob" is not assigned to the activity

  #Scenario: assign a different developer
    #Given user "kem" is project lead on project "existingProject"
    #And user "Bob" is unavailable
    #When project leader assigns "Bob" to an activity
    #Then "Bob" status is unchanged

  