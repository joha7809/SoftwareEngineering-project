package dtu.example.model;

public interface ActivityInterface {
    String getName();
    String getStartDate();
    String getEndDate();
    Boolean isActive();
    void setName(String name);
    void setStartDate(String startDate);
}
