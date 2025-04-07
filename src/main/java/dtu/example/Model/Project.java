
package dtu.example.Model;
import java.util.List;


public class Project {

    private String projectname;
    private User projectLead;
    private List<Activity> activities;

    //Constructor
    public Project(String name)
    {
        this.projectname = name;
    }
    
    //Getters and Setters:
    public String getProjectName(){
        return projectname;
    }
    public void setProjectName(String name){
        this.projectname = name;
    }

    public void addActivity(Activity activity){
        activities.add(activity);
    }

    public List getAllActivities(){
        return activities;
    }

    public Activity getActivity(String name){
        for (Activity activity : activities){
            if (activity.getActivityName().equals(name)){
                return activity;
            } else {
            placeholder.errormessage("nullActivity"); 
            }

        }

    }

    public void removeActivity(String activityName){
        for (Activity activity : activities){
            if (activity.getActivityName().equals(activityName)){
                activities.remove(activity);
            }

        }
    }

    public void setProjectLead(User projectLead){
        this.projectLead = projectLead;
    }

    public User getProjectLead(){
        return this.projectLead;
    }

}
