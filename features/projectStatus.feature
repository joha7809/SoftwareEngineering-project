Feature: Project status

  Adam wrote this feature

  Scenario: Project lead receives status report
    Given a project 22001 exist
    And "pete" is the project leader of the project
    And the project has activities
    And the project has timeRegistrations
    When "Get report" is typed
    Then "Time spent:" is printed
    And the sum of timeRegistrations is printed
    And "Time budgetted:" is printed
    And the sum of estimatedRemainingHours is printed

  Scenario: Project lead fails to receive status report
    Given a project 22001 exist
    And "pete" is the project leader of the project
    And the project has no activities
    When "Get report" is typed
    Then returns a string "No activities has been created yet"

  Scenario: Project lead receives status report without time registrations
    Given a project 22001 exist
    And "pete" is the project leader of the project
    And the project has activities
    And the project has no timeRegistrations
    When "Get report" is typed
    Then "No time has been registred for activities related to this project" is printed
    And "Time budgetted:" is printed
    And the sum of estimatedRemainingHours is printed