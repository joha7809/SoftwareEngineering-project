package dtu.example.model;

//is the same as fixed activity. Maybe it should be renamed?
public class OtherActivity implements ActivityInterface {

    private String activityName;
    private String description;
    private String startDate;
    private String endDate;
    private Boolean isActive;

    @Override
    public String getDescription() {
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

}