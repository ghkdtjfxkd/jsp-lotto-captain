<%--
  Created by IntelliJ IDEA.
  User: syt11
  Date: 2024. 12. 17.
  Time: 오전 8:34
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
    <form action="/recommend/save">
    <!-- EL을 사용해 데이터 출력 -->
    <c:forEach var="lotto" items="${lottoList}">
        <li id="lottoNumbers">${lotto.numbers}</li>
    </c:forEach>

    </form>
    <button>submit</button>
</ul>
</body>
</html>
</body>
</html>

