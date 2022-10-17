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
        <%@ include file="../../static/min/side-bar.jsp" %>
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
                        <h1 class="m-0">Edit Product</h1>
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
                                <%--                                <a href="" class="btn btn-primary">--%>
                                <%--                                    <i class="fas fa-save"></i>&ensp;Save--%>
                                <%--                                </a>--%>
                            </div>
                            <!-- /.card-header -->
                            <div class="card-body">
                                <input type="hidden" name="status" id="status" value="${status}">
                                <form action="products?action=create" method="post">
                                    <div class="form-group center">
                                        <label for="name">Name</label>
                                        <input type="text" class="form-control" id="name" name="name"
                                               placeholder="Enter name"
                                               value="">
                                        <label for="image">Link image</label>
                                        <input type="text" name="image" id="image" class="form-control"
                                               value="">
                                        <label for="price">price</label>
                                        <input type="text" name="price" id="price" class="form-control"
                                               value="">
                                        <label>Status</label><br>
                                        <input type="radio" id="available" name="available" value="available">
                                        <label for="available">Available</label>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        <input type="radio" id="not-available" name="available" value="no available">
                                        <label for="not-available">Unavailable</label>
                                        <br>
                                        <label for="description">Description</label>
                                        <input type="text" name="description" id="description" class="form-control"
                                               value="">
                                        <label for="category">Category</label>
                                        <select name="id_category" id="category" class="form-control">
                                            <c:forEach items="${categories}" var="category">
                                                <option value="${category.id_category}">
                                                        ${category.name_category}
                                                </option>
                                            </c:forEach>
                                        </select>
                                        <br>
                                        <input type="submit" value="Save" class="btn btn-primary">

                                    </div>
                                </form>

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
<!-- DataTables & Plugins -->
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
<h1>Create product</h1>
<form action="products?action=create" method="post">
    <input type="text" name="name" placeholder="name product"><br>
    <input type="text" name="price" placeholder="price product"><br>
    <input type="text" name="image" placeholder="image product"><br>
    <input type="radio" name="available" value="available" checked>
    <lable>Available</lable>
    <br>
    <input type="radio" name="available" value="no available">
    <lable>No Available</lable>
    <br>
    <lable>Description</lable>
    <input type="text" name="description" placeholder="description product" style="height: 90px; width: 300px">
    <br>
    <lable>Category</lable>
    <input type="text" name="category" placeholder="category">
    <br>
    <br>
    <br>
    <input type="submit" value="Create">
</form>
</body>
<i color: red>${message}</i>
<script>
    var a = document.getElementById("available").value;
</script>
</html>
