package com.brandon.Servlets;

import com.brandon.Database.UserService;
import com.brandon.Router.Routable;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignupServlet extends HttpServlet implements Routable {

    @Override
    public String getMapping() {
        return "/signup";
    }

    UserService data;

    public SignupServlet(UserService dataService){
        this.data = dataService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("errorMsg","");

        RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/signup.jsp");
        rd.include(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if(!data.userExists(username)){
            data.addUser(username,password);
            req.getSession(true).setAttribute("username",username);
            resp.sendRedirect("/users");
        }
        else {
            resp.sendRedirect("/signup");
        }
    }
}
