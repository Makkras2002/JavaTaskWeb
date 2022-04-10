<%--
  Created by IntelliJ IDEA.
  User: max20
  Date: 02.04.2022
  Time: 13:43
  To change this template use File | Settings | File Templates.
--%>
<head>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ page isELIgnored="false" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <div class="row" style="margin-top: 60px">
        <h2><fmt:message key="main_admin.products_selling_statistics"/></h2>
        <div id="piechart"></div>
        <div id="piechartddd" style="display: none">${products_selling_statistics}</div>
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

        <script type="text/javascript">
            google.charts.load('current', {'packages':['corechart']});
            google.charts.setOnLoadCallback(drawChart);
            function drawChart() {
                let data = google.visualization.arrayToDataTable([
                    ['Product', 'Amount'],
                    <c:forEach var="statistics_data"  items="${products_selling_statistics}">
                    ['<c:out value="${statistics_data.key}" escapeXml="false"/>',<c:out value="${statistics_data.value}"/>],
                    </c:forEach>
                    ['xxx', 0]
                ]);
                let options = {'title': '<fmt:message key="main_admin.products_demand_ratio_statistics"/>','height':600};
                let chart = new google.visualization.PieChart(document.getElementById('piechart'));
                chart.draw(data, options);
            }
        </script>

    </div>
    <div class="row" style="margin-top: 20px">
        <h2><fmt:message key="main_admin.orders"/></h2>
        <form method="post" action="${pageContext.request.contextPath}/controller">
            <input type="hidden" name="command" id="command11" value="prepare_view_orders_page"/>
            <button type="submit" class="btn btn-success btn-lg"><fmt:message key="main_admin.view_all_orders"/></button>
        </form>
    </div>
</div>
</div>











<script type="text/javascript" src="${pageContext.request.contextPath}/js/orders_admin_view_handler.js"/>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
</body>
</html>
