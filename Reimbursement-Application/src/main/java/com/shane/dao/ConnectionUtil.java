package com.shane.dao;

public class ConnectionUtil {

    public static String url = "jdbc:postgresql://" + System.getenv("POSTGRES_URI") + "/reimbursement";
    public static String username = System.getenv("POSTGRES_USERNAME");
    public static String password = System.getenv("POSTGRES_PASSWORD");
}
