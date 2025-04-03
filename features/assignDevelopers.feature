Feature: Assign developers

  Adam wrote this feature 
  
  Scenario: Assign developers
    Given "Pete" is the project leader
    And "Bob" is available
    When project leader assigns "Bob" to activity
    Then "Bob" is assigned to the activity

  Scenario: assign a different developer
    Given "Pete" is the project leader
    And "Bob" is unavailable
    When project leader assigns "Bob" to an activity
    Then "Bob" status is unchanged

  Scenario: failing to assign a developer
    Given "Pete" is the project leader
    And "Bob" is unavailable
    When project leader assigns "Bob" to the activity
    Then "Bob" status is unchanged
