package com.shane.service;

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

class UserServiceTest {
    @Mock
    private UserService userService;


    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
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

        Mockito.when(userService.getAllEmployees()).thenReturn(expectedResult);

        //Act
        List<Users> actualResult = userService.getAllEmployees();

        //Assert
        assertEquals(expectedResult, actualResult);

    }

    @Test
    void addUser() {
        //Assign
        Users users = new Users(
                "AlbaO",
                "OpenUp",
                "Alba",
                "Orozco",
                "albao@email.com",
                2);
        Users expectedResult = users;

        //Act
        Mockito.when(userService.addUser(users)).thenReturn(users);
        Users actualResult = userService.addUser(users);

        //Assert
        assertEquals(expectedResult.toString(), actualResult.toString());
    }

    @Test
    void login() {
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

        //Act
        Mockito.when(userService.login(users)).thenReturn(users);
        Users actualResult = userService.login(users);

        //Assert
        assertEquals(expectedResult.toString(), actualResult.toString());
    }
}