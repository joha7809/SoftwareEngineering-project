package hellocucumber;

import dtu.example.Controller.AppManager;
import dtu.example.Controller.command_returns.StatusMessage;
import dtu.example.model.Project;
import dtu.example.model.User;

public class SharedContext {
    private final AppManager appManager;
    private Project currentProject;
    private User currentUser;
    private StatusMessage result;

    public SharedContext() {
        this.appManager = new AppManager();
    }

    public AppManager getAppManager() {
        return appManager;
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
}