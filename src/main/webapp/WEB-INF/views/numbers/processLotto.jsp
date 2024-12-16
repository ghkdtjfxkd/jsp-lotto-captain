<%--
  Created by IntelliJ IDEA.
  User: syt11
  Date: 2024. 12. 16.
  Time: 오전 11:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>::로또대장::로또 번호 추천</title>
    <link rel="stylesheet" href="index.css">
</head>
<body>
<div class="container">
    <div class="main">
        <%--    왼쪽 광고  --%>
        <div class="left_ad">
            <%@ include file="../../../ad/left-ad.jsp" %>
        </div>

        <%--메인 디스플레이--%>
        <div class="main_wrapper">
            <div class="main_wrapper_header">
                <%@ include file="../../../header.jsp" %>
            </div>

            <div class="main_wrapper_contents">
                <%@ include file="../../../testrecommend.jsp"%>
            </div>
        </div>

        <%--   오른쪽 광고--%>
        <div class="right_ad">
            <%@ include file="../../../ad/right-ad.jsp" %>
        </div></div>
</div>
</body>
</html>
