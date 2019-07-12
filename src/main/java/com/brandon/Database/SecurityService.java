package com.brandon.Database;

import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.servlet.http.HttpServletRequest;

public class SecurityService {

    public static String encryptPassword(String password){
        return BCrypt.hashpw(password,BCrypt.gensalt());
    }

    private static boolean correctPassword(User user, String password){
        return BCrypt.checkpw(password,user.getPassword());
    }

    public static boolean AuthenticateUser(UserService userService, String username, String password){
        User user = userService.getUser(username);
        if(user != null){
            return correctPassword(user, password);
        }
        return false;
    }

    public static boolean SessionAuthorized(HttpServletRequest req){
        return req.getSession().getAttribute("username") != null;
    }
}
