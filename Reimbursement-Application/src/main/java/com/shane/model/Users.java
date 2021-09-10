package com.shane.model;

public class Users {
    private Integer userId;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String userEmail;
    private Integer userRoleIdFk;

    //No Args constructor
    public  Users(){
    }

    //constructor
    public Users(Integer userId, String username, String password, String firstName, String lastName, String userEmail, Integer userRoleIdFk) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userEmail = userEmail;
        this.userRoleIdFk = userRoleIdFk;
    }

    public Users(String username, String password, String firstName, String lastName, String userEmail, Integer userRoleIdFk) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userEmail = userEmail;
        this.userRoleIdFk = userRoleIdFk;
    }

    public Users(String username, String password) {
        this.username = username;
        this.password = password;
    }


    //Getters and Setters
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Integer getUserRoleIdFk() {
        return userRoleIdFk;
    }

    public void setUserRoleIdFk(Integer userRoleIdFk) {
        this.userRoleIdFk = userRoleIdFk;
    }


    //toString
    @Override
    public String toString() {
        return "Users{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userRoleIdFk=" + userRoleIdFk +
                '}';
    }
}
