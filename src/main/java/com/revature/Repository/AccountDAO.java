package com.revature.Repository;

import com.revature.Entity.Account;
import com.revature.Exception.AccountSQLException;
import com.revature.Utility.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO implements AccountInterface{

    public Account createAccount(String username){

        String sql = "insert into account (amnt, username) values (?, ?);";
        float amt = 0.0f;

        try(Connection connection = DatabaseConnector.createConnection()){

            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setFloat(1, amt);
            preparedStatement.setString(2, username);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                int accnt_no = (int) resultSet.getLong(1);
                return new Account(accnt_no, amt, username);
            }
            throw new AccountSQLException("Account could not be created. Please try again.");
        }
        catch (SQLException e){
            throw new AccountSQLException(e.getMessage());
        }
    }

    public List<Account> getAllAccountsByUsername(String username){

        String sql = "select * from account where username = ?;";
        try(Connection connection = DatabaseConnector.createConnection()){

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
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

    public Account getAccountByAccnt_no(int accnt_no){

        String sql = "select * from account where accnt_no = ?;";
        try(Connection connection = DatabaseConnector.createConnection()){

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, accnt_no);
            ResultSet resultSet = statement.executeQuery();
            Account account = new Account();
            if(resultSet.next()){
                account.setAccnt_no(resultSet.getInt("accnt_no"));
                account.setAmt(resultSet.getFloat("amnt"));
                account.setUsername(resultSet.getString("username"));
            }
            return account;
        }
        catch (SQLException e){
            throw new AccountSQLException(e.getMessage());
        }
    }

    public void updateAmt(float amt, int accnt_no) {

        String sql = "update Account set amnt = ? where accnt_no = ?;";
        try (Connection connection = DatabaseConnector.createConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setFloat(1, amt);
            preparedStatement.setInt(2, accnt_no);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new AccountSQLException(e.getMessage());
        }
    }

    public void deleteAccountByAccnt_no(int accnt_no){

        String sql = "delete from Account where accnt_no = ?;";
        try (Connection connection = DatabaseConnector.createConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, accnt_no);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new AccountSQLException(e.getMessage());
        }
    }

}
