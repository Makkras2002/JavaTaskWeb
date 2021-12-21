<html>
<head>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ page isELIgnored="false" %>
    <title>AutoShopPlus</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script type="text/javascript">
        function disableBack() { window.history.forward(); }
        setTimeout("disableBack()", 0);
        window.onunload = function () { null };
    </script>
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
                        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
                            Профиль
                        </button>
                        <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="staticBackdropLabel">Профиль ${pageContext.session.getAttribute("login")}</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <div>
                                            <a href="/pages/authorization.jsp" class="btn btn-primary btn-sm">Войти</a>
                                        </div>
                                        <br/>
                                        <div style="margin-top: 20px">
                                            <a href="/pages/registration.jsp" class="btn btn-primary btn-sm" >Зарегистрироваться</a>
                                        </div>
                                        <br/>
                                        <form method="post" action="/controller">
                                            <input type="hidden" name="command" id="command" value="logout"/>
                                            <button type="submit" class="btn btn-primary btn-sm" style="margin-top: 20px">Выйти</button>
                                        </form>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-4">
                        Корзина
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="productData" style="display: none">
        ${productsInStock}
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
