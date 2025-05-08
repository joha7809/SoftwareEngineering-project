package dtu.example.model;

import java.util.ArrayList;

public class User {

    private String userID;

    private ArrayList<ProjectActivity> joinedActivities = new ArrayList<>();

    //User Constructor
    public User(String userID){
        this.userID = userID;

    }

    //Getters:
    public String getUserID(){
        return this.userID;
    }
    //Nikolaj
    public void joinActivity(ProjectActivity activity){
        joinedActivities.add(activity);
    }
    
    public ArrayList<ProjectActivity> getJoinedActivities(){
        return joinedActivities;
    }

    public ArrayList<ProjectActivity> getJoinedActivities(String week) {
        ArrayList<ProjectActivity> activitiesInWeek = new ArrayList<>();
        for (ProjectActivity activity : joinedActivities) {
            if (activity.getStartWeek().equals(week)) {
                activitiesInWeek.add(activity);
            }
        }
        return activitiesInWeek;
    }
}
