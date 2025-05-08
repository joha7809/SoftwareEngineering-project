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
        //init model
        ArrayList<User> users = new ArrayList<>(Arrays.asList(new User("admin")));
        ArrayList<Project> projects = new ArrayList<>();
        projects.add(new Project("test", "25001"));

        AppState state = new AppState();
        
        for (User user : users) {
            state.addUser(user);
        }
        for (Project project : projects) {
            state.addProject(project);
        }

        AppController controller = new AppController(state);
        UIController ui = new UIController(controller);

        ui.run();

        // once finished close
        System.exit(0);
    }
}