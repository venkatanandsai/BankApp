package com.revature.Repository;

import com.revature.Entity.Account;

import java.util.List;

public interface AccountInterface {

    Account createAccount(float amt, String username, String type);
    List<Account> getAllAccountsByUsername(String username);
    Account getAccountByAccnt_no(int accnt_no, String username);
    void updateAmt(float amt, int accnt_no, String username);
    void updateCousername(int accnt_no, String username, String cousername);
    void deleteAccountByAccnt_no(int accnt_no, String username);

}
