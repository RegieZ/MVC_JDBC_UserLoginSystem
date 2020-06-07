<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>修改用户</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery-2.1.0.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

</head>
<body>
<div class="container">
    <div class="container">
        <h3 style="text-align: center;">修改用户</h3>
        <form action="${pageContext.request.contextPath}/user?action=update" method="post">
            <div class="form-group">
                <label for="id">编号：</label>
                <input type="text" class="form-control" id="id" readonly="readonly" name="id" value="${user.id}">
            </div>

            <div class="form-group">
                <label for="username">用户名：</label>
                <input type="text" class="form-control" id="username" name="username" placeholder="请输入用户名"/>
            </div>

            <div class="form-group">
                <label for="password">密码：</label>
                <input type="text" class="form-control" id="password" name="password" placeholder="请输入密码"/>
            </div>

            <div class="form-group" style="text-align: center">
                <input class="btn btn-primary" type="submit" value="提交"/>
                <input class="btn btn-default" type="reset" value="重置"/>
                <input class="btn btn-default" type="button" value="返回"/>
            </div>
        </form>
    </div>
</div>
</body>
</html>