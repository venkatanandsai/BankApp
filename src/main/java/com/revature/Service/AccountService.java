package com.revature.Service;

import com.revature.Entity.Account;
import com.revature.Exception.notEnoughBalance;
import com.revature.Exception.thereIsStillBalance;
import com.revature.Exception.typeInvalidException;
import com.revature.Repository.AccountInterface;

import java.util.List;

public class AccountService {

    private AccountInterface accountDAO;

    public AccountService(AccountInterface accountDAO) {
        this.accountDAO = accountDAO;
    }

    public Account registerAccount(String username, String type){
        if(type.equals("1")){
            String accnt_type = "CHECKING";
            float amt = 30.0f;
            return accountDAO.createAccount(amt, username, accnt_type);
        }
        else if(type.equals("2")){
            String accnt_type = "SAVINGS";
            float amt = 50.0f;
            return accountDAO.createAccount(amt, username, accnt_type);
        }
        throw new typeInvalidException("Invalid type. Please try again.");
    }

    public List<Account> getAllAccountsByUsername(String username){

        return accountDAO.getAllAccountsByUsername(username);

    }

    public Account getAccountByaccnt_no(int accnt_no, String username){

        return accountDAO.getAccountByAccnt_no(accnt_no, username);

    }

    public Account registerJointUser(int accnt_no, String username, String cousername){

        accountDAO.updateCousername(accnt_no, username, cousername);
        return accountDAO.getAccountByAccnt_no(accnt_no, username);

    }

    public Account depositByAccnt_no(float amt, int accnt_no, String username){

        Account account = accountDAO.getAccountByAccnt_no(accnt_no, username);
        float balance = account.getAmt();
        float new_amt = balance + amt;
        accountDAO.updateAmt(new_amt, accnt_no, username);
        return accountDAO.getAccountByAccnt_no(accnt_no, username);

    }

    public Account withdrawByAccnt_no(float amt, int accnt_no, String username) throws notEnoughBalance{

        Account account = accountDAO.getAccountByAccnt_no(accnt_no, username);
        float balance = account.getAmt();
        if(balance >= amt){
            float new_amt = balance - amt;
            accountDAO.updateAmt(new_amt, accnt_no, username);
            return accountDAO.getAccountByAccnt_no(accnt_no, username);
        }
        throw new notEnoughBalance("Not enough balance. Try again with different amount.");
    }

    public void deleteAccountByAccnt_no(int accnt_no, String username){

        Account account = accountDAO.getAccountByAccnt_no(accnt_no, username);
        float balance = account.getAmt();
        if(balance == 0.0){
            accountDAO.deleteAccountByAccnt_no(accnt_no, username);
        }
        else{
            throw new thereIsStillBalance("Withdraw the balance before closing the account.");
        }

    }

}
