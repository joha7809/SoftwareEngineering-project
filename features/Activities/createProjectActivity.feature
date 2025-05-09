@projectActivity
Feature: Create Project Activity


  Scenario: Project leader creates activity for project
    Given there is a project with the name "existingProject"
    And there is a user with the name "hgaa"
    And user "hgaa" is logged in
    And user "hgaa" is project lead on project "existingProject"
    When the user creates a project activity with the name "BuildABud"
    Then a new activity is added to the project with the name

  Scenario: User creates activity for leader less project
    Given there is a project with the name "existingProject"
    And there is a user with the name "hgaa"
    And user "hgaa" is logged in
    And project "existingProject" has no project lead
    When the user creates a project activity with the name "BuildABud"
    Then a new activity is added to the project with the name

  Scenario: project leader fails to create activity for project
    Given there is a project with the name "existingProject"
    And there is a user with the name "hgaa"
    And user "hgaa" is logged in
    And user "hgaa" is project lead on project "existingProject"
    When the user creates a project activity with the name ""
    Then the "Error: Activity name cannot be empty!" error message is given

  Scenario: User who is not project leader fails to create activity
    Given there is a project with the name "existingProject"
    And there is a user with the name "hgaa"
    And user "hgaa" is logged in
    And the project has a project leader for "existingProject"
    And user "hgaa" is not project leader for "existingProject"
    When the user creates a project activity with the name "BuildABud"
    Then the "Error: Logged in user not project leader." error message is given

  Scenario: User who is not logged in fails to create activity
    Given there is a project with the name "existingProject"
    And there is a user with the name "hgaa"
    And no user is logged in
    And the project has a project leader for "existingProject"
    When the user creates a project activity with the name "BuildABud"
    Then the "Error: No user is logged in." error message is given

