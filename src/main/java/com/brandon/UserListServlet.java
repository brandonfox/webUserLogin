package com.brandon;

import com.brandon.Database.DataService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class UserListServlet extends HttpServlet {

    DataService dataService;

    public UserListServlet(DataService dataService){
        this.dataService = dataService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/user-list.jsp");
        req.setAttribute("userList",getUsers());
        rd.include(req,resp);

    }

    private Collection<User> getUsers(){
        return dataService.getAllUsers();
    }
}
