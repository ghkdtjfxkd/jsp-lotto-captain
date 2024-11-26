<%--
  Created by IntelliJ IDEA.
  User: syt11
  Date: 2024. 11. 26.
  Time: 오전 9:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="https://jakarta.ee/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<a href="/index.jsp">메인</a>
<table>
    <thead>
    <th>id</th>
    <th>username</th>
    <th>email</th>
    </thead>
    <tbody>
    <c:forEach var="item" items="${members}">
        <tr>
            <td>${item.getId}</td>
            <td>${item.getName}</td>
            <td>${item.getEmail}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>