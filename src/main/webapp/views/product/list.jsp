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
    <!-- Custom style -->
    <%--    <style>--%>
    <%--        .image-product{--%>
    <%--            width: 100px;--%>
    <%--            height: 100px;--%>
    <%--        }--%>
    <%--    </style>--%>

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
                        <h1 class="m-0">Manager Product</h1>
                    </div>
                    <!-- /.col -->
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item">
                                <a href="/admin/manager.jsp">Home</a>
                            </li>
                            <li class="breadcrumb-item active">[ProductsPage]</li>
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
                                <h3 class="card-title col-10" style="margin-left: 28px;">Products</h3>
                                <a href="/products?action=create" class="btn btn-primary">
                                    <i class="fas fa-plus"></i>&ensp;Add Product
                                </a>
                            </div>
                            <!-- /.card-header -->
                            <input type="hidden" name="status" id="status" value="${status}">
                            <div class="card-body">
                                <table
                                        id="products"
                                        class="table table-bordered table-striped"
                                >
                                    <thead>
                                    <tr>
                                    <tr>
                                        <th class="col-1 text-center">ID</th>
                                        <th class="col-2 text-center">Photo</th>
                                        <th class="col-2 text-center">Name</th>
                                        <th class="col-1 text-center">Price</th>
                                        <%--                                        <th class="col-1 text-center">Status</th>--%>
                                        <th class="col-2 text-center">Description</th>
                                        <th class="col-1 text-center">Category</th>
                                        <th class="col-1 text-center">Edit</th>
                                        <th class="col-1 text-center">Delete</th>
                                    </tr>

                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${products}" var="product">
                                        <c:if test="${product.available == true}">
                                            <tr>
                                                <td class="text-center">${product.id_product}</td>
                                                <td><img src="${product.image_product}" alt="" width="240px"
                                                         height="150px">
                                                </td>
                                                <td><c:out value="${product.name_product}"/></td>
                                                <td class="text-center"><c:out value="${product.price_product}"/></td>
                                                    <%--                                            <td>--%>
                                                    <%--                                                <c:if test="${product.available == true}">--%>
                                                    <%--                                                    <c:out value="${product.available}"/>--%>
                                                    <%--                                                </c:if>--%>
                                                    <%--                                            </td>--%>
                                                <td><c:out value="${product.description}"/></td>
                                                <td>
                                                    <c:forEach items="${categories}" var="category">
                                                        <c:if test="${product.id_category == category.id_category}">
                                                            <c:out value="${category.name_category}"/>
                                                        </c:if>
                                                    </c:forEach>
                                                </td>
                                                <td class="text-center">
                                                    <a href="products?action=edit&id=${product.id_product}">
                                                        <i class="fas fa-edit"></i>
                                                    </a></td>
                                                <td class="text-center">
                                                    <a href="products?action=delete&id=${product.id_product}">
                                                        <i class="fas fa-trash-alt"></i>
                                                    </a>
                                                </td>
                                            </tr>
                                        </c:if>

                                    </c:forEach>
                                    </tbody>
                                    <tfoot>
                                    <%--                                    <tr>--%>
                                    <%--                                        <th>Rendering engine</th>--%>
                                    <%--                                        <th>Browser</th>--%>
                                    <%--                                        <th>Platform(s)</th>--%>
                                    <%--                                        <th>Engine version</th>--%>
                                    <%--                                        <th>CSS grade</th>--%>
                                    <%--                                    </tr>--%>
                                    </tfoot>
                                </table>
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
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script>
    const status = document.getElementById("status").value;
    if (status == "delete-success") {
        swal(
            'Success',
            'Delete successfully',
            'success'
        )
    }
    if (status == "edit-success") {
        swal(
            'Success',
            'Edit successfully',
            'success'
        )
    }
    if (status == "create-success") {
        swal(
            'Success',
            'Create successfully',
            'success'
        )
    }
</script>
</html>
