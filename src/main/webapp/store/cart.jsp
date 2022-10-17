<%--
  Created by IntelliJ IDEA.
  User: maiva
  Date: 10/11/2022
  Time: 2:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
    <!--    boostrap 4-->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <!--    thu vien icon-->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>Order detail</title>
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

<section class="vh-100" style="background-color: #fdfdfe;">
    <div class="container h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col">
                <p><span class="h2">Cart </span></p>
                <br>
                <div class="card mb-4">
                    <div class="card-body p-4">
                        <input type="hidden" name="status" value="${status}">
                        <div class="row align-items-center" style="row-gap: 20px;">
                            <div class="col-md-2 d-flex justify-content-center">
                                <p class="small text-muted mb-4 pb-2">Photo</p>
                            </div>
                            <div class="col-md-2 d-flex justify-content-center">
                                <div>
                                    <p class="small text-muted mb-4 pb-2">Product</p>
                                </div>
                            </div>
                            <div class="col-md-2 d-flex justify-content-center">
                                <div>
                                    <p class="small text-muted mb-4 pb-2">Price</p>
                                </div>
                            </div>
                            <div class="col-md-2 d-flex justify-content-center">
                                <div>
                                    <p class="small text-muted mb-4 pb-2">Quantity</p>
                                </div>
                            </div>
                            <div class="col-md-2 d-flex justify-content-center">
                                <div>
                                    <p class="small text-muted mb-4 pb-2">Total</p>
                                </div>
                            </div>
                            <div class="col-md-2 d-flex justify-content-center text-center">
                                <div>
                                    <p class="small text-muted mb-4 pb-2">Delete</p>

                                </div>
                            </div>

                            <c:forEach items="${items}" var="item">
                                <div class="col-md-2 d-flex justify-content-center">
                                    <img src="${item.product.image_product}"
                                         class="img-fluid" alt="Generic placeholder image">
                                </div>
                                <div class="col-md-2 d-flex justify-content-center">
                                    <div>
                                        <p class="lead fw-normal mb-0">${item.product.name_product}</p>
                                    </div>
                                </div>
                                <div class="col-md-2 d-flex justify-content-center">
                                    <div>
                                        <p class="lead fw-normal mb-0">${item.price}</p>
                                    </div>
                                </div>
                                <div class="col-md-2 d-flex justify-content-center">
                                    <div>
                                        <p class="lead fw-normal mb-0">${item.quantity}</p>
                                    </div>
                                </div>
                                <div class="col-md-2 d-flex justify-content-center">
                                    <div>
                                        <p class="lead fw-normal mb-0 cost">${item.quantity * item.price}</p>
                                    </div>
                                </div>
                                <div class="col-md-2 d-flex justify-content-center text-center">
                                    <div>
                                        <div class="lead fw-normal mb-0">
                                            <a href="carts?action=remove&id=${item.id_product}"><i class="fa fa-trash-o"
                                                                                                   style="font-size:24px;color:#d77272"></i></a>

                                        </div>
                                    </div>
                                </div>
                            </c:forEach>


                        </div>

                    </div>
                </div>

                <div class="card mb-5">
                    <div class="card-body p-4">

                        <div class="float-end">
                            <p class="mb-0 me-5 d-flex align-items-center">
                                <span class="small text-muted me-2">Order total:</span>
                                <span class="lead fw-normal ">&nbsp;
                                    <span id="total">
                                    </span>
                                    <span>VND</span>
                                </span>
                            </p>
                        </div>

                    </div>
                </div>

                <%--                &lt;%&ndash;                them phan ghi chu cho don hang&ndash;%&gt;--%>
                <%--                <div class="card mb-5">--%>
                <%--                    <div class="card-body p-4">--%>
                <%--                        <div class="float-end">--%>
                <%--                            <p class="mb-0 me-5 d-flex align-items-center">--%>
                <%--                                <span class="small text text-muted me-2">Note:</span>--%>
                <%--                                <input type="text" id="note" placeholder="Note for order" size="input100">--%>
                <%--                            </p>--%>
                <%--                        </div>--%>
                <%--                    </div>--%>
                <%--                </div>--%>

                <div class="d-flex justify-content-end">
                    <button type="button" class="btn btn-light btn-lg me-2" style="margin-right: 20px;">
                        <a href="stores">Continue
                            shopping</a>
                    </button>
                    <button type="button" class="btn btn-secondary btn-lg">
                        <a href="stores?action=pay" class="text-light" style="list-style: none;">Pay up</a></button>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
<script>
    var sum = 0;
    var cost = document.getElementsByClassName("cost");
    for (var i = 0; i < cost.length; i++) {
        sum += parseInt(cost[i].innerHTML);
    }
    document.getElementById("total").innerHTML = sum;
</script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.2.0/sweetalert2.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.2.0/sweetalert2.all.min.js"></script>
<script>
    var status = "${status}";
    if (status == "success") {
        swal({
            title: 'Success',
            text: 'Order success',
            type: 'success',
            confirmButtonText: 'OK'
        });
    }
    if (status == "fail") {
        swal({
            title: 'Fail',
            text: 'Order fail',
            type: 'error',
            confirmButtonText: 'OK'
        });
    }
</script>
</html>
