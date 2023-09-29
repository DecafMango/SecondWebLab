package com.decafmango.secondweblab;

import com.decafmango.secondweblab.dao.user_dao.UserRepository;
import com.decafmango.secondweblab.model.User;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.Optional;

public class UserServlet extends HttpServlet {

    @EJB
    private UserRepository userRepository;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getHeader("X-User-Login");
        String password = request.getHeader("X-User-Password");

        if (login == null || password == null) {
            response.sendRedirect(request.getContextPath() + "/authorization.jsp");
            return;
        }


        String pathInfo = request.getPathInfo();
        Optional<User> userOptional = checkUser(login);
        switch (pathInfo) {
            case "/login":
                if (userOptional.isEmpty()) {
                    response.setStatus(404);
                    response.getWriter().println("User " + login + " not found");
                    return;
                }
                if (!userOptional.get().getPassword().equals(password)) {
                    response.setStatus(418);
                    response.getWriter().println("Incorrect password for user " + login);
                    return;
                }
                System.out.println("logged in");
                break;
            case "/register":
                if (userOptional.isPresent()) {
                    response.setStatus(418);
                    response.getWriter().println("User with login " + login + " is present");
                    return;
                }
                userRepository.saveUser(new User(login, password));
                System.out.println("registered");
                break;
            default:
                response.setStatus(404);
        }
    }

    private Optional<User> checkUser(String login) {
        return userRepository.getUserByLogin(login);
    }
}
