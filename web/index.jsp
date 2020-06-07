<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <style type="text/css">
        ol li {
            list-style: none;
        }

        .list-inline {
            text-align: center;
        }
    </style>
    <meta charset="UTF-8">
    <title>首页</title>
</head>
<body>
<div class="container-fluid">
    <div class="col-md-3" style="padding-top:20px">
        <ol class="list-inline">
            <c:choose>
                <c:when test="${loginUser!=null}">
                    <li>欢迎，${loginUser.username}</li>
                    <a href="${pageContext.request.contextPath}/user?action=list" style="text-decoration:none;font-size:33px">用户列表</a>
                </c:when>
                <c:otherwise>
                    <%--"./login.jsp" 也可以--%>
                    <li><a href="login.jsp" style="text-decoration:none;font-size:33px">用户登陆</a></li>
                </c:otherwise>
            </c:choose>
        </ol>
    </div>
</div>
</body>
</html>