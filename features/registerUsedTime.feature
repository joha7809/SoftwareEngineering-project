Feature: Register used time

  Kerem wrote this

  Scenario: A user registers used time on an activity
    Given there is a user with the name "huba"
    And user "huba" is logged in
    And there is a project with the name "existingProject"
    And there is an activity with the name "projectActivity" for project "existingProject"
    When the user logs "6.5" hours on the activity 
    Then the time used is registered on the activity

  Scenario: A user registers used time on an activity
    Given there is a user with the name "huba"
    And user "huba" is logged in
    And there is a project with the name "existingProject"
    And there is an activity with the name "projectActivity" for project "existingProject"
    When the user logs "6.5" hours on the activity
    Then the time used is registered on the activity

  Scenario: Fail to register time on a non-existent activity
    Given there is a user with the name "huba"
    And user "huba" is logged in
    And there is a project with the name "existingProject"
    And there is no activity with the name "nonExistentActivity" for project "existingProject"
    When the user logs "6.5" hours on the activity
    Then an error message is printed

  Scenario: Fail to register time on a non-existent project
    Given there is a user with the name "huba"
    And user "huba" is logged in
    And there is no project with the name "nonExistentProject"
    When the user logs "6.5" hours on the activity
    Then an error message is printed

  Scenario: Fail to register invalid time
    Given there is a user with the name "huba"
    And user "huba" is logged in
    And there is a project with the name "existingProject"
    And there is an activity with the name "projectActivity" for project "existingProject"
    When the user logs "-2" hours on the activity
    Then an error message is printed
  
  Scenario: User logs invalid input as time
    Given there is a user with the name "huba"
    And user "huba" is logged in
    And there is a project with the name "existingProject"
    And there is an activity with the name "projectActivity" for project "existingProject"
    When the user logs "invalidInput" hours on the activity
    Then an error message is printed