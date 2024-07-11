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

    public void register(){
        Account registeredAccount = accountService.registerAccount(accountOwner);
        System.out.println("New account number: " + registeredAccount.getAccnt_no());
    }

    public void viewAllAccountsByUsername(){
        List<Account> accounts = accountService.getAllAccountsByUsername(accountOwner);
        for(Account account : accounts){
            System.out.println("Account number: " + account.getAccnt_no() + " Amount: " + account.getAmt());
        }
    }

    public void viewAccountByAccnt_no(){
        int accnt_no = getAccountNo();
        Account account = accountService.getAccountByaccnt_no(accnt_no);
        System.out.println("Account number: " + account.getAccnt_no() + " Amount: " + account.getAmt());
    }

    public void depositAmount(){
        int accnt_no = getAccountNo();
        System.out.println("Please enter the amount to be deposited: ");
        String amt = scanner.nextLine();
        float amt_flt = Float.parseFloat(amt);
        Account account = accountService.depositByAccnt_no(amt_flt, accnt_no);
        System.out.println("Account number: " + account.getAccnt_no() + " Amount: " + account.getAmt());
    }

    public void withdrawAmount(){
        int accnt_no = getAccountNo();
        System.out.println("Please enter the amount to be withdrawn: ");
        String amt = scanner.nextLine();
        float amt_flt = Float.parseFloat(amt);
        Account account = accountService.withdrawByAccnt_no(amt_flt, accnt_no);
        System.out.println("Account number: " + account.getAccnt_no() + " Amount: " + account.getAmt());
    }

    public void closeAccount(){
        int accnt_no = getAccountNo();
        accountService.deleteAccountByAccnt_no(accnt_no);
        System.out.println("Account with account number: " + accnt_no + " has been closed.");
    }

    public void PromptForAccountService(Map<String, String> controlMap){

        System.out.println("Welcome to your dashboard. \n What would you like to do?");
        System.out.println("1. To Open an Account");
        System.out.println("2. To view all your accounts");
        System.out.println("3. To view one of your accounts");
        System.out.println("4. To deposit money into one of your accounts");
        System.out.println("5. To withdraw money from one of your accounts");
        System.out.println("6. To close an Account");
        System.out.println("7. Logout");
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
                    depositAmount();
                    break;
                case "5":
                    withdrawAmount();
                    break;
                case "6":
                    closeAccount();
                    break;
                case "7":
                    controlMap.put("Continue Loop", "false");
            }
        }
        catch (InvalidEntry e){
            System.out.println(e.getMessage());
        }
    }

}
