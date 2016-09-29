package com.example.zakar.logindesignbyzak;


public class User {

    String username;
    String password;

public User(String USERNAME, String PASSWORD){
    username = USERNAME;
    password = PASSWORD;
}

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
