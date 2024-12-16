<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: syt11
  Date: 2024. 12. 9.
  Time: 오후 2:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%--  <link rel="stylesheet" href="header.css">--%>
</head>
<body>
<div class="winning_number_header">
    <p>${lottoData.draw}회차 당첨번호</p>
    <form action="winning-numbers.jsp" method="post" class="refresh">
        <button type="submit" class="refresh_button">새로고침</button>
    </form>
</div>

<div class="winning_number_main">
    <div class="winning_entry">
        <div class="winning_entry_number">
            <c:forEach var="num" items="${winningNumbers}">
                <div class=picked_number>
                    <p class=number_value>${lottoData.drwtNo}</p>
                </div>
            </c:forEach>
        </div>
    </div>

    <div class=picked_number>
        <p class=plus_Sign>+</p>
    </div>

    <div class="winning_entry_bonus">
        <div class=picked_number>
            <p class=number_value>${lottoData.drwtNo}</p>
        </div>
    </div>
</div>

</body>
</html>
