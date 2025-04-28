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