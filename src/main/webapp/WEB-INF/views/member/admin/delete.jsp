<%--
  Created by IntelliJ IDEA.
  User: syt11
  Date: 2024. 11. 26.
  Time: 오후 2:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>회원 삭제 성공</title>
<body>
삭제 된 회원들
<table>
    <tbody>
    <c:forEach var="item" items="${deleteMemberIds}">
        <tr>
            <td>${item}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="/index.jsp">메인</a>
</body>
</html>
