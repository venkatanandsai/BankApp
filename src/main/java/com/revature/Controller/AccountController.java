package com.revature.Controller;

import com.revature.Entity.Account;
import com.revature.Exception.InvalidEntry;
import com.revature.Service.AccountService;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AccountController {

    private Scanner scanner;
    private AccountService accountService;
    private final String accountOwner;

    public AccountController(Scanner scanner, AccountService accountService, String accountOwner) {
        this.scanner = scanner;
        this.accountService = accountService;
        this.accountOwner = accountOwner;
    }

    public int getAccountNo(){
        System.out.println("Please enter the account number: ");
        String accnt_no = scanner.nextLine();
        return Integer.parseInt(accnt_no);
    }

    public String getType(){
        System.out.println("Enter the type of account: ");
        System.out.println("1 for Checking and 2 for Savings");
        return scanner.nextLine();
    }

    public void register(){
        String type = getType();
        Account registeredAccount = accountService.registerAccount(accountOwner, type);
        System.out.println("New account number: " + registeredAccount.getAccnt_no());
    }

    public void viewAllAccountsByUsername(){
        List<Account> accounts = accountService.getAllAccountsByUsername(accountOwner);
        for(Account account : accounts){
            System.out.println("Account number: " + account.getAccnt_no() + " Amount: " + account.getAmt() + " Type: "
            + account.getType());
        }
    }

    public void viewAccountByAccnt_no(){
        int accnt_no = getAccountNo();
        Account account = accountService.getAccountByaccnt_no(accnt_no, accountOwner);
        System.out.println("Account number: " + account.getAccnt_no() + " Amount: " + account.getAmt() + " Type: "
        + account.getType());
    }

    public void registerJointUser(){
        int accnt_no = getAccountNo();
        System.out.println("Enter the joint username: ");
        String cousername = scanner.nextLine();
        Account account = accountService.registerJointUser(accnt_no, accountOwner, cousername);
        System.out.println("Account number: " + account.getAccnt_no() + " Amount: " + account.getAmt() + " Type: "
                + account.getType() + "Joint username: " + account.getCoUsername());
    }

    public void depositAmount(){
        int accnt_no = getAccountNo();
        System.out.println("Please enter the amount to be deposited: ");
        String amt = scanner.nextLine();
        float amt_flt = Float.parseFloat(amt);
        Account account = accountService.depositByAccnt_no(amt_flt, accnt_no, accountOwner);
        System.out.println("Account number: " + account.getAccnt_no() + " Amount: " + account.getAmt());
    }

    public void withdrawAmount(){
        int accnt_no = getAccountNo();
        System.out.println("Please enter the amount to be withdrawn: ");
        String amt = scanner.nextLine();
        float amt_flt = Float.parseFloat(amt);
        Account account = accountService.withdrawByAccnt_no(amt_flt, accnt_no, accountOwner);
        System.out.println("Account number: " + account.getAccnt_no() + " Amount: " + account.getAmt());
    }

    public void closeAccount(){
        int accnt_no = getAccountNo();
        accountService.deleteAccountByAccnt_no(accnt_no, accountOwner);
        System.out.println("Account with account number: " + accnt_no + " has been closed.");
    }

    public void PromptForAccountService(Map<String, String> controlMap){

        System.out.println("Welcome to your dashboard. \n What would you like to do?");
        System.out.println("1. To Open an Account");
        System.out.println("2. To view all your accounts");
        System.out.println("3. To view one of your accounts");
        System.out.println("4. To register a joint user for one of your accounts");
        System.out.println("5. To deposit money into one of your accounts");
        System.out.println("6. To withdraw money from one of your accounts");
        System.out.println("7. To close an Account");
        System.out.println("l. Logout");
        try{
            String action = scanner.nextLine();
            switch (action){
                case "1":
                    register();
                    break;
                case "2":
                    viewAllAccountsByUsername();
                    break;
                case "3":
                    viewAccountByAccnt_no();
                    break;
                case "4":
                    registerJointUser();
                    break;
                case "5":
                    depositAmount();
                    break;
                case "6":
                    withdrawAmount();
                    break;
                case "7":
                    closeAccount();
                    break;
                case "l":
                    controlMap.put("Continue Loop", "false");
            }
        }
        catch (InvalidEntry e){
            System.out.println(e.getMessage());
        }
    }

}
