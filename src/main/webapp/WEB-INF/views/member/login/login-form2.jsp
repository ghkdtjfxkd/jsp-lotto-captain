<%--
  Created by IntelliJ IDEA.
  User: syt11
  Date: 2024. 11. 26.
  Time: 오후 1:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>로그인 완료</title>
</head>
<body>
<div class="welcome_message">
  <h1>${loggedMember.id}님, 환영합니다!</h1>
  <p>로그인이 성공적으로 완료되었습니다.</p>
</div>
<a href="/members/logout">로그아웃</a>
<a href="../mypage/mypage-form">마이페이지</a>
<a href="../admin/members">회원관리</a>

</body>
</html>
