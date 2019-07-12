package com.brandon;

import com.brandon.Database.UserService;
import com.brandon.Servlets.LoginServlet;
import com.brandon.Servlets.SignupServlet;
import com.brandon.Servlets.UserListServlet;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import java.io.File;
import java.util.Properties;

public class Main {

    public static void main(String[] args){
        File tempDir = new File("src/main/webapp/");
        tempDir.mkdirs();
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(80);

        System.out.println("Starting sql connection");
        String databaseName = "ooc_a4";
        String connectionString = "jdbc:mysql://localhost:3306"; //Database connection ip/string
        Properties connProps = new Properties();
        connProps.put("user","root");
        connProps.put("password","Yikey123");

        UserService dataService = new UserService(connectionString,connProps, databaseName);

        Context ctx;
        try{
            System.out.println("Starting webapp");
            ctx = tomcat.addWebapp("",tempDir.getAbsolutePath());

            LoginServlet login = new LoginServlet(dataService);
            SignupServlet signup = new SignupServlet(dataService);
            UserListServlet users = new UserListServlet(dataService);

            Tomcat.addServlet(ctx,"com.brandon.Servlets.LoginServlet",login);
            Tomcat.addServlet(ctx,"com.brandon.Servlets.SignupServlet",signup);
            Tomcat.addServlet(ctx, "com.brandon.Servlets.UserListServlet",users);

            ctx.addServletMappingDecoded("/signup","com.brandon.Servlets.SignupServlet");
            ctx.addServletMappingDecoded("/login","com.brandon.Servlets.LoginServlet");
            ctx.addServletMappingDecoded("/users","com.brandon.Servlets.UserListServlet");

            System.out.println("Starting tomcat server");
            tomcat.start();
            tomcat.getServer().await();
        }
        catch(LifecycleException ex){
            ex.printStackTrace();
        }
    }

}
