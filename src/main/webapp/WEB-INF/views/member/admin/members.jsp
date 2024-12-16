<%--
  Created by IntelliJ IDEA.
  User: syt11
  Date: 2024. 11. 26.
  Time: 오전 9:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<a href="/index.jsp">메인</a>
<form method="post" action="/member/admin/delete">
<table>
    <thead>
    <th>선택</th>
    <th>id</th>
    <th>비밀번호</th>
    <th>username</th>
    <th>email</th>
    <th>관리자</th>

    </thead>
    <tbody>
    <c:forEach var="item" items="${members}">
        <tr>
            <td><input type="checkbox" name="${item.id}" value="${item.id}"></td>
            <td>${item.id}</td>
            <td>${item.password}</td>
            <td>${item.name}</td>
            <td>${item.email}</td>
            <td>${item.admin}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
    <button type="submit">선택된 멤버 삭제</button>
</form>
</body>
</html>