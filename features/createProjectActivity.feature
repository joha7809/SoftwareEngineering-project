Feature: Create Project Activity

  Scenario: Project leader creates activity for project
    Given a project called ”22001” exists
    And the user ”hga” is project leader for ”22001”
    When the project leader create a project activity
    And gives the activity a name
    Then a new activity is added to the project with the name

  Scenario: User creates activity for leaderless project
    Given a project called ”22001” exists
    And project ”22001” has no project lead
    When the user creates a project activity
    And gives the activity a name
    Then a new activity is added to the project with the name

  Scenario: project lead fails to create activity for project
    Given a project called ”22001” exists
    And the user ”hga” is project leader for ”22001”
    When the project lead creates a activity
    And does not give the activity a name
    Then a error message is printed

  Scenario: User who is not project lead fails to create activity
    Given a project called ”22001” exists
    And the project has a project lead
    And user ”hga” is not project leader for ”22001”
    When the user creates a project activity
    And gives the activity a name
    Then a error message is printed
