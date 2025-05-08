package dtu.example.model;

//is the same as fixed activity. Maybe it should be renamed?
public class FixedActivity implements ActivityInterface {
    private String name;
    private String startDate;
    private String endDate;
    private Boolean isActive;
    private String activityDescription;
    
    public FixedActivity(String startDate, String endDate, String type){
        name = type;
        this.startDate = startDate;
        this.endDate = endDate;
    }


    @Override
    public String getName() {
        return name;
    }
    @Override
    public String getStartDate() {
        return startDate;
    }
    @Override
    public String getEndDate() {
        return endDate;
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