Feature: Register used time

  Kerem wrote this

  Scenario: A user registers used time on an activity
    Given there is a user with the name "huba"
    And user "huba" is logged in
    And there is a project with the name "existingProject"
    And there is an activity with the name "projectActivity" for project "existingProject"
    When the user logs their time on the activity 
    Then the time used is registered on the activity
