import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.ServletException;
import java.io.File;

public class Main {

    public static void main(String[] args){
        File tempDir = new File("src/main/webapp_temp/");
        tempDir.mkdirs();
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8082);

        Context ctx;
        try{
            ctx = tomcat.addWebapp("",tempDir.getAbsolutePath());



            tomcat.start();
            tomcat.getServer().await();
        }
        catch(ServletException | LifecycleException ex){
            ex.printStackTrace();
        }
    }

}
