package com.brandon;

import com.brandon.Database.DataService;
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
        String connectionString = "jdbc:mysql://localhost/ooca4"; //Database connection ip/string
        Properties connProps = new Properties();
        connProps.put("user","ooc");
        connProps.put("password","dbooc");

        DataService dataService = new DataService(connectionString,connProps);

        Context ctx;
        try{
            System.out.println("Starting webapp");
            ctx = tomcat.addWebapp("",tempDir.getAbsolutePath());

            LoginServlet login = new LoginServlet(dataService);
            SignupServlet signup = new SignupServlet(dataService);

            Tomcat.addServlet(ctx,"com.brandon.LoginServlet",login);
            Tomcat.addServlet(ctx,"com.brandon.SignupServlet",signup);
            ctx.addServletMappingDecoded("/signup","com.brandon.SignupServlet");
            ctx.addServletMappingDecoded("/login","com.brandon.LoginServlet");

            System.out.println("Starting tomcat server");
            tomcat.start();
            tomcat.getServer().await();
        }
        catch(LifecycleException ex){
            ex.printStackTrace();
        }
    }

}
