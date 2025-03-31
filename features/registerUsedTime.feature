Feature: Register used time

  Scenario: A user registers used time on an activity
    Given an user has used a certain time on activity
    When user inputs used time on project
    Then the time used is registered on the activity

  Scenario: A user fails to register used time on an activity
    Given an user has not used a certain time on activity
    When user inputs used time on project
    Then the time used is registered on the activity