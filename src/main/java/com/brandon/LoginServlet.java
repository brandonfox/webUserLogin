package com.brandon;

import com.brandon.Database.DataService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    DataService data;

    public LoginServlet(DataService dataService){
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
        if(!data.usernameTaken(username)){
            data.addUser(username,password);
        }
    }
}
