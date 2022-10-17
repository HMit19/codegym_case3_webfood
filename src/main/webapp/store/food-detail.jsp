<%--
  Created by IntelliJ IDEA.
  User: maiva
  Date: 10/6/2022
  Time: 12:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
    <style>
        a {
            border: 1px solid black;
            padding: 2px;
        }
    </style>
    <script>
        function increment() {
            var value = parseInt(document.getElementById('number').innerHTML);
            value++;
            var cost = parseInt(document.getElementById('cost').innerHTML);
            cost += ${product.price_product};
            document.getElementById('number').innerHTML = value;
            document.getElementById('cost').innerHTML = cost;
            document.getElementById('quantity').value = value;
        }

        function decrement() {
            var value = parseInt(document.getElementById('number').innerHTML);
            if (value == 1) {
            } else {
                value--;
                var cost = parseInt(document.getElementById('cost').innerHTML);
                cost -= ${product.price_product};
                document.getElementById('number').innerHTML = value;
                document.getElementById('cost').innerHTML = cost;
                document.getElementById('quantity').value = value;
            }
        }
    </script>
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

<div class="text-center">
    <div class="container">
        <div class="row">
            <div class="col-md-7">
                <img src="${product.image_product}" alt="" width="500px" height="500px">
            </div>
            <div class="col-md-5">
                <p>${product.name_product}</p>
                <button onclick="decrement()">-</button>

                <span id="number">1</span>

                <button onclick="increment()">+</button>

                <p>${product.price_product}</p>
                <div>
                    <br>
                    <p>Tong tien: <span id="cost">${product.price_product}</span></p>
                    <br>
                </div>
                <form action="/carts" method="post">
                    <input type="hidden" name="action" value="addToCart">
                    <input type="hidden" name="id" value="${product.id_product}">
                    <input type="hidden" name="price" value="${product.price_product}">
                    <input type="hidden" id="quantity" name="quantity" value="1">
                    <input type="submit" value="Add to cart">
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
