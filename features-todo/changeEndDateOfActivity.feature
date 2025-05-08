Feature: Change end date of activity

  Nikolaj wrote this feature

  Scenario: Employee changes end date
    Given a user is logged in
    And there is a project with the name "existingProject"
    And there is an activity with the name "existingActivity" for project "existingProject"
    When the user updates the end date to "02022025"
    Then the end date is updated
