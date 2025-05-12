Feature: User Creation

  Scenario: Successfully create a user
    When a user with the ID "ABCD" is created
    Then the user with the ID "ABCD" exists

  Scenario: Fail to create a user with numbers in the ID
    When a user with the ID "AB12" is created
    Then the "Error creating user. User ID cannot contain numbers." error message is given

  Scenario: Fail to create a user with more than 4 characters
    When a user with the ID "ABCDE" is created
    Then the "Error creating user. User ID must be 4 characters long." error message is given

  Scenario: Fail to create a user with less than 4 characters
    When a user with the ID "ABC" is created
    Then the "Error creating user. User ID must be 4 characters long." error message is given

  Scenario: Fail to create a user with an empty ID
    When a user with the ID "" is created
    Then the "Error creating user. User ID cannot be empty." error message is given

  Scenario: Fail to create a user with an ID that already exists
    Given there is a user with the name "ABCD"
    When a user with the ID "ABCD" is created
    Then the "Error creating user. User with ID: ABCD already exists." error message is given