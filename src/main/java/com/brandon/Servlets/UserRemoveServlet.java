package com.brandon.Servlets;

import com.brandon.Database.SecurityService;
import com.brandon.Database.UserService;
import com.brandon.Router.Routable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserRemoveServlet extends HttpServlet implements Routable {

    @Override
    public String getMapping() {
        return "/remove";
    }

    UserService userService;

    public UserRemoveServlet(UserService userService){
        this.userService = userService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");

        System.out.println("Retrieved request to remove user " + username);

        if(SecurityService.SessionAuthorized(req)){
            userService.removeUser(username);

        }
        resp.sendRedirect("/users");
    }
}
