package com.revature.Controller;

import com.revature.Entity.User;
import com.revature.Exception.LoginFail;
import com.revature.Exception.RegistrationFail;
import com.revature.Exception.UserSQLException;
import com.revature.Service.UserService;

import java.util.Map;
import java.util.Scanner;

public class UserController {

    private Scanner scanner;
    private UserService userService;

    public UserController(Scanner scanner, UserService userService) {
        this.scanner = scanner;
        this.userService = userService;
    }

    public User getUserCredentials(){
        String newUsername;
        String newPassword;
        System.out.println("Username: ");
        newUsername = scanner.nextLine();
        System.out.println("Password: ");
        newPassword = scanner.nextLine();
        return new User(newUsername, newPassword);
    }

    public void register(){
        User newCredentials = getUserCredentials();
        User newUser = userService.validateNewCredentials(newCredentials);
        String newUsername = newUser.getUsername();
        System.out.println("New user created: " + newUsername);
    }

    public User login(){
        return userService.checkLoginCredentials(getUserCredentials());
    }

    public void promptUserForService(Map<String, String> controlMap){

        System.out.println("What would you like to do?");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("q: Quit");
        try{
            String userAction = scanner.nextLine();
            switch (userAction){
                case "1":
                    register();
                    break;
                case "2":
                    controlMap.put("User", login().getUsername());
                    break;
                case "q":
                    System.out.println("Thank you!, visit again!");
                    controlMap.put("Continue Loop", "false");
            }
        }
        catch (LoginFail | RegistrationFail | UserSQLException e){
            System.out.println(e.getMessage());
        }

    }
}
