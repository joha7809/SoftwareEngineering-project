
package dtu.example.model;
import java.util.List;


public class Project {
    private String projectID;
    private String projectName;
    private String description = null;
    private User projectLead;
    private List<WorkActivity> activities;

    //Constructor
    public Project(String projectID, String projectName)
    {
        this.projectID = projectID;
        this.projectName = projectName;
    }
    
    //Getters and Setters:
    public String getProjectID(){
        return projectID;
    }

    public String getProjectName(){
        return this.projectName;
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String desc){
        this.description = desc;
    }


    public void addActivity(WorkActivity activity){
        activities.add(activity);
    }

    public List getAllActivities(){
        return activities;
    }

    public void setProjectLead(User projectLead){
        this.projectLead = projectLead;
    }

    public User getProjectLead(){
        return this.projectLead;
    }

}
