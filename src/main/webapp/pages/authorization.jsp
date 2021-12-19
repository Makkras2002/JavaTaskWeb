<%--
  Created by IntelliJ IDEA.
  User: max20
  Date: 13.12.2021
  Time: 17:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<html>
<head>
    <title>Auth/Reg</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body class="p-2 bg-primary text-black bg-opacity-50">
<div align="center" class="align-top">
    <div class="col-md-6">
        <div class="display-3">Вход</div>
    </div>
</div>
<div align="center" class="align-middle">
    <form action="/controller" method="post">
        <input type="text" id="command" name="command" hidden="hidden" required="required" value="login"/>
        <div class="mb-3 w-25" style="margin-top: 40px">
            <label for="loginOrEmail" class="form-label" >Введите ваш логин</label>
            <input type="text" class="form-control" id="loginOrEmail" name="login" required="required"/>
        </div>
        <div class="mb-3 w-25" style="margin-top: 40px">
            <label for="password" class="form-label">Введите ваш пароль</label>
            <input type="password" class="form-control" id="password" name="password" required="required"/>
        </div>
        <div class="mb-3 w-25 form-check form-switch" style="margin-top: 40px" align="left">
            <input class="form-check-input" type="checkbox" role="switch" id="enterAsAdmin" name="enterAsAdmin">
            <label class="form-check-label" for="enterAsAdmin">Войти как администратор</label>
        </div>
        <button type="submit " class="btn btn-outline-success btn-lg" style="margin-top: 40px">Войти</button>
    </form>
</div>
<div align="center" style="margin-top: 160px">
    <div style="color: red; font-weight: bolder; font-style: italic">${errorAuthMessage}</div>
    <a href="/pages/registration.jsp" class="text-black">Регистрация</a>
    <br/>
    <a href="/pages/mainclient.jsp" class="text-black">Каталог</a>
    <br/>
</div>


<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
</body>
</html>
