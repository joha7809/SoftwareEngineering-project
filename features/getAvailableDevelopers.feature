Feature: Get Available Developers

  Scenario: Project lead wants to know which developers are available given an amount of work a given week.
    Given Users not assigned to activities exists
    When Duration needed is entered
    And Button is pressed
    Then Return all available users

  Scenario: Project lead fails to know which developers are available given an amount of work given week.
    Given Users assigned to activities exists
    When Duration needed is entered
    And Button is pressed
    Then Return a string stating that no user is currently available
