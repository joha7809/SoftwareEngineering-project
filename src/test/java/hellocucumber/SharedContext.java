package hellocucumber;

//import java.sql.Time;

//import dtu.example.Controller.AppManager;
import dtu.example.Controller.AppController;
import dtu.example.Controller.command_returns.StatusMessage;
import dtu.example.model.Project;
import dtu.example.model.User;
import dtu.example.model.ProjectActivity;
import dtu.example.model.TimeRegistration;

//Johannes
public class SharedContext {
    private final AppController controller;
    private Project currentProject;
    private User currentUser;
    private StatusMessage result;
    private ProjectActivity projectactivity;
    private TimeRegistration registration;
    public String activityName;
    public String projectName;

    

    public SharedContext() {
        this.controller = new AppController();
    }

    public AppController getController() {
        return controller;
    }
    
    public Project getCurrentProject() {
        return currentProject;
    }

    public void setCurrentProject(Project project) {
        this.currentProject = project;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }
    public StatusMessage getResult() {
        return result;
    }
    public void setResult(StatusMessage result) {
        this.result = result;
    }

    public void setProjectActivity(ProjectActivity newprojectactivity){
        this.projectactivity = newprojectactivity;
    }

    public ProjectActivity getProjectActivity(){
        return projectactivity;
    }

    public TimeRegistration getTimeRegistration(){
        return registration;
    }

    public void setTimeRegistration(TimeRegistration registration){
        this.registration = registration;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    public String getProjectName() {
        return projectName;
    }

}