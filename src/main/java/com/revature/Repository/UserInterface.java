package com.revature.Repository;

import com.revature.Entity.User;

import java.util.List;

public interface UserInterface {

    User createUser(User newUserCredentials);
    List<User> getAllUsers();

}
