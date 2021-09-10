package com.shane.service;


import com.shane.model.Users;

import java.util.List;


public interface UserService {

    List<Users> getAllEmployees();
    Users addUser(Users users);
    Users login(Users users);

}
