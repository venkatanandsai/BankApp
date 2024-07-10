package com.revature.Controller;

import com.revature.Entity.Account;
import com.revature.Exception.LoginFail;
import com.revature.Service.AccountService;

import java.util.Map;
import java.util.Scanner;

public class AccountController {

    private Scanner scanner;
    private AccountService accountService;

    public AccountController(Scanner scanner, AccountService accountService) {
        this.scanner = scanner;
        this.accountService = accountService;
    }

    public Account getAccountDetails(){
        String username;
        float amt;
        System.out.println("Enter the username associated with the account: ");
        username = scanner.nextLine();
        System.out.println("Enter initial amount to be deposited: ");
        amt = scanner.nextFloat();
        return new Account(0, amt, username);
    }

    public void register(){
        Account account = getAccountDetails();
        Account registeredAccount = accountService.registerAccount(account);
        System.out.println("New account number: " + registeredAccount.getAccnt_no());
    }

    public void AccountService(Map<String, String> controlMap){

        System.out.println("What would you like to do?");
        System.out.println("1. Open an Account");
        try{
            String action = scanner.nextLine();
            switch (action){
                case "1":
                    register();
                    break;
            }
        }
        catch (LoginFail e){
            System.out.println(e.getMessage());
        }
    }

}
