@project
Feature:Create Project

    August wrote this feature

    Scenario: create a project with a name
        Given a user is logged in
        And there is no project with the name "newProject"
        When a project with the name "newProject" is created
        Then the project is added to the list of projects

    Scenario: fail in creating a project with a name
        Given a user is logged in
        And there is a project with the name "newProject"
        When a project with the name "newProject" is created
        Then an error message is printed

    Scenario: fail in creating a project with no name
        Given a user is logged in
        When a project with the name "" is created
        Then an error message is printed
    
    Scenario: Test create project and incremental id
        Given no projects exist
        When a project with the name "test" is created
        Then the project with the name "test" has id "25001"
    
    Scenario: Test creation of multiple project and id
        Given no projects exist
        When a project with the name "test" is created
        And a project with the name "test2" is created
        And a project with the name "test3" is created
        Then the project with the name "test" has id "25001"
        Then the project with the name "test2" has id "25002"
        Then the project with the name "test3" has id "25003"

