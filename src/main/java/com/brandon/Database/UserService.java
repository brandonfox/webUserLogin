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

    public UserService(String connectionString, Properties connectionProperties, String databaseName){
        try {
            dbconn = DriverManager.getConnection(connectionString + "/" + databaseName, connectionProperties);
            tableMap = new HashMap<>();
            tableMap.put("users","create table users(username varchar(255) primary key not null, password varchar(72) not null)");
            initialiseTables(databaseName);
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

    public User getUser(String username){
        String sql = "select * from users where username = ?";
        List<User> userList = parseResultSetAsUser(executeQuery(sql,username));
        if(userList.size() > 0)
            return userList.get(0);
        return null;
    }

    private List<User> getUsers(String sql, String... args){
        return parseResultSetAsUser(executeQuery(sql,args));
    }

    private List<User> parseResultSetAsUser(ResultSet rs){
        try {
            List<User> users = new ArrayList<>();
            while (rs.next()) {
                String username = rs.getString(1);
                User user = new User(username);
                if(hasColumn(rs,"password"))
                    user.setPassword(rs.getString("password"));
                users.add(user);
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
        String sql = "insert into users(username,password) values (?,?)";
        executeSql(sql, username, SecurityService.encryptPassword(password));
    }

//    public boolean isValidCredentials(String username, String password){
//        return true;
//    }

}
