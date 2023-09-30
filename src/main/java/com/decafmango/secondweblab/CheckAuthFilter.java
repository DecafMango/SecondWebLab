package com.decafmango.secondweblab;

import com.decafmango.secondweblab.dao.user_dao.UserRepository;
import com.decafmango.secondweblab.model.User;
import jakarta.ejb.EJB;
import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

public class CheckAuthFilter implements Filter {

    @EJB
    private UserRepository userRepository;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String pathInfo = request.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/check"))
            checkCookies(request, response);
        else if (pathInfo.equals("/login"))
            checkLogin(request, response);
        else if (pathInfo.equals("/register"))
            checkRegister(request, response);
        else
            response.setStatus(404);
    }

    private void checkCookies(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Cookie[] cookies = request.getCookies();

        Cookie loginCookie = null;
        Cookie passwordCookie = null;

        // Check cookies on existence
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("X-User-Login"))
                    loginCookie = cookie;
                if (cookie.getName().equals("X-User-Password"))
                    passwordCookie = cookie;
            }
        } else {
            request.getServletContext().getNamedDispatcher("authorization.jsp").forward(request, response);
            return;
        }

        if (loginCookie == null || passwordCookie == null) {
            response.setStatus(401);
            request.getRequestDispatcher("/authorization.jsp").forward(request, response);
            return;
        }

        Optional<User> userOptional = checkUser(loginCookie.getValue());
        if (userOptional.isPresent()) {
            if (userOptional.get().getPassword().equals(passwordCookie.getValue()))
                request.getServletContext().getNamedDispatcher("ControllerServlet").forward(request, response);
            else {
                response.setStatus(401);
                request.getRequestDispatcher("/authorization.jsp").forward(request, response);
            }
        } else {
            response.setStatus(401);
            request.getRequestDispatcher("/authorization.jsp").forward(request, response);
        }
    }

    private void checkLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String login = request.getHeader("X-User-Login");
        String password = request.getHeader("X-User-Password");

        if (login == null || password == null) {
            response.setStatus(401);
            request.getRequestDispatcher("authorization.jsp").forward(request, response);
            return;
        }

        Optional<User> userOptional = checkUser(login);

        if (userOptional.isPresent()) {
            if (userOptional.get().getPassword().equals(password)) {
                request.getServletContext().getNamedDispatcher("ControllerServlet").forward(request, response);
            } else {
                response.setStatus(401);
                request.getRequestDispatcher("/authorization.jsp").forward(request, response);
            }
        } else {
            response.setStatus(401);
            request.getRequestDispatcher("/authorization.jsp").forward(request, response);
        }
    }

    private void checkRegister(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String login = request.getHeader("X-User-Login");
        String password = request.getHeader("X-User-Password");

        if (login == null || password == null) {
            response.setStatus(401);
            request.getRequestDispatcher("authorization.jsp").forward(request, response);
            return;
        }

        Optional<User> userOptional = checkUser(login);

        if (userOptional.isPresent()) {
            response.setStatus(418);
            request.getRequestDispatcher("authorization.jsp").forward(request, response);
        } else {
            userRepository.saveUser(new User(login, password));
            request.getServletContext().getNamedDispatcher("ControllerServlet").forward(request, response);
        }
    }

    private Optional<User> checkUser(String login) {
        return userRepository.getUserByLogin(login);
    }

}
