package com.brandon.Servlets;

import com.brandon.Database.SecurityService;
import com.brandon.Database.User;
import com.brandon.Database.UserService;
import com.brandon.Router.Routable;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class UserPageServlet extends HttpServlet implements Routable {

    @Override
    public String getMapping() {
        return "/user";
    }

    UserService userService;

    public UserPageServlet(UserService userService){
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(!SecurityService.SessionAuthorized(req)){
            resp.sendRedirect("/login");
        }
        else{
            RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/user.jsp");
            User user = userService.getUser(SecurityService.getUsername(req));
            req.setAttribute("user",user);
            rd.include(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Enumeration<String> paramNames = req.getParameterNames();
        Map<String,String> editMap = new HashMap<>();
        while(paramNames.hasMoreElements()){
            String element = paramNames.nextElement();
            editMap.put(element,req.getParameter(element));
        }
        userService.editUser(editMap, SecurityService.getUsername(req));
    }
}
