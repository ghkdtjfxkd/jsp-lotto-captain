<%--
  Created by IntelliJ IDEA.
  User: syt11
  Date: 2024. 11. 18.
  Time: 오후 2:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="../../login.css">
</head>
<body>
<div class="login_wrapper">
    <form action="/member/login" method="post" class="login_core">
        <div class="login_core_input">
            <input type="text" placeholder="아이디" name="id" class="login_core_input_id" required>
            <input type="password" placeholder="비밀번호" name="password" class="login_core_input_pw" required>
        </div>

        <div class="login_core_button">
            <button type="submit">로그인</button>
        </div>
    </form>

    <div class="login_sub">
        <div class="login_sub_factor"><a href="${pageContext.request.contextPath}/member/signup/signup-form">회원가입</a></div>
        <div class="login_sub_factor"><a href="">id찾기</a></div>
        <div class="login_sub_factor"><a href="">pw찾기</a></div>
    </div>
</div>
</body>
</html>
