package com.brandon;

import com.brandon.Database.UserService;
import com.brandon.Router.Router;
import com.brandon.Servlets.*;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.ErrorPage;

import java.io.File;
import java.util.Properties;

public class Main {

    public static void main(String[] args){
        File tempDir = new File("src/main/webapp/");
        tempDir.mkdirs();
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);

        System.out.println("Starting sql connection");
        String databaseName = "ooc_a4";
        String connectionString = "jdbc:mysql://localhost:3306"; //Database connection ip/string
        Properties connProps = new Properties();
        connProps.put("user","root");
        connProps.put("password","Yikey123");

        UserService dataService = new UserService(connectionString,connProps, databaseName);

        Context ctx;
        try{
            ctx = tomcat.addWebapp("",tempDir.getAbsolutePath());

            LoginServlet login = new LoginServlet(dataService);
            SignupServlet signup = new SignupServlet(dataService);
            UserListServlet users = new UserListServlet(dataService);
            LogoutServlet logout = new LogoutServlet();
            UserRemoveServlet removeServlet = new UserRemoveServlet(dataService);
            UserPageServlet userPageServlet = new UserPageServlet(dataService);

            Router router = new Router();
            router.addRoutable(login);
            router.addRoutable(signup);
            router.addRoutable(users);
            router.addRoutable(logout);
            router.addRoutable(removeServlet);
            router.addRoutable(userPageServlet);

            router.init(ctx);

            ErrorPage defaultRedirect = new ErrorPage();
            defaultRedirect.setErrorCode(404);
            defaultRedirect.setLocation("/users");

            ctx.addErrorPage(defaultRedirect);

            tomcat.start();
            tomcat.getServer().await();
        }
        catch(LifecycleException ex){
            ex.printStackTrace();
        }
    }

}
