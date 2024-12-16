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
        <form action="/member/login/login" method="post" class="login_core">
            <div class="login_core_after">
                <div class ="login_core_after_profile_image">

                </div>

                <div class="login_core_after_profile_name">
                    ${member.id} 님
                </div>
            </div>

            <div class="logout_button">
                <button type="submit">로그아웃</button>
            </div>
        </form>
    </div>
    <div class="after-login_sub">
        <div class="login_sub_factor"><a href="${pageContext.request.contextPath}/member/signup/signup-form">마이페이지</a></div>
    </div>
</div>
</body>
</html>
