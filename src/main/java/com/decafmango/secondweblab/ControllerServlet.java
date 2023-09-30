package com.decafmango.secondweblab;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ControllerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        request.setAttribute("X-From-Controller", true);
        if (pathInfo == null)
            request.getRequestDispatcher("/input-form.jsp").forward(request, response);
        else if (pathInfo.equals("/check")) {
            request.getRequestDispatcher("/check").forward(request, response);
        } else if (pathInfo.equals("/login") || pathInfo.equals("/register")) {
            Cookie loginCookie = new Cookie("X-User-Login", request.getHeader("X-User-Login"));
            Cookie passwordCookie = new Cookie("X-User-Password", request.getHeader("X-User-Password"));
            response.addCookie(loginCookie);
            response.addCookie(passwordCookie);
        }
    }

}
