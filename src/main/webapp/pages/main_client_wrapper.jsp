<%--
  Created by IntelliJ IDEA.
  User: max20
  Date: 20.12.2021
  Time: 17:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>Main Client Wrapper</title>
</head>
<body class="p-2 bg-primary text-black bg-opacity-50">
<form method="post" action="/controller" name="get_prod_data_wrapper">
    <input type="hidden" name="command" id="command" value="prepare_main_client_page">
</form>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/main_client_wrapper_form_autosubmitter.js"></script>
</body>
</html>
