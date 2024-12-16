<%--
  Created by IntelliJ IDEA.
  User: syt11
  Date: 2024. 11. 18.
  Time: 오후 1:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>::로또대장::압도적인 대한민국 NO.1 당첨률</title>
    <link rel="stylesheet" href="index.css">
</head>
<body>
<div class="container">

    <div class="main">
        
    <div class="left_ad">
        <%@ include file="ad/left-ad.jsp" %>
    </div>

    <div class="main_wrapper">
        <div class="main_wrapper_header">
            <%@ include file="header.jsp" %>
        </div>

        <div class="main_wrapper_contents">
            <div class="main_upperPart">
<%--                <div>--%>
<%--                    <c:choose>--%>

<%--                        <c:when test="${not empty sessionScope.loggedMember}">--%>
<%--                            <%@ include file="WEB-INF/views/login/after-login.jsp" %>--%>
<%--                        </c:when>--%>

<%--                        <c:otherwise>--%>
<%--                            <%@ include file="WEB-INF/views/login/login-form.jsp" %>--%>
<%--                        </c:otherwise>--%>
<%--                    </c:choose>--%>
<%--                </div>--%>
                <div>
                    <%@ include file="WEB-INF/views/member/login/login-status.jsp" %>
                </div>
                <div>
                    <%@ include file="local-ad.jsp" %>
                </div>
            </div>

            <div class="main_lowerPart">
                <%@ include file="main-lowerpart/main-lower.jsp"%>
            </div>

            <div class="main_lower_ad">
                <a href="https://www.dongyang.ac.kr">
                    <img src="./static/safetyPlayground.png" alt="">
                </a>
            </div>
        </div>
    </div>

    <div class="right_ad">
        <%@ include file="ad/right-ad.jsp" %>
    </div>

    </div>
</div>
</body>
</html>
