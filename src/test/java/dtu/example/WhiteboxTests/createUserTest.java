package dtu.example.WhiteboxTests;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import dtu.example.Service.UserService;
import dtu.example.model.AppState;
import dtu.example.model.User;

//Nikolaj
public class createUserTest {
    // This is a test from our white box tests, regarding the createUser method from userService.
    private AppState mockState;
    private UserService service;
    private String input;

    @BeforeEach
    public void setUp() {
        // Sets up mockito and the mock the state
        mockState = mock(AppState.class);
        service = new UserService(mockState);
    }

    @Test
    public void test1() {
        // First we test for the case where the username is empty
        input = ""; 
        var result = service.createUser(input);

        // We expect a failure
        assert result.success == false;
        assert result.message.contains("Error creating user. User ID cannot be empty");
    }

    @Test
    public void test2() {
        input = "f00bar"; // name cant contain numbers (length is too long but numbers are checked first)
        var result = service.createUser(input);
        // We expect a failure
        assert result.success == false;
        assert result.message.contains("Error creating user. User ID cannot contain numbers");
    }

    @Test
    public void test3() {
        input = "foo";
        var result = service.createUser(input);
        // We expect a failure
        assert result.success == false;
        assert result.message.contains("Error creating user. User ID must be 4 characters long");
    }

    @Test
    public void test4() {
        input = "f00b"; // name cant contain numbers
        var result = service.createUser(input);
        // We expect a failure
        assert result.success == false;
        assert result.message.contains("Error creating user. User ID cannot contain numbers");
    }

    @Test
    public void test5() {
        // Test with valid name, but exsisting user.
        input = "foob"; // Valid name
        when(mockState.getUser(input)).thenReturn(new User("foob")); // Mocking that there already exists a user

        var result = service.createUser(input);
        // We expect a failure
        assert result.success == false;
        assert result.message.contains("Error creating user. User with ID: " + input + " already exists.");
    }

    @Test
    public void test6() {
        // Test with valid name, and no other conditions
        input = "foob"; // Valid name
        //when(mockState.getUser(input)).thenReturn(null); // Mocking that there does not exist a user
        var result = service.createUser(input);
        // We expect a success
        assert result.success == true;
        assert result.message.contains("User created with ID: " + input);
        // Verify that the user is added to the state
        verify(mockState, times(1)).addUser(argThat(user -> user.getUserID().equals(input)));
    }
}
