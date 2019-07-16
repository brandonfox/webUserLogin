package com.brandon.Database;

public class User {

    private String username;
    private String password;
    private String email;

    public User(String username){
        this.username = username;
    }

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername(){ return username; }
    public String getPassword(){ return password; }
    public String getEmail(){ return email;}

    public void setPassword(String password) { this.password = password; }
    public void setEmail(String email){ this.email = email;}

}
