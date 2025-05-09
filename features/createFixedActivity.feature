@fixedactivity
Feature: Create fixed activity

  Adam wrote this feature

  Scenario: User creates fixed activity
    Given there is a user with the name "hgaa"
    When the user creates a fixed activity with timeStart "03022025" timeEnd "04022025" type "sick"
    Then a fixed activity is created with the start date "03022025", end date "04022025" and type "sick"
    
  Scenario: User enters invalid timeStart
    Given there is a user with the name "hgaa"
    When the user creates a fixed activity with timeStart "Banan" timeEnd "04022025" type "sick"
    Then the "Error: Start date is not a valid date!" error message is given

  Scenario: User enters invalid timeEnd
    Given there is a user with the name "hgaa"
    When the user creates a fixed activity with timeStart "03022025" timeEnd "Banan" type "sick" 
    Then the "Error: End date is not a valid date!" error message is given

  Scenario: User enters two invalid dates
    Given there is a user with the name "hgaa"
    When the user creates a fixed activity with timeStart "03022025231" timeEnd "Banan" type "sick" 
    Then the "Error: Neither dates are valid dates!" error message is given



