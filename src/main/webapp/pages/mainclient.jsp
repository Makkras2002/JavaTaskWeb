<html>
<head>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ page isELIgnored="false" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
                <form method="post" action="${pageContext.request.contextPath}/controller">
                    <input type="hidden" name="command" id="command8" value="prepare_main_admin_page"/>
                    <button type="submit" class="btn btn-outline-primary"><fmt:message key="main_admin.enter_menu"/></button>
                </form>
            </div>
            <div class="col-4">
                <div class="row">
                    <div class="col-4">
                        <%@ include file="change_locale_button_form.jsp"%>
                    </div>
                    <div class="col-4">
                        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
                            <fmt:message key="main_client.profile"/>
                        </button>
                        <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="staticBackdropLabel"><fmt:message key="main_client.profile"/> ${pageContext.session.getAttribute("login")}</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <div class="row">
                                            <div class="col-4">
                                                <div>
                                                    <a href="${pageContext.request.contextPath}/pages/authorization.jsp" style="width: 160px" class="btn btn-primary btn-sm"><fmt:message key="main_client.login_profile"/></a>
                                                </div>
                                                <br/>
                                                <div style="margin-top: 20px">
                                                    <a href="${pageContext.request.contextPath}/pages/registration.jsp" style="width: 160px" class="btn btn-primary btn-sm" ><fmt:message key="main_client.reg_profile"/></a>
                                                </div>
                                                <br/>
                                                <form method="post" action="${pageContext.request.contextPath}/controller">
                                                    <input type="hidden" name="command" id="command" value="logout" required="required"/>
                                                    <button type="submit" style="width: 160px" class="btn btn-primary btn-sm" style="margin-top: 20px"><fmt:message key="main_client.logout_profile"/></button>
                                                </form>
                                            </div>
                                            <div class="col-4">
                                                <div style="margin-left: 140px">
                                                    <button class="btn btn-primary btn-sm" style="width: 160px" data-bs-target="#staticBackdrop2" data-bs-toggle="modal"><fmt:message key="main_client.change_login"/></button>
                                                </div>
                                                <br/>
                                                <div style="margin-top: 20px; margin-left: 140px">
                                                    <button class="btn btn-primary btn-sm" style="width: 160px" data-bs-target="#staticBackdrop3" data-bs-toggle="modal"><fmt:message key="main_client.change_password"/></button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal"><fmt:message key="main_client.close_window"/></button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="modal fade" id="staticBackdrop2" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="staticBackdropLabel2"><fmt:message key="main_client.change_login"/></h5>
                                    </div>
                                    <div class="modal-body">
                                        <form method="post" action="${pageContext.request.contextPath}/controller">
                                            <input type="hidden" name="command" id="command2" value="change_login" required="required"/>
                                            <div class="mb-3 w-25" style="margin-top: 40px">
                                                <label for="loginOrEmail" class="form-label" ><fmt:message key="user_form.enter_login"/></label>
                                                <input type="text" class="form-control" id="loginOrEmail" name="login" required="required"/>
                                            </div>
                                            <button type="submit" class="btn btn-outline-success btn-lg" style="margin-top: 40px"><fmt:message key="main_client.change_login"/></button>
                                        </form>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal"><fmt:message key="main_client.close_window"/></button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="modal fade" id="staticBackdrop3" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="staticBackdropLabel3"><fmt:message key="main_client.change_password"/></h5>
                                    </div>
                                    <div class="modal-body">
                                        <form method="post" action="${pageContext.request.contextPath}/controller">
                                            <input type="hidden" name="command" id="command3" value="change_password" required="required"/>
                                            <div class="mb-3 w-25" style="margin-top: 40px">
                                                <label for="password" class="col-form-label"><fmt:message key="user_form.enter_password"/></label>
                                            </div>
                                            <div class="mb-3 w-25">
                                                <input type="password" id="password" name="password" class="form-control" maxlength="20" minlength="8" aria-describedby="passwordHelpInline" required="required">
                                            </div>
                                            <div class="col-auto">
                                                   <span id="passwordHelpInline" class="form-text">
                                                        <fmt:message key="user_form.password_requirements"/>
                                                   </span>
                                            </div>
                                            <button type="submit" class="btn btn-outline-success btn-lg" style="margin-top: 40px"><fmt:message key="main_client.change_password"/></button>
                                        </form>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal"><fmt:message key="main_client.close_window"/></button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-4">
                        <form method="post" action="${pageContext.request.contextPath}/controller">
                            <input type="hidden" name="command" id="command7" value="open_order_page_wrapper"/>
                            <button type="submit" class="btn btn-primary"><fmt:message key="main_client.basket"/></button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="productData" style="display: none">
        ${productsInStock}
    </div>
    <div id="categoriesForSearch" style="display: none">
        ${productCategories}
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
    <div id="prod_comment" style="display: none">
        <fmt:message key="main_client.prod_comment"/>
    </div>
    <div id="prod_amount" style="display: none">
        <fmt:message key="main_client.prod_amount"/>
    </div>
    <div id="prod_empty_res" style="display: none">
        <fmt:message key="main_client.empty_products"/>
    </div>
    <div id="prod_add_to_basket" style="display: none">
        <fmt:message key="main_client.prod_add_to_basket"/>
    </div>
    <div class="row" style="margin-top: 40px">
        <div class="col-4">
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <label for="command4"><fmt:message key="main_client.sort_form_name"/></label>
                <select class="form-select" name="command" id="command4" aria-label="Default select example" required="required">
                    <option value="sort_products_by_name"><fmt:message key="main_client.sort_by_name"/></option>
                    <option value="sort_products_by_category"><fmt:message key="main_client.sort_by_category"/></option>
                    <option value="sort_products_by_price"><fmt:message key="main_client.sort_by_price"/></option>
                </select>
                <button type="submit " class="btn btn-outline-success btn-sm" style="margin-top: 20px"><fmt:message key="main_client.sort"/></button>
            </form>
        </div>
        <div class="col-4">
            <button type="button" class="btn btn-outline-success btn-sm" style="margin-top: 82px; margin-left: -300px" data-bs-toggle="modal" data-bs-target="#staticBackdrop4">
                <fmt:message key="main_client.search"/>
            </button>
            <div class="modal fade" id="staticBackdrop4" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="staticBackdropLabel4"><fmt:message key="main_client.search"/> ${pageContext.session.getAttribute("login")}</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <p><fmt:message key="main_client.search_explanation"/></p>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="text" id="command5" name="command" hidden="hidden" required="required" value="find_product"/>
                                <div class="mb-3 w-25" style="margin-top: 40px" width="160px">
                                    <label for="name" class="form-label" ><fmt:message key="main_client.prod_name"/></label>
                                    <input type="text" maxlength="100" class="form-control" id="name" name="name" />
                                </div>
                                <div class="mb-3 w-25" style="margin-top: 40px" width="160px">
                                    <label for="categories" class="form-label" ><fmt:message key="main_client.prod_category"/></label>
                                    <select class="form-select" id="categories" name="category" aria-label="Default select example">
                                    </select>
                                </div>
                                <div class="mb-3 w-25" style="margin-top: 40px" width="160px">
                                    <label for="min_price" class="form-label" ><fmt:message key="main_client.prod_price_min"/></label>
                                    <input type="number" min="0" class="form-control" id="min_price" name="min_price" />
                                </div>
                                <div class="mb-3 w-25" style="margin-top: 40px"  width="160px">
                                    <label for="max_price" class="form-label" ><fmt:message key="main_client.prod_price_max"/></label>
                                    <input type="number" min="0" class="form-control" id="max_price" name="max_price" />
                                </div>
                                <button type="submit" class="btn btn-outline-success btn-sm" style="margin-top: 20px"><fmt:message key="main_client.search"/></button>
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
    <div class="row" style="margin-top: 60px">
        <table class="table table-hover" >
            <tbody id="productTable">
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
</div>











<script type="text/javascript" src="${pageContext.request.contextPath}/js/main_client_page_handler.js"/>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
</body>
</html>
