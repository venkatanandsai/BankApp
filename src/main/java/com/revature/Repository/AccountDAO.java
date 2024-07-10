package com.revature.Repository;

import com.revature.Entity.Account;
import com.revature.Exception.AccountSQLException;
import com.revature.Utility.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO implements AccountInterface{

    public Account createAccount(Account newAccountDetails){

        String sql = "insert into account (amt, username) values (?, ?)";

        try(Connection connection = DatabaseConnector.createConnection()){

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setFloat(1, newAccountDetails.getAmt());
            preparedStatement.setString(2, newAccountDetails.getUsername());
            ResultSet resultSet = preparedStatement.executeQuery();
            Account persistedAccount = new Account();
            persistedAccount.setAccnt_no(resultSet.getInt("Accnt_no"));
            persistedAccount.setAmt(resultSet.getFloat("amt"));
            persistedAccount.setUsername(resultSet.getString("username"));
            if(persistedAccount.getAccnt_no() > 0){
                return persistedAccount;
            }
            throw new AccountSQLException("Account could not be created. Please try again.");
        }
        catch (SQLException e){
            throw new AccountSQLException(e.getMessage());
        }
    }

    public List<Account> getAllAccounts(){

        String sql = "select * from account";
        try(Connection connection = DatabaseConnector.createConnection()){

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            List<Account> accounts = new ArrayList<>();
            while (resultSet.next()){
                Account accountRecord = new Account();
                accountRecord.setAccnt_no(resultSet.getInt("accnt_no"));
                accountRecord.setAmt(resultSet.getFloat("amt"));
                accountRecord.setUsername(resultSet.getString("username"));
                accounts.add(accountRecord);
            }
            return accounts;
        }
        catch (SQLException e){
            throw new AccountSQLException(e.getMessage());
        }
    }

}
