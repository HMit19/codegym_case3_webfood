<%--
  Created by IntelliJ IDEA.
  User: maiva
  Date: 10/6/2022
  Time: 12:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%--import jstl--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        .container-fluid{
            margin: 5px;
        }
        img {
            width: 100%;
            height: 100%;
        }
        .test {
            margin: 50px 0px;
            border: 1px solid black;
            padding: 5px 0px 70px 0px;
        }

        p {
            margin-top: 10px;
            text-align: center;
        }
    </style>
    <title>Trang danh sach danh cho khach hang</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="jumbotron text-center">
    <h3>Hello: ${sessionScope.Member.name}</h3>
    <c:if test="${sessionScope.Member.role == true}">
        <a href="../admin/manager.jsp">manager</a>
    </c:if>
    <h1>Danh sach cac san pham</h1>
    <c:if test="${sessionScope.Member == null}">
        <a href="login-signup?action=login">Log In</a>
    </c:if>
    <c:if test="${sessionScope.Member != null}">
        <a href="login-signup?action=logout">Logout</a>
    </c:if>
    <a href="carts?action=view">gio hang</a>
</div>

<div class="container">
    <div class="row">
        <c:forEach items="${products}" var="product">
            <div class="col-md-3 test">
                <a href="products?action=detail&id=${product.id_product}"><img src="${product.image_product}" alt=""></a>
                <p>${product.name_product}</p>
                <p>${product.price_product}</p>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
