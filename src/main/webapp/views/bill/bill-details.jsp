<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>[ProjectName]</title>

    <!-- Google Font: Source Sans Pro -->
    <link
            rel="stylesheet"
            href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback"
    />
    <!-- Font Awesome -->
    <link rel="stylesheet" href="../static/css/fontawesome.all.min.css"/>
    <!-- DataTables -->
    <link rel="stylesheet" href="../static/css/dataTables.bootstrap4.min.css"/>
    <link rel="stylesheet" href="../static/css/responsive.bootstrap4.min.css"/>
    <!-- Theme style -->
    <link rel="stylesheet" href="../static/css/adminlte.min.css"/>
</head>
<body class="hold-transition sidebar-mini">
<div class="wrapper">
    <!-- Navbar -->
    <%@ include file="../../static/min/nav.jsp" %>
    <!-- /.navbar -->

    <!-- Main Sidebar Container -->
    <aside class="main-sidebar sidebar-dark-primary elevation-4">
        <!-- Brand Logo -->
        <a href="/starter.html" class="brand-link">
            <img
                    src="/static/img/logo.png"
                    alt="AdminLTE Logo"
                    class="brand-image img-circle elevation-3"
                    style="opacity: 0.8"
            />
            <span class="brand-text font-weight-light">AdminLTE 3</span>
        </a>

        <!-- Sidebar -->
        <div class="sidebar">
            <!-- Sidebar Menu -->
            <nav class="mt-2">
                <ul
                        class="nav nav-pills nav-sidebar flex-column"
                        data-widget="treeview"
                        role="menu"
                        data-accordion="false"
                >
                    <li class="nav-item">
                        <a href="/products.html" class="nav-link active">
                            <span class="nav-icon badge">P</span>
                            <p>[ProductsPage]</p>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="/users.html" class="nav-link">
                            <span class="nav-icon badge">U</span>
                            <p>[UsersPage]</p>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="/bills.html" class="nav-link">
                            <span class="nav-icon badge">B</span>
                            <p>[BillsPage]</p>
                        </a>
                    </li>
                </ul>
            </nav>
            <!-- /.sidebar-menu -->
        </div>
        <!-- /.sidebar -->
    </aside>

    <!-- Main Sidebar Container -->
    <aside class="main-sidebar sidebar-dark-primary elevation-4">
        <!-- Brand Logo -->
        <a href="/stores" class="brand-link">
            <img src="/static/img/logo.png" alt="AdminLTE Logo" class="brand-image img-circle elevation-3"
                 style="opacity: .8">
            <span class="brand-text font-weight-light">Store Page</span>
        </a>

        <!-- Sidebar -->
        <%@ include file="../../static/min/side-bar.jsp" %>
        <!-- /.sidebar -->
    </aside>


    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <div class="content-header">
            <div class="container-fluid">
                <div class="row mb-2">
                    <div class="col-sm-6">
                        <h1 class="m-0">Manager Bill</h1>
                    </div>
                    <!-- /.col -->
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item">
                                <a href="/admin/manager.jsp">Home</a>
                            </li>
                            <li class="breadcrumb-item active">[BillsPage]</li>
                        </ol>
                    </div>
                    <!-- /.col -->
                </div>
                <!-- /.row -->
            </div>
            <!-- /.container-fluid -->
        </div>
        <!-- /.content-header -->

        <!-- Main content -->
        <div class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-header">
                                <h3 class="card-title">Bill Detail</h3>
                            </div>
                            <!-- /.card-header -->
                            <div class="card-body">
                                <h3 style="margin-bottom: 20px;">Order Detail</h3>
                                <div style="margin-bottom:50px;">
                                    <ul>
                                        <li>ID Order: &nbsp;
                                            <span>
                                                ${bill.id_bill}
                                            </span></li>
                                        <li>Name Customer: &nbsp;
                                            <span>
                                                ${bill.user.name}
                                            </span></li>
                                        <li>Date: &nbsp;
                                            <span>
                                                ${
                                                bill.date_bill
                                                }
                                            </span></li>
                                        <li>Address: &nbsp;
                                            <span>
                                                ${bill.user.address}
                                            </span></li>
                                        <li>Note: &nbsp;
                                            <c:if test="${bill.detail == null}">
                                                <span></span>
                                            </c:if>
                                            <c:if test="${bill.detail != null}">
                                                <span>${bill.detail}</span>
                                            </c:if>
                                        </li>
                                    </ul>
                                </div>
                                <table id="products" class="table table-bordered table-striped">
                                    <tr>
                                        <th>Name Product</th>
                                        <th>Quantity</th>
                                    </tr>
                                    <c:forEach items="${items}" var="item">
                                        <tr>
                                            <td>${item.product.name_product}</td>
                                            <td>${item.quantity}</td>
                                        </tr>
                                    </c:forEach>
                                </table>

                                <h3 style="margin: 50px 0px 20px 0px;">Pay</h3>
                                <div>
                                    <h4>Total:
                                        <span>${bill.total}</span>&nbsp; VND
                                    </h4>
                                </div>

                            </div>
                            <!-- /.card-body -->
                        </div>
                        <!-- /.card -->
                    </div>
                    <!-- /.col-md-6 -->
                </div>
                <!-- /.row -->
            </div>
            <!-- /.container-fluid -->
        </div>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <!-- Main Footer -->
    <%@ include file="../../static/min/footer.jsp" %>
</div>
<!-- ./wrapper -->

<!-- REQUIRED SCRIPTS -->
<!-- jQuery -->
<script src="/static/js/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="/static/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE App -->
<script src="/static/js/adminlte.min.js"></script>
<!-- DataTables  & Plugins -->
<script src="/static/js/jquery.dataTables.min.js"></script>
<script src="/static/js/dataTables.bootstrap4.min.js"></script>
<script src="/static/js/dataTables.responsive.min.js"></script>
<script src="/static/js/responsive.bootstrap4.min.js"></script>
<script>
    $("#products").DataTable({
        paging: true,
        lengthChange: false,
        searching: true,
        ordering: true,
        info: true,
        autoWidth: true,
        responsive: true,
    });
</script>
</body>
</html>
