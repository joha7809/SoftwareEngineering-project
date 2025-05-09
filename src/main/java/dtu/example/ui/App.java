package dtu.example.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import dtu.example.Controller.AppController;
import dtu.example.View.UIController;
import dtu.example.model.AppState;
import dtu.example.model.Project;
import dtu.example.model.ProjectActivity;
import dtu.example.model.TimeRegistration;
import dtu.example.model.User;
 
/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        
        AppState state = setup();
        AppController controller = new AppController(state);
        UIController ui = new UIController(controller);

        ui.run();

        // once finished close
        System.exit(0);
    }

    private static AppState setup() {
    // Initialize users
    ArrayList<User> users = new ArrayList<>(Arrays.asList(
        new User("admin"),
        new User("user1"),
        new User("user2"),
        new User("user3"),
        new User("user4")
    ));

    // Initialize projects
    ArrayList<Project> projects = new ArrayList<>(Arrays.asList(
        new Project("Project Alpha", "10001"),
        new Project("Project Beta", "10002"),
        new Project("Project Gamma", "10003")
    ));

    // Initialize activities for each project
    for (Project project : projects) {
        project.addActivity(new ProjectActivity("Design Phase"));
        project.addActivity(new ProjectActivity("Development Phase"));
        project.addActivity(new ProjectActivity("Testing Phase"));
    }

    // Initialize AppState
    AppState state = new AppState();

    // Add users to AppState
    for (User user : users) {
        state.addUser(user);
    }

    // Add projects to AppState
    for (Project project : projects) {
        state.addProject(project);
    }

    // Assign project leads
    projects.get(0).setProjectLead(users.get(1)); // user1 is the lead for Project Alpha
    projects.get(1).setProjectLead(users.get(2)); // user2 is the lead for Project Beta
    projects.get(2).setProjectLead(users.get(3)); // user3 is the lead for Project Gamma

    // Assign users to activities and register time
    for (Project project : projects) {
        for (ProjectActivity activity : project.getAllActivities()) {
            // Assign users to activities
            activity.addUser(users.get(1));
            activity.addUser(users.get(2));

            // Register time for users
            activity.addRegistration(new TimeRegistration(5.0f, users.get(1))); // 5 hours by user1
            activity.addRegistration(new TimeRegistration(3.5f, users.get(2))); // 3.5 hours by user2
        }
    }

    return state;
}
}