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
            <div class="col-4">
                <div class="row">
                    <div class="col-4">
                        <%@ include file="change_locale_button_form.jsp"%>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="orderData" style="display: none">
        ${orders}
    </div>
    <div id="order_user_login" style="display: none">
        <fmt:message key="main_admin.order_user_login"/>
    </div>
    <div id="order_user_email" style="display: none">
        <fmt:message key="main_admin.order_user_email"/>
    </div>
    <div id="order_status" style="display: none">
        <fmt:message key="main_admin.order_is_completed"/>
    </div>
    <div id="order_status_completed" style="display: none">
        <fmt:message key="main_admin.completed_status_completed"/>
    </div>
    <div id="order_status_not_completed" style="display: none">
        <fmt:message key="main_admin.completed_status_not_completed"/>
    </div>
    <div id="order_date" style="display: none">
        <fmt:message key="main_admin.order_date"/>
    </div>
    <div id="order_complete" style="display: none">
        <fmt:message key="main_admin.complete_order"/>
    </div>
    <div id="order_components" style="display: none">
        <fmt:message key="main_admin.order_components"/>
    </div>
    <div id="empty_orders" style="display: none">
        <fmt:message key="main_admin.empty_orders"/>
    </div>
    <div class="row" style="margin-top: 60px">
        <h2><fmt:message key="main_admin.orders"/></h2>
        <div class="row" style="margin-top: 40px">
            <div class="col-4">
                <form action="${pageContext.request.contextPath}/controller" method="post">
                    <label for="command4"><fmt:message key="main_admin.sort_orders"/></label>
                    <select class="form-select" name="command" id="command4" aria-label="Default select example" required="required">
                        <option value="prepare_view_orders_page">-</option>
                        <option value="sort_orders_by_date"><fmt:message key="main_admin.sort_orders_by_date"/></option>
                        <option value="sort_orders_by_user_login"><fmt:message key="main_admin.sort_orders_by_login"/></option>
                    </select>
                    <button type="submit" class="btn btn-outline-success btn-sm" style="margin-top: 20px"><fmt:message key="main_admin.sort_orders"/></button>
                </form>
            </div>
            <div class="col-4">
                <button type="button" class="btn btn-outline-success btn-lg" data-bs-toggle="modal" data-bs-target="#staticBackdrop4">
                    <fmt:message key="main_admin.search_order"/>
                </button>
                <div class="modal fade" id="staticBackdrop4" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="staticBackdropLabel4"><fmt:message key="main_admin.search_order"/> </h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <p><fmt:message key="main_admin.search_order_explanation"/></p>
                                <form action="${pageContext.request.contextPath}/controller" method="post">
                                    <input type="text" id="command5" name="command" hidden="hidden" required="required" value="find_order"/>
                                    <div class="mb-3 w-25" style="margin-top: 40px" width="300px">
                                        <label for="login" class="form-label" ><fmt:message key="user_form.enter_login"/></label>
                                        <input type="text" maxlength="100" class="form-control" id="login" name="login" />
                                    </div>
                                    <div class="mb-3 w-25" style="margin-top: 40px" width="300px">
                                        <label for="completion_status" class="form-label" ><fmt:message key="main_admin.product_status"/></label>
                                        <select class="form-select" id="completion_status" name="completion_status" aria-label="Default select example">
                                            <option value="-" selected="selected">-</option>
                                            <option value="completed"><fmt:message key="main_admin.completed_status_completed"/></option>
                                            <option value="in_progress"><fmt:message key="main_admin.completed_status_not_completed"/></option>
                                        </select>
                                    </div>
                                    <div class="mb-3 w-25" style="margin-top: 40px" width="300px">
                                        <label for="start_date" class="form-label" ><fmt:message key="main_admin.enter_order_start_date"/></label>
                                        <input type="date"  class="form-control" id="start_date" name="start_date" />
                                    </div>
                                    <div class="mb-3 w-25" style="margin-top: 40px" width="300px">
                                        <label for="end_date" class="form-label" ><fmt:message key="main_admin.enter_order_end_date"/></label>
                                        <input type="date" class="form-control" id="end_date" name="end_date" />
                                    </div>
                                    <button type="submit" class="btn btn-outline-success btn-sm" style="margin-top: 20px"><fmt:message key="main_admin.search_order"/></button>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal"><fmt:message key="main_client.close_window"/></button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row" style="margin-top: 40px">
            <div style="color: red; font-weight: bolder; font-style: italic">${errorOrderCompleteMessage}</div>
            <table class="table table-hover">
                <tbody id="ordersTable">
                </tbody>
            </table>
        </div>
        <div class="row" style="margin-top: 60px; margin-left: 300px">
            <div class="col-4">
                <button class="btn-warning" id="prev" type="button" onclick="prevPage()"><fmt:message key="pagination.backwards"/></button>
            </div>
            <div class="col-4" style="color: darkorange; margin-left: -100px">
                <span id="page"></span>
            </div>
            <div class="col-4" style="margin-left: -150px">
                <button class="btn-warning" id="next" type="button" onclick="nextPage()"><fmt:message key="pagination.forward"/></button>
            </div>
        </div>
    </div>
    <div class="row" style="margin-top: 20px">
        <form method="post" action="${pageContext.request.contextPath}/controller">
            <input type="hidden" name="command" id="command9" value="show_products_selling_statistics"/>
            <button type="submit" class="btn btn-outline-primary"><fmt:message key="main_admin.products_selling_statistics"/></button>
        </form>
    </div>
    <div class="row" style="margin-top: 20px">
        <form method="post" action="${pageContext.request.contextPath}/controller">
            <input type="hidden" name="command" id="command8" value="prepare_main_admin_page"/>
            <button type="submit" class="btn btn-outline-primary"><fmt:message key="main_admin.enter_menu"/></button>
        </form>
    </div>
</div>
</div>











<script type="text/javascript" src="${pageContext.request.contextPath}/js/orders_admin_view_handler.js"/>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
</body>
</html>
