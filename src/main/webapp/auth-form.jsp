<%--
  Created by IntelliJ IDEA.
  User: decafmango
  Date: 28.09.2023
  Time: 21:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <style><%@include file="style.css"%></style>
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
            Laboratory Work №2<br>
            Variant: 17113<br>
            Student: Разинкин Александр<br>
            Group: P3207
        </p>
    </details>
</header>
<main>
    <fieldset>
        <legend>Авторизация</legend>
        <label class="hidden" for="login-input">111</label>
        <input id="login-input" type="text" placeholder="Enter login" name="X-User-Login">
        <label class="hidden" for="password-input">111</label>
        <input id="password-input" type="password" placeholder="Enter password" name="X-User-Password">
        <input id="submit-button" type="submit" value="Submit">
        <div class="auth-type-container">
            <div class="login-container"></div>
            <label for="login-radio">Login</label>
            <input id="login-radio" type="radio" value="login" name="Auth-Type" checked>
        </div>
        <div class="register-container">
            <label for="register-radio">Register</label>
            <input id="register-radio" type="radio" value="register" name="Auth-Type">
        </div>
        </div>
    </fieldset>
</main>
<footer>

</footer>
<script><%@ include file="script/AuthFormScript.js"%></script>
</body>
</html>
