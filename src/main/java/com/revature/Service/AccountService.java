package com.revature.Service;

import com.revature.Entity.Account;
import com.revature.Repository.AccountInterface;
import com.revature.Repository.UserInterface;

import java.util.List;

public class AccountService {

    private AccountInterface accountDAO;

    public AccountService(AccountInterface accountDAO) {
        this.accountDAO = accountDAO;
    }

    public Account registerAccount(Account newAccountDetails){

        return accountDAO.createAccount(newAccountDetails);

    }

    public List<Account> getAllAccountsByUsername(String username){

        return accountDAO.getAllAccountsByUsername(username);

    }

}
