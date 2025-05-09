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
            // Check if the activity is within the specified week range
            // We need to make sure that we handle cases where activity misses a start or end week
            // TODO: Handle cases where activity misses a start or end week

            if (Integer.valueOf(activity.getStartWeek())<=Integer.valueOf(week) && Integer.valueOf(week)<=Integer.valueOf(activity.getEndWeek())) {
                activitiesInWeek.add(activity);
            }

        }
        return activitiesInWeek;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID='" + userID + '\'' +'}';
    }
}
