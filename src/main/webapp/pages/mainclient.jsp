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
                                                    <a href="/pages/authorization.jsp" style="width: 160px" class="btn btn-primary btn-sm"><fmt:message key="main_client.login_profile"/></a>
                                                </div>
                                                <br/>
                                                <div style="margin-top: 20px">
                                                    <a href="/pages/registration.jsp" style="width: 160px" class="btn btn-primary btn-sm" ><fmt:message key="main_client.reg_profile"/></a>
                                                </div>
                                                <br/>
                                                <form method="post" action="/controller">
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
                                        <form method="post" action="/controller">
                                            <input type="hidden" name="command" id="command2" value="change_login" required="required"/>
                                            <div class="mb-3 w-25" style="margin-top: 40px">
                                                <label for="loginOrEmail" class="form-label" ><fmt:message key="user_form.enter_login"/></label>
                                                <input type="text" class="form-control" id="loginOrEmail" name="login" required="required"/>
                                            </div>
                                            <button type="submit " class="btn btn-outline-success btn-lg" style="margin-top: 40px"><fmt:message key="main_client.change_login"/></button>
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
                                        <form method="post" action="/controller">
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
                                            <button type="submit " class="btn btn-outline-success btn-lg" style="margin-top: 40px"><fmt:message key="main_client.change_password"/></button>
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
                        <fmt:message key="main_client.basket"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="productData" style="display: none">
        ${productsInStock}
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
    <div id="prod_add_to_basket" style="display: none">
        <fmt:message key="main_client.prod_add_to_basket"/>
    </div>
    <div class="row" style="margin-top: 60px">
        <table class="table" >
            <tbody id="productTable">
            </tbody>
        </table>
    </div>
</div>
</div>











<script type="text/javascript" src="${pageContext.request.contextPath}/js/main_client_page_handler.js"/>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
</body>
</html>
