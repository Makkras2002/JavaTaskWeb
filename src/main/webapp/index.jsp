<html>
<head>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <title>AutoShopPlus</title>
    <script type="text/javascript">
        function disableBack() { window.history.forward(); }
        setTimeout("disableBack()", 0);
        window.onunload = function () { null };
    </script>
</head>
<body>
<jsp:forward page="pages/main_client_wrapper.jsp"></jsp:forward>
</body>
</html>
