Feature: Change start date of activity

  Scenario: Employee changes start date
    Given a user is logged in
    And there is a project with the name "existingProject"
    And there is an activity with the name "existingActivity" for project "existingProject"
    When user updates the start date
    Then the start date is updated
