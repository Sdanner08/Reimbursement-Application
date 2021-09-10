package com.shane.dao;

import com.shane.model.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserDaoImpTest {
    @Mock
    private UserDaoImp userDaoImp;
    private UserDao userDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

    }
/*    public static UserDao getInstance(){
        if(userDao == null)
            userDao = new UserDaoImp();
        return userDao;

        public class Singleton {
    static Singleton singleton;
    static {
        singleton = new Singleton();
    }

    public static Singleton getSingleton(){
        return singleton;
    }

    public Boolean getBool(){
        return new Random().nextBoolean();
    }
}
    }*/
/*    @Test
    void getInstance() {
        UserDao userDao1 = null;
        userDao = new UserDaoImp();
        UserDao expectResult = userDao;
        Mockito.when(UserDao) getInstance()).thenReturn(expectResult);

        UserDao actualResult = UserDaoImp.getInstance();
        assertEquals(expectResult, actualResult);
    }*/

    @Test
    void createUser() {
        //Assign
        Users users = new Users(
                "AlbaO",
                "OpenUp",
                "Alba",
                "Orozco",
                "albao@email.com",
                2);
        //Act
        doNothing().when(userDaoImp).createUser(users);
        userDaoImp.createUser(users);

        //Assert
        verify(userDaoImp, times(1)).createUser(users);
    }

    @Test
    void checkUser() {
        //Assign
        Users users = new Users(
                1,
                "sdanner",
                "password",
                "Shane",
                "Danner",
                "shane@email.com",
                2);

        Users expectedResult = users;
        Mockito.when(userDaoImp.checkUser("sdanner","password")).thenReturn(users);
        //Act
        Users actualResult = userDaoImp.checkUser("sdanner","password");
        //Assert
        assertEquals(expectedResult.toString(), actualResult.toString());
    }

    @Test
    void getAllEmployees() {
        Users user1 = new Users(1,"sdanner","password","Shane",
                "Danner","shane@email.com",2);

        Users user2 = new Users(2,"test2","test2","TestTwo",
                "TwoTest","testtwo@email.com",1);

        Users user3 = new Users(3,"test3","test3","TestThree",
                "ThreeTest","testthree@email.com",2);

        List<Users> expectedResult = new ArrayList<>();
        expectedResult.add(user1);
        expectedResult.add(user2);
        expectedResult.add(user3);

        Mockito.when(userDaoImp.getAllEmployees()).thenReturn(expectedResult);

        //Act
        List<Users> actualResult = userDaoImp.getAllEmployees();


        //Assert
        assertEquals(expectedResult, actualResult);

    }
}