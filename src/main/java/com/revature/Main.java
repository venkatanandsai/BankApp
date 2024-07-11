package com.revature;

import com.revature.Controller.AccountController;
import com.revature.Controller.UserController;
import com.revature.Repository.AccountDAO;
import com.revature.Repository.UserDAO;
import com.revature.Repository.UserInterface;
import com.revature.Service.AccountService;
import com.revature.Service.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        try(Scanner scanner = new Scanner(System.in)){

            UserDAO userDAO = new UserDAO();
            AccountDAO accountDAO = new AccountDAO();
            UserService userService = new UserService(userDAO);
            AccountService accountService = new AccountService(accountDAO);
            UserController userController = new UserController(scanner, userService);

            Map<String, String> controlMap1 = new HashMap<>();
            controlMap1.put("Continue Loop", "true");
            while(Boolean.parseBoolean(controlMap1.get("Continue Loop"))){
                userController.promptUserForService(controlMap1);
                if(controlMap1.containsKey("User") && Boolean.parseBoolean(controlMap1.get("Continue Loop"))){
                    System.out.println("Banking stuff for " + controlMap1.get("User") + " can happen here!");
                    Map<String, String> controlMap2 = new HashMap<>();
                    controlMap2.put("Continue Loop", "true");
                    while(Boolean.parseBoolean(controlMap2.get("Continue Loop"))){
                        AccountController accountController = new AccountController(scanner, accountService);
                        accountController.PromptForAccountService(controlMap2);
                    }

                }
            }
        }

    }

}
