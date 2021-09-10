package com.shane.service;

import com.shane.dao.UserDao;
import com.shane.dao.UserDaoImp;
import com.shane.model.Users;
import java.util.List;


public class UserServiceImp implements UserService{
    UserDao userDao;

    //gets the Instance of this to make sure there is only 1
    public UserServiceImp(){userDao = UserDaoImp.getInstance();}


    @Override
    public List<Users> getAllEmployees() {
        return userDao.getAllEmployees();
    }

    @Override
    public Users addUser(Users users) {
        //do a check to see if username already exists in database
        Users temporaryUser = userDao.checkUser(users.getUsername(), users.getPassword());

        //checks to make sure that the username is not currently used
        if(temporaryUser !=null)
        return null;

        //calls the userDao to create the user
        userDao.createUser(users);
        return userDao.checkUser(users.getUsername(), users.getPassword());
    }


    @Override
    public Users login(Users users) {
        //Checks for the username is in the database
        Users temporaryUser = userDao.checkUser(users.getUsername(), users.getPassword());

        //checks to make sure that the username is in the database
        if(temporaryUser == null)
            return null;

        //compares the given password and one in database
        if(!temporaryUser.getPassword().equals(users.getPassword()));
        return temporaryUser;
    }
}
