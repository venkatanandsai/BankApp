package com.revature.Repository;

import com.revature.Entity.Account;
import com.revature.Exception.AccountSQLException;
import com.revature.Utility.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO implements AccountInterface{

    public Account createAccount(Account account){

        String sql = "insert into account (amnt, username, type) values (?, ?, ?);";

        try(Connection connection = DatabaseConnector.createConnection()){

            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setFloat(1, account.getAmt());
            preparedStatement.setString(2, account.getUsername());
            preparedStatement.setString(3, account.getType());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                int accnt_no = (int) resultSet.getLong(1);
                return new Account(accnt_no, account.getAmt(), account.getUsername(), account.getType());
            }
            throw new AccountSQLException("Account could not be created. Please try again.");
        }
        catch (SQLException e){
            throw new AccountSQLException(e.getMessage());
        }
    }

    public List<Account> getAllAccountsByUsername(Account account){

        String sql = "select * from account where username = ? or cousername = ?;";
        try(Connection connection = DatabaseConnector.createConnection()){

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, account.getUsername());
            statement.setString(2, account.getUsername());
            ResultSet resultSet = statement.executeQuery();
            List<Account> accounts = new ArrayList<>();
            while (resultSet.next()){
                Account accountRecord = new Account();
                accountRecord.setAccnt_no(resultSet.getInt("accnt_no"));
                accountRecord.setAmt(resultSet.getFloat("amnt"));
                accountRecord.setUsername(resultSet.getString("username"));
                accountRecord.setType(resultSet.getString("type"));
                accountRecord.setCoUsername(resultSet.getString("cousername"));
                accounts.add(accountRecord);
            }
            return accounts;
        }
        catch (SQLException e){
            throw new AccountSQLException(e.getMessage());
        }
    }

    public Account getAccountByAccnt_no(Account account){

        String sql = "select * from account where accnt_no = ? and (username = ? or cousername = ?);";
        try(Connection connection = DatabaseConnector.createConnection()){

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, account.getAccnt_no());
            statement.setString(2, account.getUsername());
            statement.setString(3, account.getUsername());
            ResultSet resultSet = statement.executeQuery();
            Account resaccount = new Account();
            if(resultSet.next()){
                resaccount.setAccnt_no(resultSet.getInt("accnt_no"));
                resaccount.setAmt(resultSet.getFloat("amnt"));
                resaccount.setUsername(resultSet.getString("username"));
                resaccount.setType(resultSet.getString("type"));
                resaccount.setCoUsername(resultSet.getString("cousername"));
            }
            return resaccount;
        }
        catch (SQLException e){
            throw new AccountSQLException(e.getMessage());
        }
    }

    public void updateAmt(Account account) {

        String sql = "update Account set amnt = ? where accnt_no = ? and (username = ? or cousername = ?);";
        try (Connection connection = DatabaseConnector.createConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setFloat(1, account.getAmt());
            preparedStatement.setInt(2, account.getAccnt_no());
            preparedStatement.setString(3, account.getUsername());
            preparedStatement.setString(4, account.getUsername());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new AccountSQLException(e.getMessage());
        }
    }

    public void updateCousername(Account account) {

        String sql = "update Account set cousername = ? where accnt_no = ? and username = ?;";
        try (Connection connection = DatabaseConnector.createConnection()){

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, account.getCoUsername());
            preparedStatement.setInt(2, account.getAccnt_no());
            preparedStatement.setString(3, account.getUsername());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            throw new AccountSQLException(e.getMessage());
        }
    }

    public void deleteAccountByAccnt_no(Account account){

        String sql = "delete from Account where accnt_no = ? and (username = ? or cousername = ?);";
        try (Connection connection = DatabaseConnector.createConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, account.getAccnt_no());
            preparedStatement.setString(2, account.getUsername());
            preparedStatement.setString(3, account.getUsername());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new AccountSQLException(e.getMessage());
        }
    }

}
