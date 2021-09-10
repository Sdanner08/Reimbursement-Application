package com.shane.dao;

import com.shane.model.Users;

import java.util.List;


public interface UserDao {
    void createUser(Users users);
    Users checkUser(String usName, String pass);
    List<Users> getAllEmployees();
}
