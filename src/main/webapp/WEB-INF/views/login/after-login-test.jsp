
<%--
  Created by IntelliJ IDEA.
  User: syt11
  Date: 2024. 12. 13.
  Time: 오후 4:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="css/member/login/login-after.css">
</head>
<body>
<div>
    <c:choose>
        <c:when test="${sessionScope.loggedMember.admin}">
            <%@ include file="after-login-admin.jsp" %>
        </c:when>

        <c:otherwise>
            <%@ include file="after-login-user.jsp" %>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>

