Feature: Create fixed activity

  Adam wrote this feature

  Scenario: User creates fixed activity
    Given Given user "pete" exists
    When user enters the timeStart 03022025 for the fixed activity
    And user enters the timeEnd 04022025 for the fixed activity
    And user enters the type "sick" for the fixed activity
    Then a fixed activity is created with the user "Pete", start date "03022025", end date "04022025" and type "sick"
    
  Scenario: User fails to create a fixed activity
    Given Given user "pete" exists
    When user enters the timeStart "Banan" for the fixed activity
    And user enters the timeEnd 04022025 for the fixed activity
    And user enters the type "sick" for the fixed activity
    Then a string code will state "The entered data are invalid"

  Scenario: User fails to create a fixed activity
    Given Given user "pete" exists
    When user enters the timeStart 03022025 for the fixed activity
    And user enters the timeEnd "Banan" for the fixed activity
    And user enters the type "sick" for the fixed activity
    Then a string code will state "The entered data are invalid"

  Scenario: User fails to create a fixed activity
    Given Given user "pete" exists
    When user enters the timeStart 03022025 for the fixed activity
    And user enters the timeEnd 04022025 for the fixed activity
    And user enters the type "-" for the fixed activity
    Then a string code will state "The entered data are invalid"
