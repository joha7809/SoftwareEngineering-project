Feature: Create Project Activity

  Scenario: Project leader creates activity for project
    Given a project called ”22001”exists
    And user ”hga”is project leader for ”22001”
    When button ”Create Project Activity”is clicked
    And the activity has a name
    Then a new activity is added to the project

  Scenario: User creates activity for leaderless project
    Given a project called ”22001”exists
    And project ”22001”has no project lead
    When button ”Create Project Activity”is clicked
    And the activity has a name
    Then a new activity is added to the project

  Scenario: User fails to create activity for project
    Given a user called ”hga”exists
    And a project called ”22001”exists
    When button ”Create Activity”is clicked
    And Activity has no name
    Then the NoActivityNameError error message is printed

  Scenario: User who is not project lead fails to create activity
    Given a project called ”22001”exists
    And the project has a project lead
    And user ”hga”is not project leader for ”22001”
    When button ”Create Activity”is clicked
    Then the NotProjectLeaderError error message is printed
