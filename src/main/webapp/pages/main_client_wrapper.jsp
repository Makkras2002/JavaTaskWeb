<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>Main Client Wrapper</title>
    <script type="text/javascript">
        function disableBack() { window.history.forward(); }
        setTimeout("disableBack()", 0);
        window.onunload = function () { null };
    </script>
</head>
<body class="p-2 bg-primary text-black bg-opacity-50" onkeydown="return (event.keyCode != 116)">
<form method="post" action="${pageContext.request.contextPath}/controller" name="get_prod_data_wrapper">
    <input type="hidden" name="command" id="command" value="prepare_main_client_page">
</form>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/main_client_wrapper_form_autosubmitter.js"></script>
</body>
</html>
