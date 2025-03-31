Feature: Change project activity description

  Scenario: Project leader changes activity description
    Given a project called ”22001”exists
    And user ”hga”is project leader for ”22001”
    And an activity exists
    And text field ”Description”is not empty
    When button ”Update information”is clicked
    Then description is set to text field ”Update information”text

  Scenario: Project leader fails to change activity description
    Given a project called ”22001”exists
    And user ”hga”is project leader for ”22001”
    And an activity exists
    And text field ”Description”is empty
    When button ”Update information”is clicked
    Then the TextfieldIsEmpty error message is printed

  Scenario: User updates activity description for leaderless project
    Given a project called ”22001”exists
    And project ”22001”has no project lead
    And text field ”Description”is not empty
    When button ”Update information”is clicked
    Then description is set to text field ”Update information”text

  Scenario: User who is not project lead fails to update project activity description
    Given a project called ”22001”exists
    And the project has a project lead
    And user ”hga”is not project leader for ”22001”
    And text field ”Description”is not empty
    When button ”Update information”is clicked
    Then the NotProjectLeaderError error message is printed