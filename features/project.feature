Feature:Create Project

    Scenario: Create a Project with a name
        When a Project is created with a name "newProject"
        Then the Projects name "newProject"

