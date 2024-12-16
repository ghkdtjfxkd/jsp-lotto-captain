<%--
  Created by IntelliJ IDEA.
  User: syt11
  Date: 2024. 12. 13.
  Time: 오후 4:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="css/member/login/login.css">
</head>
<body>
<div class="login_wrapper">
    <div class="login_core">
        <!-- 사용자 정보 -->
        <div class="login_core_after">
            <div class="login_core_after_profile_image">
                <img src="/static/logo.png" alt="프로필 이미지">
            </div>
            <div class="login_core_after_profile_name">
                ${sessionScope.loggedMember.name} 님
            </div>
        </div>

        <!-- 로그아웃 버튼 -->
        <div class="logout_button">
            <form action="login/logout" method="post">
                <button type="submit">로그아웃</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>

