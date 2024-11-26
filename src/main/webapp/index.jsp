<%--
  Created by IntelliJ IDEA.
  User: syt11
  Date: 2024. 11. 18.
  Time: 오후 1:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>::로또대장::압도적인 대한민국 NO.1 당첨률</title>
    <link rel="stylesheet" href="index.css">
</head>
<body>

<div class="container">
    <div class="left_ad">
        <a href="https://www.kosaf.go.kr/ko/main.do" class="left_ad_factor">
            <img src="./static/img_1.png" alt="">
        </a>
    </div>

    <div class="main_wrapper">
        
        <div class="main_wrapper_header">
            <%@ include file="header.jsp" %>
        </div>

        <div class="main_wrapper_contents">
            <div class="main_upperPart">
                <%@ include file="WEB-INF/views/member/login/login-status.jsp"%>
                <%@ include file="local-ad.jsp"%>
            </div>
            <div class="main_lowerPart"></div>
            <div class="main_lower_ad">
                <a href="https://www.dongyang.ac.kr">
                    <img src="./static/safetyPlayground.png" alt="">
                </a>
            </div>
        </div>

    </div>

    <div class="right_ad">

    </div>
</div>
</body>
</html>
