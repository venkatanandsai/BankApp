package com.revature.Service;

import com.revature.Entity.Account;
import com.revature.Repository.AccountInterface;
import com.revature.Repository.UserInterface;

public class AccountService {

    private AccountInterface accountDAO;

    public AccountService(AccountInterface accountDAO) {
        this.accountDAO = accountDAO;
    }

    public Account registerAccount(Account newAccountDetails){

        return accountDAO.createAccount(newAccountDetails);

    }

}
