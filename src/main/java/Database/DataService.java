package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataService {

    Connection dbconn;

    public DataService(String connectionString, Properties connectionProps){
        try {
            dbconn = DriverManager.getConnection(connectionString, connectionProps);
        }catch(SQLException e){
            System.out.println("Failed to create sql connection");
        }
    }



}
