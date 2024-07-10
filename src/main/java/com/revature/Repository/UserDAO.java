package com.revature.Repository;

import com.revature.Entity.User;
import com.revature.Exception.UserSQLException;
import com.revature.Utility.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements UserInterface {

    public User createUser(User newUserCredentials) {

        String sql = "insert into user values (?, ?)";
        try (Connection connection = DatabaseConnector.createConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, newUserCredentials.getUsername());
            preparedStatement.setString(2, newUserCredentials.getPassword());
            int result = preparedStatement.executeUpdate();
            if (result == 1) {
                return newUserCredentials;
            }

            throw new UserSQLException("User could not be created. Please try again.");

        } catch (SQLException e) {
            throw new UserSQLException(e.getMessage());
        }

    }

    public List<User> getAllUsers() {

        String sql = "select * from user";
        try (Connection connection = DatabaseConnector.createConnection()) {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            List<User> users = new ArrayList<>();
            while (resultSet.next()){
                User userRecord = new User();
                userRecord.setUsername(resultSet.getString("username"));
                userRecord.setPassword(resultSet.getString("password"));
                users.add(userRecord);
            }

            return users;

        } catch (SQLException e) {

            throw new UserSQLException(e.getMessage());

        }
    }

}
