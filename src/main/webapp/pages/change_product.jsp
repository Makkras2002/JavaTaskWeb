<html>
<head>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ page isELIgnored="false" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    <fmt:setLocale value="${locale}" scope="session" />
    <fmt:setBundle basename="localized_text"/>
    <title>AutoPartsPlus</title>
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
        </div>
    </div>
    <div id="categoriesForSearch" style="display: none">
        ${productCategories}
    </div>
    <div id="productDataStartCategory" style="display: none">
        ${product_for_change_data.category}
    </div>
    <div id="productDataStartName" style="display: none">${product_for_change_data.name}</div>
    <div class="row" style="margin-top: 60px" align="center">
        <h2><fmt:message key="main_admin.change_product_form_name"/></h2>
        <form method="post" action="/controller" name="add_product_form" id="add_product_form">
            <input type="hidden" id="command" name="command" required="required" value="change_product_data"/>
            <input type="hidden" id="product_id" name="product_id" value="${product_for_change_data.product_id}" required="required"/>
            <div class="mb-3 w-25" style="margin-top: 40px">
                <label for="name" class="form-label" ><fmt:message key="main_client.prod_name"/></label>
                <input type="text" class="form-control" id="name" name="name" value="${product_for_change_data.name}" required="required"/>
            </div>
            <div class="mb-3 w-25" style="margin-top: 40px">
                <label for="price" class="form-label"><fmt:message key="main_client.prod_price"/></label>
                <input type="number" min="1" step="0.01" class="form-control" id="price" value="${product_for_change_data.price}" name="price" required="required"/>
            </div>
            <div class="mb-3 w-25" style="margin-top: 40px">
                <label for="categories" class="form-label" > <fmt:message key="main_client.prod_category"/></label>
                <select class="form-select" id="categories" name="category">
                </select>
            </div>
            <div class="mb-3  w-25">
                <label for="image" class="form-label"><fmt:message key="main_client.prod_image"/></label>
                <input class="form-control" type="file" id="image" name="image">
            </div>
            <div class="mb-3 w-25" style="margin-top: 40px">
                <label for="comment" class="form-label" ><fmt:message key="main_client.prod_comment"/></label>
                <textarea class="form-control" id="comment" name="comment" rows="3" required="required">${product_for_change_data.comment}</textarea>
            </div>
            <div class="mb-3 w-25 form-check form-switch" style=" margin-top: 40px" align="left">
                <input class="form-check-input" type="checkbox" role="switch" id="status" name="status"/>
                <label class="form-check-label" for="status"><fmt:message key="main_admin.in_stock_status"/></label>
            </div>
            <button type="submit" id="submitButton" class="btn btn-outline-warning"><fmt:message key="main_admin.change_product"/></button>
        </form>
    </div>
    <div align="center" style="margin-top: 160px">
        <div style="color: red; font-weight: bolder; font-style: italic">${errorChangeProductMessage}</div>
        <form method="post" action="/controller">
            <input type="hidden" name="command" id="command8" value="prepare_main_admin_page"/>
            <button type="submit" class="btn btn-outline-primary"><fmt:message key="main_admin.enter_menu"/></button>
        </form>
    </div>
</div>
</div>











<script type="text/javascript" src="${pageContext.request.contextPath}/js/change_product_page_handler.js"/>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
</body>
</html>
