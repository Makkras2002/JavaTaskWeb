<%--
  Created by IntelliJ IDEA.
  User: max20
  Date: 13.12.2021
  Time: 17:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Auth/Reg</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body class="p-2 bg-primary text-black bg-opacity-50">
<div align="center" class="align-top">
    <div class="col-md-6">
        <div class="display-3">Регистрация</div>
    </div>
</div>
<div align="center" class="align-middle">
    <form action="/controller" method="post">
        <input type="text" id="command" name="command" hidden="hidden" required="required" value="registration"/>
        <div class="mb-3 w-25" style="margin-top: 40px">
            <label for="login" class="form-label" >Введите ваш логин</label>
            <input type="text" class="form-control" id="login" name="login" required="required"/>
        </div>
        <div class="mb-3 w-25" style="margin-top: 40px">
            <label for="email" class="form-label" >Введите вашу почту</label>
            <input type="text" class="form-control" id="email" name="email" required="required"/>
        </div>
        <div class="mb-3 w-25" style="margin-top: 40px">
            <label for="password" class="col-form-label">Введите ваш пароль</label>
        </div>
        <div class="mb-3 w-25">
            <input type="password" id="password" name="password" class="form-control" aria-describedby="passwordHelpInline" required="required">
        </div>
        <div class="col-auto">
            <span id="passwordHelpInline" class="form-text">
                Пароль должен быть 8-20 символов вдлину.
            </span>
        </div>
        <div class="mb-3 w-25 form-check form-switch" style="margin-top: 40px" align="left">
            <input class="form-check-input" type="checkbox" role="switch" id="enterAsAdmin">
            <label class="form-check-label" for="enterAsAdmin">Войти как администратор</label>
        </div>
        <button type="submit " class="btn btn-outline-success btn-lg" style="margin-top: 40px">Зарегистрироваться</button>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
</body>
</html>
