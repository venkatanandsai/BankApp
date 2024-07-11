package com.revature.Repository;

import com.revature.Entity.Account;
import com.revature.Exception.AccountSQLException;
import com.revature.Utility.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO implements AccountInterface{

    public Account createAccount(Account newAccountDetails){

        String sql = "insert into account (amnt, username) values (?, ?);";

        try(Connection connection = DatabaseConnector.createConnection()){

            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setFloat(1, newAccountDetails.getAmt());
            preparedStatement.setString(2, newAccountDetails.getUsername());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                int accnt_no = (int) resultSet.getLong(1);
                return new Account(accnt_no, newAccountDetails.getAmt(), newAccountDetails.getUsername());
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
                accountRecord.setAmt(resultSet.getFloat("amnt"));
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
