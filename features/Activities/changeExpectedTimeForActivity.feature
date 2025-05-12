Feature: Change expected time for activity
Kerem

  Scenario: A project lead updates the expected time, in hours, for a activity with no expected time
    Given there is a project with the name "Ikea"
    And there is a user with the name "huba"
    And user "huba" is project lead on project "Ikea"
    And there is an activity with the name "test" for project "Ikea"
    And the activity with the name "test" has no estimated time
    When The project lead inputs the expected time, of "5" hours, for the activity "test" 
    Then the estimated time for the activity "test" is updated

  Scenario: A project lead updates the expected time, in hours, for a activity with an expected time
    Given there is a project with the name "Ikea"
    And there is a user with the name "huba"
    And user "huba" is project lead on project "Ikea"
    And there is an activity with the name "test" for project "Ikea"
    And the activity with the name "test" has an estimated time
    When The project lead inputs the expected time, of "5" hours, for the activity "test"
    Then the estimated time for the activity "test" is updated

Scenario: A project lead fails to update the expected time
    Given there is a project with the name "Ikea"
    And there is a user with the name "huba"
    And user "huba" is project lead on project "Ikea"
    And there is an activity with the name "test" for project "Ikea"
    And the activity with the name "test" has no estimated time
    When The project lead inputs the expected time, of "-2" hours, for the activity "test"
    Then an error message is printed
