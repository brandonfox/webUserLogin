package com.brandon.Router;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.http.HttpServlet;
import java.util.ArrayList;
import java.util.List;

public class Router {

    private final List<Routable> routables = new ArrayList<>();

    public void addRoutable(Routable routable){
        routables.add(routable);
    }

    public void init(Context ctx){
        for (Routable routable : routables) {
            try {
                String name = routable.getClass().getSimpleName();
                Tomcat.addServlet(ctx, name, (HttpServlet) routable);
                ctx.addServletMappingDecoded(routable.getMapping(), name);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
