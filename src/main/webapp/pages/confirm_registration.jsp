<%--
  Created by IntelliJ IDEA.
  User: max20
  Date: 16.12.2021
  Time: 19:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>ConfReg</title>
</head>
<body class="p-2 bg-primary text-black bg-opacity-50">
<form method="post" action="/controller" name="confregform">
    <input type="hidden" name="command" id="command" value="confirm_registration">
</form>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/autoformsubmitter.js"></script>
</body>
</html>
