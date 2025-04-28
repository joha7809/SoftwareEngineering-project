package dtu.example.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import dtu.example.Controller.AppManager;
import dtu.example.View.AppUi;
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

        AppManager manager = new AppManager(projects, users);
        AppUi ui = new AppUi(manager);

        ui.run();
    }

}