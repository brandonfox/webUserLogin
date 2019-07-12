package com.brandon.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface DataService {

    Connection getDataConnection();

    /**
     * A map that stores the tablename and the string for creating the table complete with columns
     * Currently does not support table orders (In case of foreign keys)
     */
    Map<String,String> tableMap();

    default boolean tableExists(){
        String sql = "select table_name from information_schema.tables where table_type = 'table' and table_name = ?";
        return parseResultSetAsString(executeQuery(sql,true)).size() != 0;
    }

    default void initialiseTables(String databaseName){
        System.out.println("Initialising tables");
        String sql = "select table_name from information_schema.tables where table_schema = '" + databaseName + "'";
        List<String> existingTables = parseResultSetAsString(executeQuery(sql,true));
        System.out.println("Tables existing in the database: " + existingTables.toString());
        for (String s:tableMap().keySet()) {
            if(!existingTables.contains(s)){
                executeSql(tableMap().get(s),true);
            }
        }
    }

    default List<String> parseResultSetAsString(ResultSet rs){
        try {
            List<String> strings = new ArrayList<>();
            while (rs.next()) {
                strings.add(rs.getString(1));
            }
            return strings;
        }
        catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }


    default ResultSet executeQuery(String sql, boolean killOnFail, String... args){
        try{
            PreparedStatement prepStmt = getDataConnection().prepareStatement(sql);
            for(int x = 0; x < args.length; x++){
                prepStmt.setString(x+1,args[x]);
            }
            return prepStmt.executeQuery();
        }
        catch(SQLException ex){
            ex.printStackTrace();
            if(killOnFail)
                System.exit(-1);
            return null;
        }
    }

    default void executeSql(String sql, boolean killOnFail, String... args){
        try{
            PreparedStatement prepStmt = getDataConnection().prepareStatement(sql);
            for(int x = 0; x < args.length; x++){
                prepStmt.setString(x+1,args[x]);
            }
            prepStmt.execute();
        }
        catch(SQLException ex){
            ex.printStackTrace();
            if(killOnFail)
                System.exit(-1);
        }
    }

    default void executeSql(String sql, String... args){
        executeSql(sql,false,args);
    }

    default ResultSet executeQuery(String sql, String... args){
        return executeQuery(sql,false,args);
    }


}
