@developer2
Feature: Assign developers


  Adam wrote this feature 
  //https://learn.microsoft.com/en-us/training/browse/?resource_type=learning%20path

  Scenario: Assign developer
    Given there is a user with the name "keme"
    And user "keme" is logged in
    And there is a user with the name "bobo"
    And there is a project with the name "existingProject"
    And user "keme" is project lead on project "existingProject"
    And user "bobo" is available in week "4"
    And there is an activity with the name "Sleep" for project "existingProject"
    And the activity "Sleep" starts in week "3" and ends in week "5"
    When project leader assigns "bobo" to the activity "Sleep"
    Then "bobo" is assigned to the activity

  Scenario: Fail to assign developer to a null activity
    Given there is a user with the name "keme"
    And user "keme" is logged in
    And there is a user with the name "bobo"
    And there is a project with the name "existingProject"
    And user "keme" is project lead on project "existingProject"
    And there is no activity with the name "nullActivity" for project "existingProject"
    When project leader assigns "bobo" to the activity "nullActivity"
    Then the "Error: Activity not found" error message is given
  
  Scenario: Fail to assign non-existing developer
    Given there is a user with the name "keme"
    And user "keme" is logged in
    And there is no user with the name "nonExistingUser"
    And there is a project with the name "existingProject"
    And user "keme" is project lead on project "existingProject"
    And there is an activity with the name "Sleep" for project "existingProject"
    When project leader assigns "nonExistingUser" to the activity "Sleep"
    Then the "Error: User not found" error message is given

  