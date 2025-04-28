
package dtu.example.Model;
import java.util.List;


public class Project {
    private String projectID;
    private User projectLead;
    private List<WorkActivity> activities;

    //Constructor
    public Project(String projectID)
    {
        this.projectID = projectID;
    }
    
    //Getters and Setters:
    public String getProjectID(){
        return projectID;
    }

    public void addActivity(WorkActivity activity){
        activities.add(activity);
    }

    public List getAllActivities(){
        return activities;
    }

    //public Activity getActivity(String name){
      //  for (Activity activity : activities){
        //    if (activity.getActivityName().equals(name)){
              //  return activity;
          //  } else 
            //}

        //}

    //}

    // public void removeActivity(String activityName){
    //     for (WorkActivity activity : activities){
    //         if (activity.getActivityName().equals(activityName)){
    //             activities.remove(activity);
    //         }

    //     }
    // }

    public void setProjectLead(User projectLead){
        this.projectLead = projectLead;
    }

    public User getProjectLead(){
        return this.projectLead;
    }

}
