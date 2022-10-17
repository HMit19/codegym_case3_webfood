
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
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
<h1>Thanh toan thanh cong</h1>
</body>
</html>
