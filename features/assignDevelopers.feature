Feature: Assign developers

  Scenario: Assign developers
    Given a developer is the project leader
    And another developer is available
    When the project leader assigns the other developer to activity
    Then the developer is assigned to the activity

  Scenario: assign a different developer
    Given a developer is the project leader
    And another developer is unavailable
    When the project leader assigns an other developer to activity
    Then the developer status is unchanged

  Scenario: failing to assign a developer
    Given a developer is the project leader
    And no developer is unavailable
    When the project leader assigns the developer to the activity
    Then the developer status is unchanged
