package dtu.example.model;

public class ProjectActivity implements ActivityInterface {
    private String name;
    private String startDate;
    private String activityDescription;

    public ProjectActivity(String activityName){
        this.name = activityName;
        
    }

    @Override
    public String getName() {
        return this.name;
    }
    @Override
    public String getStartDate() {
        return null;
    }
    @Override
    public String getEndDate() {
        return null;
    }
    @Override
    public Boolean isActive() {
        return null;
    }

    @Override 
    public void setName(String name){
        this.name = name;
    }

    @Override
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    @Override
    public void setActivtyDescription(String newDescription) {
        this.activityDescription = newDescription;
    }

    @Override
    public String getActivityDescription(){
        return this.activityDescription;
    }
    


}
