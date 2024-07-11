package com.revature.Service;

import com.revature.Entity.Account;
import com.revature.Exception.notEnoughBalance;
import com.revature.Exception.thereIsStillBalance;
import com.revature.Repository.AccountInterface;

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

    public Account getAccountByaccnt_no(int accnt_no){

        return accountDAO.getAccountByAccnt_no(accnt_no);

    }

    public Account depositByAccnt_no(float amt, int accnt_no){

        Account account = accountDAO.getAccountByAccnt_no(accnt_no);
        float balance = account.getAmt();
        float new_amt = balance + amt;
        accountDAO.updateAmt(new_amt, accnt_no);
        return accountDAO.getAccountByAccnt_no(accnt_no);

    }

    public Account withdrawByAccnt_no(float amt, int accnt_no) throws notEnoughBalance{

        Account account = accountDAO.getAccountByAccnt_no(accnt_no);
        float balance = account.getAmt();
        if(balance >= amt){
            float new_amt = balance - amt;
            accountDAO.updateAmt(new_amt, accnt_no);
            return accountDAO.getAccountByAccnt_no(accnt_no);
        }
        throw new notEnoughBalance("Not enough balance. Try again with different amount.");
    }

    public void deleteAccountByAccnt_no(int accnt_no){

        Account account = accountDAO.getAccountByAccnt_no(accnt_no);
        float balance = account.getAmt();
        if(balance == 0.0){
            accountDAO.deleteAccountByAccnt_no(accnt_no);
        }
        else{
            throw new thereIsStillBalance("Withdraw the balance before closing the account.");
        }

    }

}
