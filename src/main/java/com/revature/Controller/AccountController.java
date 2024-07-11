package com.revature.Controller;

import com.revature.Entity.Account;
import com.revature.Exception.InvalidEntry;
import com.revature.Exception.LoginFail;
import com.revature.Service.AccountService;

import java.util.List;
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
        String amount;
        System.out.println("Enter the username associated with the account: ");
        username = scanner.nextLine();
        System.out.println("Enter initial amount to be deposited: ");
        amount = scanner.nextLine();
        amt = Float.parseFloat(amount);
        return new Account(0, amt, username);
    }

    public void register(){
        Account account = getAccountDetails();
        Account registeredAccount = accountService.registerAccount(account);
        System.out.println("New account number: " + registeredAccount.getAccnt_no());
    }

    public void viewAllAccountsByUsername(){
        System.out.println("Please enter the username: ");
        String username = scanner.nextLine();
        List<Account> accounts = accountService.getAllAccountsByUsername(username);
        for(Account account : accounts){
            System.out.println("Account number: " + account.getAccnt_no() + " Amount: " + account.getAmt());
        }
    }

    public void PromptForAccountService(Map<String, String> controlMap){

        System.out.println("What would you like to do?");
        System.out.println("1. Open an Account");
        System.out.println("2. To view all your accounts");
        System.out.println("0. Logout");
        try{
            String action = scanner.nextLine();
            switch (action){
                case "1":
                    register();
                    break;
                case "2":
                    viewAllAccountsByUsername();
                    break;
                case "0":
                    controlMap.put("Continue Loop", "false");
            }
        }
        catch (InvalidEntry e){
            System.out.println(e.getMessage());
        }
    }

}
