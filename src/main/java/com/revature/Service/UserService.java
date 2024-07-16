package com.revature.Service;

import com.revature.Entity.User;
import com.revature.Exception.LoginFail;
import com.revature.Exception.RegistrationFail;
import com.revature.Repository.UserDAO;
import com.revature.Repository.UserInterface;

import java.util.List;

public class UserService {

    private UserInterface userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    private boolean checkUsernamePasswordLength(User newUserCredentials){
        boolean usernameValid = newUserCredentials.getUsername().length() <= 30;
        boolean passwordValid = newUserCredentials.getPassword().length() <= 30;
        return  usernameValid && passwordValid;
    }

    private boolean checkUsernameIsUnique(User newUserCredentials){
        boolean usernameUnique = true;
        List<User> users = userDAO.getAllUsers();
        for(User user : users){
            if(newUserCredentials.getUsername().equals(user.getUsername())){
                usernameUnique = false;
                break;
            }
        }
        return usernameUnique;
    }

    public User validateNewCredentials(User newUserCredentials) throws RegistrationFail {

        if(checkUsernamePasswordLength(newUserCredentials)){
            if(checkUsernameIsUnique((newUserCredentials))){
                return userDAO.createUser(newUserCredentials);
            }
        }

        throw new RegistrationFail("Either username exists or lengths are invalid.");

    }

    public User checkLoginCredentials(User credentials) throws LoginFail {
        for(User user : userDAO.getAllUsers()){
            boolean usernameMatches = user.getUsername().equals(credentials.getUsername());
            boolean passwordMatches = user.getPassword().equals(credentials.getPassword());
            if(usernameMatches && passwordMatches){
                return credentials;
            }
        }

        throw new LoginFail("Credentials are invalid. Please try again.");

    }

    public boolean checkUsername(String username){
        for(User user : userDAO.getAllUsers()){
            boolean bool = user.getUsername().equalsIgnoreCase(username);
            if (bool){
                return true;
            }
        }
        return false;
    }
}
