package com.decafmango.secondweblab;

import com.decafmango.secondweblab.dao.attempt_dao.AttemptRepository;
import com.decafmango.secondweblab.dao.user_dao.UserRepository;
import com.decafmango.secondweblab.model.User;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ControllerServlet extends HttpServlet {

    @EJB
    private UserRepository userRepository;
    @EJB
    private AttemptRepository attemptRepository;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        request.setAttribute("X-From-Controller", true);
        if (pathInfo == null) {
            request.setAttribute("X-User-Repository", userRepository);
            request.setAttribute("X-Attempt-Repository", attemptRepository);
            request.getRequestDispatcher("/input-form.jsp").forward(request, response);
        } else if (pathInfo.equals("/check")) {
            request.getRequestDispatcher("/check").forward(request, response);
        } else if (pathInfo.equals("/login") || pathInfo.equals("/register")) {
            request.getRequestDispatcher("/auth-form.jsp").forward(request, response);
        } else if (pathInfo.equals("/reset")) {
            System.out.println("reset");
            attemptRepository.deleteUserAttempts(userRepository.getUserByLogin((String) request.getSession().getAttribute("X-User-Login")).get());
        } else {
            response.setStatus(404);
        }
    }

}
