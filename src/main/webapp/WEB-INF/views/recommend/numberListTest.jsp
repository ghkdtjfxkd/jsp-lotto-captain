<%--
  Created by IntelliJ IDEA.
  User: syt11
  Date: 2024. 12. 16.
  Time: 오후 8:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Numbers List</title>
</head>
<body>
<h1>Number List</h1>
<ul>
    <!-- EL을 사용해 데이터 출력 -->
    <c:forEach var="number" items="${numberList}">
        <li>${number}</li>
    </c:forEach>
</ul>
</body>
</html>
</body>
</html>
