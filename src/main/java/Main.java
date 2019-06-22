import Database.DataService;
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


        String connectionString = "jdbc:mysql://localhost/ooca4"; //Database connection ip/string
        Properties connProps = new Properties();
        connProps.put("user","ooc");
        connProps.put("password","dbooc");

        DataService dataService = new DataService(connectionString,connProps);

        Context ctx;
        try{
            ctx = tomcat.addWebapp("",tempDir.getAbsolutePath());

            LoginServlet login = new LoginServlet(dataService);
            SignupServlet signup = new SignupServlet(dataService);

            Tomcat.addServlet(ctx,"LoginServlet",login);
            Tomcat.addServlet(ctx,"SignupServlet",signup);
            ctx.addServletMappingDecoded("/signup","SignupServlet");
            ctx.addServletMappingDecoded("/login","LoginServlet");

            tomcat.start();
            tomcat.getServer().await();
        }
        catch(LifecycleException ex){
            ex.printStackTrace();
        }
    }

}
