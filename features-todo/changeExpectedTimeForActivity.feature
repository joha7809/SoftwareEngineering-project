Feature: Change expected time for activity

  Scenario: A project lead inputs the expected amount of work, in hours, a given activity is expected to take
    Given there is a project with the name "22001"
    And user "huba" is project lead on project "22001"
    And An activity with no estimate exists
    When The project lead inputs the expected time for the given activity 
    Then the estimated time for the activity is updated

  Scenario: A project lead updates the expected amount of work, in hours, a given activity is expected to take
    Given there is a project with the name "22001"
    And user "huba" is project lead on project "22001"
    And An activity with an estimate exists
    When The project lead inputs the expected time for the given activity
    Then the estimated time for the activity is updated
