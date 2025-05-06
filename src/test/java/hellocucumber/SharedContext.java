package hellocucumber;

import dtu.example.Controller.AppManager;

public class SharedContext {
    private final AppManager appManager;

    public SharedContext() {
        this.appManager = new AppManager();
    }

    public AppManager getAppManager() {
        return appManager;
    }
}