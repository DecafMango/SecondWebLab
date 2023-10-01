<%@ page import="com.decafmango.secondweblab.dao.attempt_dao.AttemptRepository" %>
<%@ page import="com.decafmango.secondweblab.model.attempt.Attempt" %>
<%@ page import="com.decafmango.secondweblab.model.User" %>
<%@ page import="com.decafmango.secondweblab.dao.user_dao.UserRepository" %><%--
  Created by IntelliJ IDEA.
  User: decafmango
  Date: 30.09.2023
  Time: 00:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Input page</title>
</head>
<body>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <style><%@include file="styles.css"%></style>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Borel&family=Caveat:wght@400;500;600;700&family=Epilogue:ital@1&family=Noto+Sans+Vithkuqi&family=Roboto&display=swap" rel="stylesheet">
    <title>Auth Page</title>
</head>
<body>
<header>
    <details>
        <summary><img src="https://se.ifmo.ru/o/helios-theme/images/cs_logo.png" alt="duck logo"></summary>
        <p>
            Laboratory work №2<br>
            Variant: 17113<br>
            Student: Razinkin Alexander<br>
            Group: P3207
        </p>
    </details>
</header>
<main>
    <canvas id="field-canvas" width="415" height="415"></canvas>
    <div class="input-from-container">
        <h1 class="form-title">
            Try to hit the area
        </h1>
        <div class="form-fields">
            <fieldset class="form-field">
                <legend>
                    Choose X
                </legend>
                <input id="x-checkbox--2" type="checkbox" name="x" value="-2">
                <label for="x-checkbox--2">-2</label>

                <input id="x-checkbox--1.5" type="checkbox" name="x" value="-1.5">
                <label for="x-checkbox--1.5">-1.5</label>

                <input id="x-checkbox--1" type="checkbox" name="x" value="-1">
                <label for="x-checkbox--1">-1</label>

                <input id="x-checkbox--0.5" type="checkbox" name="x" value="-0.5">
                <label for="x-checkbox--0.5">-0.5</label>

                <input id="x-checkbox-0" type="checkbox" name="x" value="0">
                <label for="x-checkbox-0">0</label>

                <input id="x-checkbox-0.5" type="checkbox" name="x" value="0.5">
                <label for="x-checkbox-0.5">0.5</label>

                <input id="x-checkbox-1" type="checkbox" name="x" value="1">
                <label for="x-checkbox-1">1</label>

                <input id="x-checkbox-1.5" type="checkbox" name="x" value="1.5">
                <label for="x-checkbox-1.5">1.5</label>

                <input id="x-checkbox-2" type="checkbox" name="x" value="2">
                <label for="x-checkbox-2">2</label>
            </fieldset>
            <fieldset class="form-field">
                <legend>
                    Choose Y
                </legend>
                <input type="text" placeholder="Введите значение от -3 до 5">
            </fieldset>
            <fieldset class="form-field">
                <legend>
                    Choose R
                </legend>
                <button type="button">1</button>
                <button type="button">2</button>
                <button class="button-pressed" type="button">3</button>
                <button type="button">4</button>
                <button type="button">5</button>
            </fieldset>
        </div>
        <div class="form-buttons">
            <button class="submit-button" type="submit">Submit</button>
            <button class="exit-button" type="submit">Exit</button>
        </div>
    </div>
    <div class="results-table-container">
        <button class="reset-table-button">Reset table</button>
        <table class="results-table">
            <tr class="table-header-row">
                <th>X</th>
                <th>Y</th>
                <th>R</th>
                <th>Date and Time</th>
                <th>Duration, ns.</th>
                <th>Result</th>
            </tr>
            <%
                UserRepository userRepository = (UserRepository) request.getAttribute("X-User-Repository");
                AttemptRepository attemptRepository = (AttemptRepository) request.getAttribute("X-Attempt-Repository");
                User user = userRepository.getUserByLogin((String) session.getAttribute("X-User-Login")).get();

                for (Attempt attempt : attemptRepository.getUserAttempts(user)) {
                    out.println("<tr class='table-data-row'>");
                    out.print("<td>" + attempt.getX() + "</td>");
                    out.print("<td>" + attempt.getY() + "</td>");
                    out.print("<td>" + attempt.getR() + "</td>");
                    out.print("<td>" + attempt.getAttemptTime() + "</td>");
                    out.print("<td>" + attempt.getScriptDuration() + "</td>");
                    out.print("<td>" + attempt.isHit() + "</td>");
                    out.println("</tr>");
                }

            %>

        </table>
    </div>
</main>
<script><%@include file="script/InputFormScript.js"%></script>
</body>
</html>
