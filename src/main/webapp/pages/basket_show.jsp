<%--
  Created by IntelliJ IDEA.
  User: max20
  Date: 29.12.2021
  Time: 14:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="localized_text"/>
<html>
<head>
    <title>Basket</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script type="text/javascript">
        function disableBack() { window.history.forward(); }
        setTimeout("disableBack()", 0);
        window.onunload = function () { null };
    </script>
</head>
<body class="p-2 bg-primary text-black bg-opacity-50" onkeydown="return (event.keyCode != 116)">
<div class="container">
    <div class="row">
        <div class="row justify-content-between">
            <div class="col-4">
                <fmt:message key="main_client.shop_name"/>
            </div>
            <div class="col-4">
                <div class="row">
                    <div class="col-4">
                        <%@ include file="change_locale_button_form.jsp"%>
                    </div>
                    <div class="col-4">
                        <a href="${pageContext.request.contextPath}/index.jsp" style="width: 160px" class="btn btn-primary btn-sm"><fmt:message key="user_form.catalog"/></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="orderData" style="display: none">
        ${productsInOrder}
    </div>
    <div id="prod_name" style="display: none">
        <fmt:message key="main_client.prod_name"/>
    </div>
    <div id="prod_category" style="display: none">
        <fmt:message key="main_client.prod_category"/>
    </div>
    <div id="prod_price" style="display: none">
        <fmt:message key="main_client.prod_price"/>
    </div>
    <div id="prod_img" style="display: none">
        <fmt:message key="main_client.prod_image"/>
    </div>
    <div id="prod_amount" style="display: none">
        <fmt:message key="main_client.prod_amount"/>
    </div>
    <div id="prod_remove_from_basket" style="display: none">
        <fmt:message key="main_client.prod_remove_from_basket"/>
    </div>
    <div id="empty_order" style="display: none">
        <fmt:message key="main_client.empty_order"/>
    </div>
    <div class="row" style="margin-top: 60px">
        <table class="table" >
            <tbody id="productTable">
            </tbody>
        </table>
    </div>
    <div class="row" style="margin-top: 60px">
        <div class="row">
            <form method="post" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="command" id="command" value="make_order"/>
                <button type="submit" class="btn btn-outline-warning btn-lg"><fmt:message key="main_client.make_order"/></button>
            </form>
        </div>
    </div>
</div>







<script type="text/javascript" src="${pageContext.request.contextPath}/js/order_page_handler.js"/>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
</body>
</html>
