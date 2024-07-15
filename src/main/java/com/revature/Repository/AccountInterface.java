package com.revature.Repository;

import com.revature.Entity.Account;

import java.util.List;

public interface AccountInterface {

    Account createAccount(Account account);
    List<Account> getAllAccountsByUsername(Account account);
    Account getAccountByAccnt_no(Account account);
    void updateAmt(Account account);
    void updateCousername(Account account);
    void deleteAccountByAccnt_no(Account account);

}
