@project
Feature:Create Project

    August wrote this feature

    Scenario: create a project with a name
        Given there is no project with the name "newProject"
        When a project with the name "newProject" is created
        Then the project is added to the list of projects

    Scenario: fail in creating a project with a name
        Given there is a project with the name "newProject"
        When a project with the name "newProject" is created
        Then an error message is printed

    Scenario: fail in creating a project with no name
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

    Scenario: Project is created with id-format instead of name
        Given no projects exist
        When a project with the name "25001" is created
        Then the "Error: project name cannot be of same form as id" error message is given

    
    Scenario: Project name is edited
        Given there is a project with the name "test"
        When the project with the name "test" is edited to "test2"
        Then the project with the name "test2" exists
        And the project with the name "test" does not exist

    Scenario: Project description is edited
        Given there is a project with the name "test"
        When the project with the name "test" is edited to "test2" with description "test description"
        Then the project with the name "test2" exists
        And the project with the name "test" does not exist
        And the project with the name "test2" has description "test description"