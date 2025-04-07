package dtu.example.Controller;

import java.util.ArrayList;

import dtu.example.Model.OtherActivity;
import dtu.example.Model.Project;
import dtu.example.Model.User;

public class AppManager {
    private ArrayList<Project> projects;
    private ArrayList<User> users;
    private ArrayList<OtherActivity> otherActivities;

    public AppManager() {
        //Initialize arrayLists
        this.projects = new ArrayList<>();
        this.users = new ArrayList<>();
        this.otherActivities = new ArrayList<>(otherActivities);
    }

}
