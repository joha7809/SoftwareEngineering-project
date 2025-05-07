package dtu.example.model;

public interface ActivityInterface {
    String getName();
    String getStartDate();
    String getEndDate();
    String getActivityDescription();
    Boolean isActive();
    void setName(String name);
    void setStartDate(String startDate);
    void setActivtyDescription(String activityDescription);

}
