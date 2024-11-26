<%--
  Created by IntelliJ IDEA.
  User: syt11
  Date: 2024. 11. 26.
  Time: 오후 2:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원 삭제 성공</title>
<body>
삭제 된 회원들
<table>
    <thead>
    <th>선택</th>
    <th>id</th>
    <th>username</th>
    <th>email</th>
    </thead>
    <tbody>
    <c:forEach var="item" items="${members}">
        <tr>
            <td><input type="checkbox" name="memberIds" value="${item.id}"></td>
            <td>${item.id}</td>
            <td>${item.name}</td>
            <td>${item.email}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="/index.jsp">메인</a>
</body>
</html>
