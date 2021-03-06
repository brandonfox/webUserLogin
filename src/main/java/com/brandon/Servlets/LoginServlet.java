package com.brandon.Servlets;

import com.brandon.Database.DataService;
import com.brandon.Database.SecurityService;
import com.brandon.Database.UserService;
import com.brandon.Router.Routable;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet implements Routable {

    UserService data;

    @Override
    public String getMapping() {
        return "/login";
    }

    public LoginServlet(UserService dataService){
        data = dataService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("errorMsg","");

        RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/login.jsp");
        rd.include(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if(SecurityService.AuthenticateUser(data,username,password)){
            req.getSession(true).setAttribute("username",username);
            resp.sendRedirect("/users");
        }
        else{
            resp.sendRedirect("/login");
        }
    }
}
