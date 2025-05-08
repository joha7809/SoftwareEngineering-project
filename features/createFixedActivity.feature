@fixedactivity
Feature: Create fixed activity

  Adam wrote this feature

  Scenario: User creates fixed activity
    Given there is a user with the name "hga"
    When the user types the command "createFixedActivity" and arguments timeStart "03022025" timeEnd "04022025" type "sick"
    Then a fixed activity is created with the start date "03022025", end date "04022025" and type "sick"
    
  Scenario: User enters invalid timeStart
    Given there is a user with the name "hga"
    When the user types the command "createFixedActivity" and arguments timeStart "Banan" timeEnd "04022025" type "sick"
    Then the system displays an error message: "The entered data are invalid"

  Scenario: User enters invalid timeEnd
    Given there is a user with the name "hga"
    When the user types the command "createFixedActivity" and arguments timeStart "03022025" timeEnd "Banan" type "sick"
    Then the system displays an error message: "The entered data are invalid"

  Scenario: User enters invalid type
    Given there is a user with the name "hga"
    When the user types the command "createFixedActivity" and arguments timeStart "03022025" timeEnd "04022025" type "Banan"
    Then the system displays an error message: "The entered data are invalid"
