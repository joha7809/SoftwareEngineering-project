Feature:Create Project

    Scenario: Create a Project with a name
        Given a user is logged in
        And there is no Project with the name "newProject"
        When a Project with name "newProject" is created
        Then the Project is added to the list of projects

    Scenario: Fail in creating a Project with a name
        Given a user is logged in
        And there is a Project with the name "newProject"
        When a Project with name "newProject" is created
        Then an error message is printed

    Scenario: Fail in creating a Project with no name
        Given a user is logged in
        When a Project with the name "" is created
        Then an error message is printed
        

