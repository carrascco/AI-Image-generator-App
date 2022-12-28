package com.example.pmd_aiproject.model;

public class User {

    private String username;
    private String password;

    private String key;


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User (String username, String password, String key){

        this.username=username;
        this.password=password;
        this.key=key;

    }
}
