package com.shane.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shane.model.Response;
import com.shane.model.Users;
import com.shane.service.UserService;
import com.shane.service.UserServiceImp;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

public class UserController {

    //setup for Singleton
    private static UserController userController;
    UserService userService;
    private UserController(){userService = new UserServiceImp();}
    public static UserController getInstance(){
        if(userController == null)
            userController = new UserController();
        return userController;
    }

    public void getAllUsers(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        out.println(new ObjectMapper().writeValueAsString(new Response("Here are all the employees", true, userService.getAllEmployees())));
    }

    public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //set the type that we will be sending
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        Users users = new ObjectMapper().readValue(requestBody,Users.class);
        Users temporaryUser = userService.login(users);

        //will return session and user object for them.
        if(temporaryUser != null){
            HttpSession httpSession = req.getSession(true);
            httpSession.setAttribute("userobject", temporaryUser);

            out.println(new ObjectMapper().writeValueAsString(new Response("You are Logged in", true,temporaryUser )));
        }else{
            out.println(new ObjectMapper().writeValueAsString(new Response("You Failed to Logged in", false,null )));
        }
    }

    public void addUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //set the type that we will be sending
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        Users users = new ObjectMapper().readValue(requestBody, Users.class);
        Users temporaryUser = userService.addUser(users);


        if(temporaryUser != null){
            out.println(new ObjectMapper().writeValueAsString(new Response("User has been added to database", true, temporaryUser)));
        }else{
            out.println(new ObjectMapper().writeValueAsString(new Response("Failed to add user", false, null)));
        }
    }

    public void checkSession(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        Users users = (Users) req.getSession().getAttribute("userobject");

        if(users != null) {
            out.println(new ObjectMapper().writeValueAsString(new Response("session found", true, users)));
        }else{
            out.println(new ObjectMapper().writeValueAsString(new Response("session not found", false, null)));
        }
    }

    public void logOut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        req.getSession().setAttribute("userobject",null);
        out.println(new ObjectMapper().writeValueAsString(new Response("You have logged out",true, null)));
    }
}
