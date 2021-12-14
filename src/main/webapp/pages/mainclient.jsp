<%@ page import="java.util.List" %>
<%@ page import="com.makkras.shop.entity.Product" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.makkras.shop.dao.ProductDao" %>
<%@ page import="com.makkras.shop.dao.impl.ProductDaoImpl" %>
<%@ page import="com.makkras.shop.exception.InteractionException" %>
<html>
<head>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <title>AutoShopPlus</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body class="p-2 bg-primary text-black bg-opacity-50">
<div class="container">
    <div class="row">
        <div class="row justify-content-between">
            <div class="col-4">
                Магазин Автозапчасти-Плюс
            </div>
            <div class="col-4">
                <div class="row">
                    <div class="col-4">
                        Локаль
                    </div>
                    <div class="col-4">
                        Профиль
                    </div>
                    <div class="col-4">
                        Корзина
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row" style="margin-top: 60px">
        <table class="table">
            <tr>
                <td>ID</td>
                <td>Название</td>
                <td>Категория</td>
                <td>Цена</td>
                <td>Изображение</td>
                <td>Комментарий</td>
            </tr>
<%--                <%--%>
<%--                ProductDao productDao = new ProductDaoImpl();--%>
<%--                List<Product> products = null;--%>
<%--                try {--%>
<%--                products = productDao.findAllProductInStock();} catch (InteractionException e) {--%>
<%--                e.printStackTrace();--%>
<%--                }--%>
<%--                int counter = 0;--%>
<%--                while (counter < products.size()){--%>
<%--                    %><tr>--%>
<%--            <td><form id="<%=counter%>" action="/addToBucket" method="post"><input type="number" name="id" value="<%=products.get(counter).getProductId()%>" readonly="readonly" required="required"/></form><td>--%>
<%--            <td><%=products.get(counter).getProductName()%><td>--%>
<%--            <td><%=products.get(counter).getProductCategory()%><td>--%>
<%--            <td><%=products.get(counter).getProductPrice()%><td>--%>
<%--            <td><img src="<%=products.get(counter).getPicturePath()%>" class="img-fluid" alt="<%=products.get(counter).getProductName()%>"><td>--%>
<%--            <td><%=products.get(counter).getProductComment()%><td>--%>
<%--            <td><input form="<%=counter%>" type="range" min="0" max="1000000" step="1" name="orderedProductAmount" required="required"><td>--%>
<%--            <td><input form="<%=counter%>" type="submit" class="btn-outline-success"/></td>--%>
<%--                <%--%>
<%--                    counter++;--%>
<%--                }--%>
<%--            %>--%>
    </div>
</div>
</div>













<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
</body>
</html>
