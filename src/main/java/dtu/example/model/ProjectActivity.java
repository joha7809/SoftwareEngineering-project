package dtu.example.model;

import java.util.ArrayList;

public class ProjectActivity {
    private String name;
    private String startWeek;
    private String endWeek;
    private String activityDescription;
    private float allottedTime;
    private Boolean isActive;
    private ArrayList<TimeRegistration> registrations = new ArrayList<>();
    private ArrayList<User> assignedUsers = new ArrayList<>();

    public ProjectActivity(String activityName){
        this.name = activityName;
        
    }

    public String getName() {
        return this.name;
    }

    public String getStartWeek() {
        return this.startWeek;
    }

    public String getEndWeek() {
        return this.endWeek;
    }

    public Boolean isActive() {
        return isActive;
    }

    public void setName(String name){
        this.name = name;
    }


    public void setStartWeek(String startDate) {
        this.startWeek = startDate;
    }

    public void setActivtyDescription(String newDescription) {
        this.activityDescription = newDescription;
    }

    public String getActivityDescription(){
        return this.activityDescription;
    }

    public void setEndWeek(String endDate){
        this.endWeek = endDate;
    }

    public void setAllottedTime(float allottedTime){
        this.allottedTime = allottedTime;
    }

    public float getAllottedTime(){
        return this.allottedTime;
    }

    public ArrayList<TimeRegistration> getRegistrations(){
        return registrations;
    }

    public float getTotalHoursSpent(){
        float sum = 0;
        for(TimeRegistration registration : registrations){
            sum += registration.getHours();
        }
        return sum;
    }

    public void addRegistration(TimeRegistration registration){
        registrations.add(registration);
    }

    public void addUser(User user) {
        this.assignedUsers.add(user);
    }

    @Override
    public String toString() {
        return "ProjectActivity{" +
                "name='" + name + '\'' +
                ", startWeek='" + startWeek + '\'' +
                ", endWeek='" + endWeek + '\'' +
                ", activityDescription='" + activityDescription + '\'' +
                ", allottedTime=" + allottedTime +
                ", registrations=" + registrations +
                ", assignedUsers=" + assignedUsers +
                '}';
    }
}
