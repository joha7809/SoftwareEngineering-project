package dtu.example.WhiteboxTests;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.Array;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import org.junit.jupiter.api.BeforeEach;

import dtu.example.Service.ProjectService;
import dtu.example.model.AppState;
import dtu.example.model.Project;
import dtu.example.model.ProjectActivity;
import dtu.example.model.TimeRegistration;
import dtu.example.model.User;


class getProjectStatusTests {
    // A unit test from white box testing, tests the method from ProjectService, that handles retrieving the report of a given project.

    Project mockProject;
    AppState mockState;
    ProjectService service;

    @BeforeEach
    public void setUp() {
        // Sets up mockito and the mock the state
        mockState = mock(AppState.class);
        service = new ProjectService(mockState);
        mockProject = mock(Project.class);
    }

    @Test
    public void test1() {
        // First test, tests for a project with value of null.
        var result = service.getProjectStatus(null);
        // We expect a failure
        assert result.success == false;
        assert result.message.contains("Project not found!");
    }

    @Test
    public void test2() {
        // Here a project exists, but no user is logged in.
        when(mockState.getActiveUser()).thenReturn(null);

        var result = service.getProjectStatus(mockProject);
        // We expect a failure
        assert result.success == false;
        assert result.message.contains("You are not logged in");
    }

    @Test
    public void test3() {
        // here a project exists, a user is logged in, a project leader exists, but the user logged in is not project lead.
        User mockUser = mock(User.class);
        User mockLeader = mock(User.class);
        when(mockState.getActiveUser()).thenReturn(mockUser);
        when(mockProject.getProjectLead()).thenReturn(mockLeader);

        var result = service.getProjectStatus(mockProject);
        // We expect a failure
        assert result.success == false;
        assert result.message.contains("You are not the project lead of this project!");
    }

    @Test
    public void test4() {
        // Project exists, user logged in is also project lead, but no activities exists for the given project.
        User mockUser = mock(User.class);
        when(mockState.getActiveUser()).thenReturn(mockUser);
        when(mockProject.getProjectLead()).thenReturn(mockUser);
        when(mockProject.hasActivities()).thenReturn(false);

        var result = service.getProjectStatus(mockProject);
        // We expect a failure
        assert result.success == false;
        assert result.message.contains("No activities has been created yet");
    }

    @Test
    public void test5() {
        // Project exists, user logged in is also project lead, and activities exists for the given project.
        User mockUser = mock(User.class);
        when(mockState.getActiveUser()).thenReturn(mockUser);
        when(mockProject.getProjectLead()).thenReturn(mockUser);
        when(mockProject.hasActivities()).thenReturn(true);
        when(mockProject.getProjectStatus()).thenReturn("Project status"); // Fake the output as we are not interrested in the string returned

        var result = service.getProjectStatus(mockProject);
        // We expect a success
        assert result.success == true;
        assert result.message.contains("Project status");
    }
    
}