package com.revature.Repository;

import com.revature.Entity.Account;

import java.util.List;

public interface AccountInterface {

    Account createAccount(Account newAccountDetails);
    List<Account> getAllAccounts();

}
