package com.decafmango.secondweblab;

import com.decafmango.secondweblab.dao.attempt_dao.AttemptRepository;
import com.decafmango.secondweblab.dao.user_dao.UserRepository;
import com.decafmango.secondweblab.model.User;
import com.decafmango.secondweblab.model.attempt.Attempt;
import com.decafmango.secondweblab.model.attempt.AttemptMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

public class AreaCheckServlet extends HttpServlet {

    @EJB
    private UserRepository userRepository;
    @EJB
    private AttemptRepository attemptRepository;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String xStr = request.getParameter("x");
        String yStr = request.getParameter("y");
        String rStr = request.getParameter("r");

        if (xStr == null || yStr == null || rStr == null) {
            response.setStatus(400);
            response.getWriter().print("Request should contain the query string of x, y, r parameters");
            return;
        }

        float x;
        float y;
        int r;

        try {
            x = Float.parseFloat(xStr);
            y = Float.parseFloat(yStr);
            r = Integer.parseInt(rStr);
        } catch (NumberFormatException e) {
            response.setStatus(400);
            response.getWriter().println("x is float value");
            response.getWriter().println("y is float value");
            response.getWriter().println("r is integer value");
            return;
        }

        if (!(List.of(-2f, -1.5f, -1f, -0.5f, 0f, 0.5f, 1f, 1.5f, 2f).contains(x))) {
            response.setStatus(400);
            response.getWriter().println("x should be value of [-2, -1.5, -1, -0.5, 0, 0.5, 1, 1.5, 2]");
        }
        if (!(y > -3 && y < 5)) {
            response.setStatus(400);
            response.getWriter().println("y should be value of (-3; 5)");
        }
        if (!(List.of(1, 2, 3, 4, 5).contains(r))) {
            response.setStatus(400);
            response.getWriter().print("r should be value of [1, 2, 3, 4, 5]");
        }

        if (response.getStatus() == 400)
            return;

        LocalDateTime attemptTime = LocalDateTime.now();

        Instant scriptStartTime = Instant.now();
        boolean isHit = checkHit(x, y, r);
        Instant scriptEndTime = Instant.now();
        Duration scriptDuration = Duration.between(scriptStartTime, scriptEndTime);

        HttpSession session = request.getSession();
        String login = (String) session.getAttribute("X-User-Login");
        String loginHeader = request.getHeader("X-User-Login");

        User user = userRepository.getUserByLogin(login == null ? loginHeader : login).get();
        Attempt attempt = new Attempt(x, y, r, isHit, attemptTime, scriptDuration, user);
        attemptRepository.saveAttempt(attempt);

        System.out.println(attemptRepository.getUserAttempts(user));

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValueAsString(attempt);
        response.getWriter().print(objectMapper.writeValueAsString(AttemptMapper.toAttemptDto(attempt)));
    }

    private boolean checkHit(float x, float y, int r) {
        if (x > 0 && y > 0)
            return false;
        else if ((x <= 0 && x >= (float) -r / 2) && y >= 0)
            return y <= (2 * x + r);
        else if ((x <= 0 && x >= -r) && y <= 0)
            return Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(r, 2);
        else return (x >= 0 && x <= r) && (y <= 0 && y >= (float) -r / 2);
    }
}
