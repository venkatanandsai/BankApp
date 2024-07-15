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

    public Account registerAccount(Account account){
        if(account.getType().equals("1")){
            String accnt_type = "CHECKING";
            float amt = 30.0f;
            account.setType(accnt_type);
            account.setAmt(amt);
            return accountDAO.createAccount(account);
        }
        else if(account.getType().equals("2")){
            String accnt_type = "SAVINGS";
            float amt = 50.0f;
            account.setType(accnt_type);
            account.setAmt(amt);
            return accountDAO.createAccount(account);
        }
        throw new typeInvalidException("Invalid type. Please try again.");
    }

    public List<Account> getAllAccountsByUsername(Account account){

        return accountDAO.getAllAccountsByUsername(account);

    }

    public Account getAccountByaccnt_no(Account account){

        return accountDAO.getAccountByAccnt_no(account);

    }

    public Account registerJointUser(Account account){

        accountDAO.updateCousername(account);
        return accountDAO.getAccountByAccnt_no(account);

    }

    public Account depositByAccnt_no(Account account){

        Account retaccount = accountDAO.getAccountByAccnt_no(account);
        float balance =retaccount.getAmt();
        retaccount.setAmt(balance + account.getAmt());
        accountDAO.updateAmt(retaccount);
        return accountDAO.getAccountByAccnt_no(retaccount);

    }

    public Account withdrawByAccnt_no(Account account) throws notEnoughBalance{

        Account retaccount = accountDAO.getAccountByAccnt_no(account);
        float balance = retaccount.getAmt();
        if(balance >= account.getAmt()){
            retaccount.setAmt(balance - account.getAmt());
            accountDAO.updateAmt(retaccount);
            return accountDAO.getAccountByAccnt_no(retaccount);
        }
        throw new notEnoughBalance("Not enough balance. Try again with different amount.");
    }

    public void deleteAccountByAccnt_no(Account account){

        Account retaccount = accountDAO.getAccountByAccnt_no(account);
        float balance = retaccount.getAmt();
        if(balance == 0.0){
            accountDAO.deleteAccountByAccnt_no(account);
        }
        else{
            throw new thereIsStillBalance("Withdraw the balance before closing the account.");
        }

    }

}
