package dtu.example.Service;

import java.util.ArrayList;
import java.util.HashMap;

import dtu.example.Controller.command_returns.StatusMessage;
import dtu.example.model.*;

//Nikolaj
public class UserService {
    private final AppState state;

    public UserService(AppState state) {
        this.state = state;
    }

	/***
	 * @pre no pre conditions
	 * @post User is successfully created if and only if:
	 *       - string is not empty AND
	 *       - string does not contain numbers AND
	 *       - no user with same name already exists AND
	 *       - user string.length <= 4
	 */
    public StatusMessage createUser(String userID) {
        if (userID.isBlank()){
            return new StatusMessage(false, "Error creating user. User ID cannot be empty.");
		}
        // ENFORCE THAT WE ONLY HAVE 4 INIITIALS AND NO NUMBERS
        if (userID.matches(".*\\d.*")) {
            return new StatusMessage(false, "Error creating user. User ID cannot contain numbers.");
        }
        if (userID.length() != 4) {
            return new StatusMessage(false, "Error creating user. User ID must be 4 characters long.");
        }

        if (state.getUser(userID) != null) {
            return new StatusMessage(false, "Error creating user. User with ID: " + userID + " already exists.");
        }
        User newUser = new User(userID);
        state.addUser(newUser);
        return new StatusMessage(true, "User created with ID: " + userID);
    }

    public StatusMessage setActiveUser(User user) {
        if (user == null){
            return StatusMessage.USER_NOT_FOUND;
        }

        state.setActiveUser(user);

        return StatusMessage.success(user.getUserID() + " succesfully logged in.");
    }

    public User getActiveUser() {
        return state.getActiveUser();
    }

    public User getUser(String userName) {
        return state.getUser(userName);
    }

    public HashMap<String, User> getAllUsers(){
        return state.getUsers();
        
    }
    
    public ArrayList<User> getAllAvailableUsers(String week) {
        ArrayList<User> allAvailableUsers = new ArrayList<>();
        for (User user : state.getUsers().values()) {
            if (state.getActiveUser() != null && user.getUserID().equals(state.getActiveUser().getUserID())) {
                continue; // Skip the active user
            }
            if (user.getJoinedActivities(week).size() < 20) {
                allAvailableUsers.add(user);
            }
        }
        return allAvailableUsers;
    }


    public void addUserToActivity(User user, ProjectActivity activity) {
        user.joinActivity(activity);
    }

    public StatusMessage logout(User user) {
        if (user == null) {
            return StatusMessage.USER_NOT_FOUND;
        }
        state.setActiveUser(null);
        return StatusMessage.success(user.getUserID() + " successfully logged out.");
    }
}
