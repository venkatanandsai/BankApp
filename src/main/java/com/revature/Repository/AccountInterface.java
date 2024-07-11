package com.revature.Repository;

import com.revature.Entity.Account;

import java.util.List;

public interface AccountInterface {

    Account createAccount(String username);
    List<Account> getAllAccountsByUsername(String username);
    Account getAccountByAccnt_no(int accnt_no);
    void updateAmt(float amt, int accnt_no);
    void deleteAccountByAccnt_no(int accnt_no);

}
