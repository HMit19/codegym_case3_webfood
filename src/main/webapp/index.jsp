<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<style>
    a {
        font-size: 20px;
        text-align: center;
        margin: 20px;
    }
    div{
        margin: 0 auto;
    }
</style>
<body>
<h1>DAY LA TRANG MAC DINH CUA UNG DUNG
</h1>
<br/>
<div>
    <a href="/login-signup">login</a><br><br/>
    <a href="login-register?action=register">register</a><br>
    <h1>${item.toString()}</h1>
</div>
</body>
</html>