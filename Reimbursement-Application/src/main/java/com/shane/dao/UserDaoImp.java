package com.shane.dao;

import com.shane.model.Users;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImp implements UserDao{
    private static UserDao userDao;
    public UserDaoImp(){
        try{Class.forName("org.postgresql.Driver");
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static UserDao getInstance(){
        if(userDao == null)
            userDao = new UserDaoImp();
        return userDao;
    }
    //add user
    @Override
    public void createUser(Users users) {
        try(Connection conn = DriverManager.getConnection(ConnectionUtil.url, ConnectionUtil.username, ConnectionUtil.password)){

            //Sql Statement
            String sql = "INSERT INTO users VALUES (DEFAULT, ?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, users.getUsername());
            ps.setString(2, users.getPassword());
            ps.setString(3, users.getFirstName());
            ps.setString(4, users.getLastName());
            ps.setString(5, users.getUserEmail());
            ps.setInt(6, users.getUserRoleIdFk());

            ps.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    //signIn
    @Override
    public Users checkUser(String usName, String pass) {
        Users users =  null;
        try(Connection conn = DriverManager.getConnection(ConnectionUtil.url, ConnectionUtil.username, ConnectionUtil.password)){
            System.out.println(usName + pass);
            //Sql Statement
            String sql = "SELECT * FROM users WHERE username = ? and password = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, usName);
            ps.setString(2, pass);

            //Result from query
            ResultSet rs = ps.executeQuery();;
            while(rs.next()) {
                users = new Users(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public List<Users> getAllEmployees() {
        List<Users> users = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(ConnectionUtil.url, ConnectionUtil.username, ConnectionUtil.password)){
            //Sql Statement
            String sql = "SELECT * FROM users";
            PreparedStatement ps = conn.prepareStatement(sql);

            //Result from query
            ResultSet rs = ps.executeQuery();;
            while(rs.next()) {
                users.add( new Users(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7))
                );
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return users;
    }
}
