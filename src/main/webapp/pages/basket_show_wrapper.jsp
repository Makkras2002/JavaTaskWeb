<%--
  Created by IntelliJ IDEA.
  User: max20
  Date: 29.12.2021
  Time: 15:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>Basket Show Page Wrapper</title>
    <script type="text/javascript">
        function disableBack() { window.history.forward(); }
        setTimeout("disableBack()", 0);
        window.onunload = function () { null };
    </script>
</head>
<body class="p-2 bg-primary text-black bg-opacity-50" onkeydown="return (event.keyCode != 116)">
<form method="post" action="${pageContext.request.contextPath}/controller" name="get_prod_data_wrapper">
    <input type="hidden" name="command" id="command" value="open_user_basket">
</form>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/main_client_wrapper_form_autosubmitter.js"></script>
</body>
</html>
