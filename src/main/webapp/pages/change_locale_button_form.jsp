<%--
  Created by IntelliJ IDEA.
  User: max20
  Date: 23.12.2021
  Time: 17:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="localized_text"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <title>Change Locale</title>
</head>
<body onkeydown="return (event.keyCode != 116)">
<div class="row">
    <div class="col-4">
        ${locale}
    </div>
    <div class="col-4">
        <form method="post" action="/controller">
            <input type="hidden" name="command" value="change_locale"/>
            <input type="hidden" name="path" value="${pageContext.request.requestURL}"/>
            <button type="submit" class="btn btn-primary btn-sm">
                <fmt:message key="main_client.change_locale"/>
            </button>
        </form>
    </div>
</div>

</body>
</html>
