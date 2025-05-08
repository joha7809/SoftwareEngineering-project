package dtu.example.Service;

import java.io.ObjectInputFilter.Status;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import dtu.example.Controller.command_returns.StatusMessage;

import dtu.example.model.AppState;
import dtu.example.model.Project;
import dtu.example.model.ProjectActivity;

public class ProjectService {
    private final AppState state;

    public ProjectService() {
        this.state = new AppState();
    }

    public ProjectService(AppState state) {
        this.state = state;
    }

    public StatusMessage createProject(String projectName) {
        if (projectName.equals("")) {
            return new StatusMessage(false, "Project name cannot be empty!");
        }

        if (projectName.matches("\\d{5}")) {
            return StatusMessage.error("Error: project name cannot be of same form as id: " + projectName);
        }

        if (state.getProjectByName(projectName) != null) {
            return new StatusMessage(false, "Project already exists!");
        }
        Integer projectCount = state.getProjects().size()+1;
        // Make the id
        int currentYear = Year.now().getValue() % 100; // Returns the current year in the format 25 for 2025
        String ID = String.format("%02d%03d", currentYear, projectCount); // Ensures current year is with 2 decimals and count is with 3.


        Project project = new Project(projectName, ID);
        state.addProject(project);
        return new StatusMessage(true, "Project with name " + projectName + " and id: " + ID + " was successfully created!");
    }

        public Project getProject(String projectInput) {
        if (projectInput.matches("\\d{5}")) {
            return state.getProjectById(projectInput);
        } else {
            return state.getProjectByName(projectInput);
        }
    }



}

