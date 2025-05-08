package dtu.example.model;

public class FixedActivity {
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


    public String getName() {
        return name;
    }
    public String getStartDate() {
        return startDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public Boolean isActive() {
        return null;
    }

    public void setName(String name) {
        
    }
    public void setStartDate(String startDate) {
        
    }

    public void setActivtyDescription(String newDescription) {
        this.activityDescription = newDescription;
    }

    public String getActivityDescription(){
        return this.activityDescription;
    }

    public void setEndDate(String endDate){
        this.endDate = endDate;
    }
    


}