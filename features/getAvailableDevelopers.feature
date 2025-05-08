@developer

Feature: Get Available Developers
Kerem wrote this feature

 Feature: Get Available Developers
  As a project lead,
  I want to know which developers are available for a given week,
  So that I can assign them to activities effectively.

  Background:
    Given there is a user with the name "huba"
    And user "huba" is logged in
    And there is a project with the name "project"

  Scenario: All users are available for a given week
    Given there are "5" users in the system
    And each user has fewer than "20" activities assigned in week "5"
    When the user requests the list of available users for week "5"
    Then the user receives a list of "5" available users

  Scenario: Some users are available for a given week
    Given there are "5" users in the system
    And "2" users have "20" or more activities assigned in week "5"
    When the user requests the list of available users for week "5"
    Then the user receives a list of "3" available users

  Scenario: No users are available for a given week
    Given there are "5" users in the system
    And each user has "20" or more activities assigned in week "5"
    When the user requests the list of available users for week "5"
    Then the user receives an empty list of available users

  Scenario: user with exactly 19 activities is available
    Given there is a user with the name "dev1"
    And user "dev1" has "19" activities assigned in week "12"
    When the user requests the list of available users for week "12"
    Then user "dev1" is included in the list of available users

  Scenario: user with exactly 20 activities is unavailable
    Given there is a user with the name "dev2"
    And user "dev2" has "20" activities assigned in week "12"
    When the user requests the list of available users for week "12"
    Then user "dev2" is not included in the list of available users
