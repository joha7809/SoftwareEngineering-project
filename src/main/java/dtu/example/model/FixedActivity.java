package dtu.example.model;

//is the same as fixed activity. Maybe it should be renamed?
public class FixedActivity implements ActivityInterface {
    private String name;
    private String startDate;
    private String endDate;
    private Boolean isActive;
    private String activityDescription;
    

    @Override
    public String getName() {
        return null;
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
    public void setName(String name) {
        
    }
    @Override
    public void setStartDate(String startDate) {
        
    }

    @Override
    public void setActivtyDescription(String newDescription) {
        this.activityDescription = newDescription;
    }

    @Override
    public String getActivityDescription(){
        return this.activityDescription;
    }

    @Override

    public void setEndDate(String endDate){
        this.endDate = endDate;
    }
    


}