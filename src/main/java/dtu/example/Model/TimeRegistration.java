package dtu.example.model;

//Kerem
public class TimeRegistration {
    private float hours;
    
    public float getHours() {
        return hours;
    }

    private User user;

    public TimeRegistration(float hours, User user) {
        this.hours = hours;
        this.user = user;
        
    }

    // @Override
    // public String toString() {
    //     return "TimeRegistration{" +
    //             "hours=" + hours +
    //             ", user=" + user.getUserID() +
    //             '}';
    // }

    

}
