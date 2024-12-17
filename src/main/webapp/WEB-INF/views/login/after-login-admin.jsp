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
    <link rel="stylesheet" href="css/member/login/login-after.css">
</head>
<body>
<div class="login_wrapper">
    <div class="login_core">
        <form action="login/logout" method="post" class="login_core">
            <div class="login_core_after_profile_image">
                <img src="/static/logo.png" class="login_core_after_profile_image" alt="프로필 이미지">
            </div>

            <div class="login_core_after_profile_main">
                <div class="login_core_after_profile_level">
                    ♛관리자♛
                </div>

                <div class="login_core_after_profile_name">
                    ${sessionScope.loggedMember.name} 님
                </div>
            </div>

            <!-- 로그아웃 버튼 -->

            <button type="submit" class="logout_button">
                <p class="logout_button_text">로그아웃</p>
            </button>

        </form>
    </div>
    <div class="login_sub">
        <div class="login_sub_factor">
            <a href="../member/mypage/mypage-form" class="login_sub_factor" >마이페이지</a>
        </div>

        <div class="login_sub_factor">
            <a href="../member/admin/members" class="login_sub_factor">회원관리</a>
        </div>
    </div>
</div>
</body>
</html>

