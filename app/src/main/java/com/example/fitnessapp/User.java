package com.example.fitnessapp;

public class User {

    String Userid;
    String Username;
    String UserPassword;
    String UserType;

    public User(){

    }

    public User(String userid, String username, String userPassword, String userType) {
        Userid = userid;
        Username = username;
        UserPassword = userPassword;
        UserType= userType;
    }

    public String getUserid() {
        return Userid;
    }

    public String getUsername() {
        return Username;
    }

    public String getUserPassword() {
        return UserPassword;
    }


    public String getUserType() {
        return UserType;
    }

    public void setUserid(String userid) {
        Userid = userid;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public void setUserPassword(String userPassword) {
        UserPassword = userPassword;
    }

    public void setUserType(String userType) {
        UserType = userType;
    }
}
