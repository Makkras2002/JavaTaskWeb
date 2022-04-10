<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="localized_text"/>
<html>
<head>
    <title>Auth/Reg</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script type="text/javascript">
        function disableBack() { window.history.forward(); }
        setTimeout("disableBack()", 0);
        window.onunload = function () { null };
    </script>
</head>
<body class="p-2 bg-primary text-black bg-opacity-50" onkeydown="return (event.keyCode != 116)">
<div align="center" class="align-top">
    <div class="col-md-6">
        <div class="display-3"><fmt:message key="registration.registration_label"/></div>
    </div>
</div>
<div align="center" class="align-middle">
    <form action="${pageContext.request.contextPath}/controller" method="post">
        <input type="text" id="command" name="command" hidden="hidden" required="required" value="register_admin"/>
        <div class="mb-3 w-25" style="margin-top: 40px">
            <label for="login" class="form-label" ><fmt:message key="user_form.enter_login"/></label>
            <input type="text" class="form-control" id="login" name="login" value="${registration_form_data_map.form_login}" required="required"/>
        </div>
        <div class="mb-3 w-25" style="margin-top: 40px">
            <label for="email" class="form-label" ><fmt:message key="user_form.enter_gmail"/></label>
            <input type="email" class="form-control" id="email" name="email" value="${registration_form_data_map.form_email}" required="required"/>
        </div>
        <div class="mb-3 w-25" style="margin-top: 40px">
            <label for="password" class="col-form-label"><fmt:message key="user_form.enter_password"/></label>
        </div>
        <div class="mb-3 w-25">
            <input type="password" id="password" name="password" class="form-control" value="${registration_form_data_map.form_password}" maxlength="20" minlength="8" aria-describedby="passwordHelpInline" required="required">
        </div>
        <div class="col-auto">
            <span id="passwordHelpInline" class="form-text">
                <fmt:message key="user_form.password_requirements"/>
            </span>
        </div>
        <button type="submit" class="btn btn-outline-success btn-lg" style="margin-top: 40px"><fmt:message key="user_form.register"/></button>
    </form>
</div>
<div align="center" style="margin-top: 100px">
    <div style="color: red; font-weight: bolder; font-style: italic">${errorRegistrationMessage}</div>
    <form method="post" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" id="command8" value="prepare_main_admin_page"/>
        <button type="submit" class="btn btn-outline-primary"><fmt:message key="main_admin.enter_menu"/></button>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
</body>
</html>
