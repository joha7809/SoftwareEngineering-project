package dtu.example.Service;

import java.util.ArrayList;

import dtu.example.Controller.command_returns.StatusMessage;
import dtu.example.model.AppState;
import dtu.example.model.User;

public class UserService {
    private final AppState state;

    public UserService(AppState state) {
        this.state = state;
    }

    public StatusMessage createUser(String userID) {
        if (userID.isBlank()){
            return new StatusMessage(false, "Error creating user. User ID cannot be empty.");
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

    public ArrayList<User> getAllAvailableUsers()
    {
        //ved ikke om et hashmap ville være bedre
        ArrayList<User> allAvailableUsers = new ArrayList<>();
        for (var entry : state.getUsers().entrySet()) {
            User user = entry.getValue();
            if (user.getJoinedActivities().size() < 20);{
                allAvailableUsers.add(user);
            }
        }
        return allAvailableUsers;
    }

    public boolean isAvailable(String userID) {
        User user = state.getUser(userID);
        if (user != null) {
            return user.getJoinedActivities().size() < 20;
        }
        return false;
    }
}
