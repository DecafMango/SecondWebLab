package com.decafmango.secondweblab;

import com.decafmango.secondweblab.dao.user_dao.UserRepository;
import com.decafmango.secondweblab.model.User;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

public class ControllerServlet extends HttpServlet {

    @EJB
    private UserRepository userRepository;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

    }

    private Optional<User> checkUser(String login) {
        return userRepository.getUserByLogin(login);
    }

}
