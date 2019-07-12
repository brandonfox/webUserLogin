package com.brandon.Database;

import com.brandon.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DataService {

    Connection dbconn;

    private final String userTableString = "create table users(username varchar(255) primary key not null, password varchar(72) not null, salt varchar(16) not null)";

    public DataService(String connectionString, Properties connectionProps){
        try {
            dbconn = DriverManager.getConnection(connectionString, connectionProps);
        }catch(SQLException e){
            System.out.println("Failed to create sql connection");
        }
    }

    public List<User> getAllUsers(){
        String sql = "select * from users";
        return getUsers(sql);
    }

    private List<User> getUsers(String sql, String... args){
        try{
            PreparedStatement prepStmt = dbconn.prepareStatement(sql);
            for(int x = 0; x < args.length; x++){
                prepStmt.setString(x+1,args[x]);
            }
            ResultSet rs = prepStmt.executeQuery();
            return parseResultSet(rs);
        }
        catch (SQLException ex){
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

    private List<User> parseResultSet(ResultSet rs) throws SQLException{
        List<User> users = new ArrayList<>();
        while(rs.next()){
            String username = rs.getString(1);
            users.add(new User(username));
        }
        return users;
    }

    public boolean usernameTaken(String username){
        String sql = "select * from users where username = ?";
        return getUsers(sql,username).size() == 0;
    }

    public void addUser(String username, String password){
        String sql = "insert into users(username,password) values (?,?)";
        try{
            PreparedStatement preparedStatement = dbconn.prepareStatement(sql);
            preparedStatement.setString(1,username);

        }
        catch(SQLException e){

        }
    }


}
