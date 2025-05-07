package dtu.example.model;

import java.util.Date;

public class TimeRegistration {
    private Date date;
    private float hours;
    private User user;

    public TimeRegistration(Date date, float hours, User user) {
        this.date = date;
        this.hours = hours;
        this.user = user;
        
    }

}
