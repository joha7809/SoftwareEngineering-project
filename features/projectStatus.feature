Feature: Project status

  Scenario: Project lead recieves status report
    Given a project exist
    And a developer is the project leader Of the project
    And the project has activities
    When button ”Get report ”is clicked
    Then returns the status report for the activities

  Scenario: Project lead fails to receive status report
    Given a project exist and a developer is the project leader of the project
    And the project doesn’t have activities
    When button ”Get report ”is clicked
    Then returns a string stating an activity hasn’t been created yet
