package com.revature;

import com.revature.Controller.UserController;
import com.revature.Repository.UserDAO;
import com.revature.Repository.UserInterface;
import com.revature.Service.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        try(Scanner scanner = new Scanner(System.in)){

            UserDAO userDAO = new UserDAO();
            UserService userService = new UserService(userDAO);
            UserController userController = new UserController(scanner, userService);
            Map<String, String> controlMap = new HashMap<>();
            controlMap.put("Continue Loop", "true");
            while(Boolean.parseBoolean(controlMap.get("Continue Loop"))){
                userController.promptUserForService(controlMap);
                if(controlMap.containsKey("User")){
                    System.out.println("Banking stuff for " + controlMap.get("User") + " can happen here! Press any key to continue.");
                    scanner.nextLine();
                }
            }
        }

    }

}
