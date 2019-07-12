package com.brandon.Database;

import java.sql.*;
import java.util.*;

public class UserService implements DataService {

    Connection dbconn;
    Map<String,String> tableMap;

    @Override
    public Map<String, String> tableMap() {
        return tableMap;
    }

    @Override
    public Connection getDataConnection() {
        return dbconn;
    }

    public UserService(String connectionString, Properties connectionProperties){
        try {
            dbconn = DriverManager.getConnection(connectionString, connectionProperties);
            tableMap = new HashMap<>();
            tableMap.put("users","create table users(username varchar(255) primary key not null, password varchar(72) not null, salt varchar(16) not null)");
            initialiseTables();
        }
        catch(SQLException ex){
            ex.printStackTrace();
            System.exit(-1);
        }
    }

    public List<User> getAllUsers(){
        String sql = "select * from users";
        return getUsers(sql);
    }

    private List<User> getUsers(String sql, String... args){
        return parseResultSetAsUser(executeQuery(sql,args));
    }

    private List<User> parseResultSetAsUser(ResultSet rs){
        try {
            List<User> users = new ArrayList<>();
            while (rs.next()) {
                String username = rs.getString(1);
                users.add(new User(username));
            }
            return users;
        }
        catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }

    public boolean usernameTaken(String username){
        String sql = "select * from users where username = ?";
        return getUsers(sql,username).size() != 0;
    }

    public void addUser(String username, String password){
        String sql = "insert into users(username,password,salt) values (?,?,?)";
        SaltPasswordPair saltPassPair = SecurityService.encryptPassword(password);
        executeSql(sql, username, new String(saltPassPair.getPassword()), new String(saltPassPair.getSalt()));
    }

//    public boolean isValidCredentials(String username, String password){
//        return true;
//    }

}
