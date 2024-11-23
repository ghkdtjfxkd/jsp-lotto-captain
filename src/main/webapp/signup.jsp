<%--
  Created by IntelliJ IDEA.
  User: syt11
  Date: 2024. 11. 22.
  Time: 오후 3:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원가입</title>
</head>
<body>
<h1>회원가입</h1>
<form action="signup" method="post">

    <label for="memberId">아이디:</label>
    <input type="text" id="memberId" name="memberId" required><br>

    <label for="memberName">이름:</label>
    <input type="text" id="memberName" name="memberName" required><br>

    <label for="password">비밀번호:</label>
    <input type="password" id="password" name="password" required><br>

    <label for="passwordConfirm">비밀번호 확인:</label>
    <input type="password" id="passwordConfirm" name="passwordConfirm" required><br>

    <label for="email">이메일:</label>
    <input type="email" id="email" name="email" required><br>

    <button type="submit">가입하기</button>
</form>
</body>
</html>
