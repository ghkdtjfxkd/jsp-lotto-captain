<%--
  Created by IntelliJ IDEA.
  User: syt11
  Date: 2024. 11. 25.
  Time: 오후 5:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>로그인 완료</title>
</head>
<body>
<div class="welcome_message">
    <h1>${loggedMember}님, 환영합니다!</h1>
    <p>로그인이 성공적으로 완료되었습니다.</p>
</div>
<a href="/members/logout">로그아웃</a>
</body>
</html>
