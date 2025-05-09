
package dtu.example.model;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;


public class Project {
    private String projectID;
    private String projectName;
    private String description = null;
    private User projectLead;

    private List<ProjectActivity> activities = new ArrayList<>();

    //Constructor
    public Project(String projectName, String ID)
    {   
        this.projectName = projectName;
        this.projectID = ID; // of form 25001
        
    }
    
    //Getters and Setters:
    public String getProjectID() {
        return projectID;
    }

    public String getProjectName() {
        return this.projectName;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String desc) {
        this.description = desc;
    }

    public void addActivity(ProjectActivity activity) {
        activities.add(activity);
    }

    public List<ProjectActivity> getAllActivities(){
        return activities;
    }

    //Adam wrote this. Determines whether the project has time registrations(it does exactly what it says on the tin)
    //Adam made an edit. Plz don't mess it up
    public boolean hasTimeRegistrations(){
        if(hasActivities()){
            //if theyre undeclared, it's false, if they exist and hold a value, theyre true, otherwise if theyre declared, theyre false
        for(int i = 0; i < activities.size(); i++){
            //if(activities.get(i).getRegistrations() == null){
            if(activities.get(i) == null){
                return false;
            }else if(!activities.get(i).getRegistrations().isEmpty()){
                return true;
            }
        }
        }
        
        return false;
    }

    //Adam wrote this. Determines whether the project has activities(it does exactly what it says on the tin)
    public boolean hasActivities(){
        if(activities == null){
            return false;
        } else{
            if(activities.isEmpty()){
                return false;
            } else{
                return true;
            }
        }
    }

    public void setProjectLead(User projectLead) {
        this.projectLead = projectLead;
    }

    public User getProjectLead(){
        return this.projectLead;
    }

    public ProjectActivity getActivity(String activityName) {
        for (ProjectActivity activity : activities) {
            if (activity.getName().equals(activityName)){
                return activity;
            }
        }
        return null;
    }


    //Adam wrote this. Returns the status of all activities in the project
    public String getProjectStatus(){
        StringBuilder sb = new StringBuilder();
        sb.append("Projektets navn: " + projectName + "\nProjektets løbenummer: " + projectID);
        for (ProjectActivity activity : activities){
            sb.append("\nAktivitetens navn:" + activity.getName() + "\nBudgeteret tid: " + activity.getAllottedTime() + "\nAnvendt tid: " + activity.getTotalHoursSpent() + "\n");
        }
        return sb.toString();
    }

    public String toString(){
        return "Project{" +
                "projectID='" + projectID + '\'' +
                ", projectName='" + projectName + '\'' +
                ", description='" + description + '\'' +
                ", projectLead=" + projectLead +
                ", activities=" + activities +
                '}';
    }

}
