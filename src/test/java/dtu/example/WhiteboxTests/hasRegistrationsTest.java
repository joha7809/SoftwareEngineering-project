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

import dtu.example.model.Project;
import dtu.example.model.ProjectActivity;
import dtu.example.model.TimeRegistration;
import dtu.example.model.User;

//Adam
public class hasRegistrationsTest {
    // A unit test from our whitebox testing involving a method from the project class. The method returns a boolean depending on
    // whether the project has any registrations or not. 
    Project project;

    @BeforeEach
    public void setUp() {
        project = new Project("foo", "25001");
    }

    @Test
    public void test1() {
        // First test for project with activities as null
        boolean result = project.hasTimeRegistrations();
        // We expect a failure
        assert result == false;
    }

    @Test
    public void test2() {
        // Second test for project with empty activity list
        boolean result = project.hasTimeRegistrations();
        // We expect a failure
        assert result == false;
    }

    @Test
    public void test3() {
        // Test where we have a project with 3 activities, with no timeregistrations.
        // Create mocked activities
        ProjectActivity mockActivity1 = mock(ProjectActivity.class);

        ArrayList<ProjectActivity> activities = new ArrayList<>();
        activities.add(mockActivity1); // we technically add the same activity thrice however we control its logic in the mock
        activities.add(mockActivity1);
        activities.add(mockActivity1);

        when((mockActivity1.getRegistrations())).thenReturn(new ArrayList<TimeRegistration>());

        boolean result = project.hasTimeRegistrations();
        // We expect a failure
        assert result == false;

    }

    @Test
    public void test4() {
        // Test where we have a project with 3 activities, with one having a time registration

        // Create mocked activities
        ProjectActivity activity1 = new ProjectActivity("Activity 1");
        ProjectActivity activity2 = new ProjectActivity("Activity 2");
        ProjectActivity activity3 = new ProjectActivity("Activity 3");

        // Create time registrations
        TimeRegistration registration1 = mock(TimeRegistration.class);
        when(registration1.getHours()).thenReturn(5f);

        activity1.addRegistration(registration1);

        // Add the mocked activities to the project
        ArrayList<ProjectActivity> activities = new ArrayList<>();
        activities.add(activity1);
        activities.add(activity2);
        activities.add(activity3);



        project.addActivity(activity1);
        project.addActivity(activity2);
        project.addActivity(activity3);


        // Call the method under test
        boolean result = project.hasTimeRegistrations();

        // We expect a success
        assert result == true;
    }

}
