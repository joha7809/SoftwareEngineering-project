package dtu.example.model;

import java.util.ArrayList;
//Nikolaj
public class ProjectActivity {
	private String name;
	private String startWeek;
	private String endWeek;
	private String activityDescription;
	private float allottedTime;
	private ArrayList<TimeRegistration> registrations = new ArrayList<>();
	private ArrayList<User> assignedUsers = new ArrayList<>();
//Nikolaj
	public ProjectActivity(String activityName) {
		this.name = activityName;

	}
//Nikolaj
	public String getName() {
		return this.name;
	}
//Nikolaj
	public String getStartWeek() {
		return this.startWeek;
	}
//Nikolaj
	public String getEndWeek() {
		return this.endWeek;
	}
//Nikolaj
	public void setName(String name) {
		this.name = name;
	}

	public void setStartWeek(String startDate) {
		this.startWeek = startDate;
	}

	public void setActivtyDescription(String newDescription) {
		this.activityDescription = newDescription;
	}
//Nikolah
	public String getActivityDescription() {
		return this.activityDescription;
	}
//Nikolaj
	public void setEndWeek(String endDate) {
		this.endWeek = endDate;
	}
//Nikolaj
	public void setAllottedTime(float allottedTime) {
		this.allottedTime = allottedTime;
	}
//Nikolaj
	public float getAllottedTime() {
		return this.allottedTime;
	}

	public ArrayList<TimeRegistration> getRegistrations() {
		return registrations;
	}

	public float getTotalHoursSpent() {
		float sum = 0;
		for (TimeRegistration registration : registrations) {
			sum += registration.getHours();
		}
		return sum;
	}

	public void addRegistration(TimeRegistration registration) {
		registrations.add(registration);
	}
//Nikolaj
	public void addUser(User user) {
		this.assignedUsers.add(user);
	}

	@Override
	public String toString() {
		return "name='" + name + '\n' +
				" startWeek='" + startWeek + '\n' +
				" endWeek='" + endWeek + '\n' +
				" activityDescription='" + activityDescription + '\n' +
				" allottedTime=" + allottedTime + '\n' +
				" assignedUsers=" + assignedUsers;
	}
}
