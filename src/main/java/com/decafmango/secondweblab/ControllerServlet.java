package com.decafmango.secondweblab;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ControllerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/login") || pathInfo.equals("/register"))
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        else if (pathInfo.equals("/check")) {
            request.getRequestDispatcher("/check").forward(request, response);
        }
    }

}
