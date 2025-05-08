Feature: Get Available Developers

Kerem wrote this feature

  Scenario: Project lead wants to know which developers are available given an amount of work a given week.
    Given a user is logged in
    And there are multiple users which is available
    When the user request a list of available users
    Then the user gets all a list of all available users

  Scenario: Project lead fails to know which developers are available given an amount of work given week.
    Given a user is logged in
    And there are no users available
    When the user request a list of available users
    Then the user gets all a list of all available users

  #Scenario: Project lead sends request for overwork
   #Given Users assigned to activities over a total of 20 activities a week
  
    #Then Return all available user, that wants to work over the given 20 activities a week
