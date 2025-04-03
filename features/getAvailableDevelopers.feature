Feature: Get Available Developers

Kerem wrote this feature

  Scenario: Project lead wants to know which developers are available given an amount of work a given week.
    Given Users assigned to activities under a total of 37 hours a week
    When Duration needed for the activty is entered
    And Duration is updated
    Then Return all available users

  Scenario: Project lead fails to know which developers are available given an amount of work given week.
    Given Users assigned to activities over a total of 37 hours a week
    When Duration needed is entered
    And Duration is updated
    Then Return a string stating that no user is currently available

  Scenario: Project lead sends request for overwork
    Given Users assigned to activities over a total of 37 hours a week
    When Duration needed is entered
    And Duration is updated
    Then Return all available user, that want's to work over the given 37 hours a week
