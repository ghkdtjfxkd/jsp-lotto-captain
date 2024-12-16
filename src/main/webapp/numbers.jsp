<%--
  Created by IntelliJ IDEA.
  User: syt11
  Date: 2024. 12. 1.
  Time: 오후 5:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>로또 번호 선택</title>
  <style>
    .selected-box {
      display: flex;
      gap: 10px;
      margin-bottom: 20px;
    }
    .selected-slot {
      width: 40px;
      height: 40px;
      line-height: 40px;
      text-align: center;
      border: 1px solid #000;
      border-radius: 5px;
      font-weight: bold;
    }
    .number-button {
      margin: 5px;
      width: 40px;
      height: 40px;
    }
    .number-button.selected {
      background-color: #007bff;
      color: white;
    }
  </style>
</head>
<body>
<%
  // 세션에서 선택된 번호 가져오기 또는 초기화
  HttpSession session = request.getSession();
  List<String> selectedNumbers = (List<String>) session.getAttribute("selectedNumbers");

  if (selectedNumbers == null) {
    selectedNumbers = new ArrayList<>(Collections.nCopies(6, "")); // 6개의 빈 슬롯 초기화
    session.setAttribute("selectedNumbers", selectedNumbers);
  }

  // 클릭된 번호 처리
  String clickedNumber = request.getParameter("number");
  if (clickedNumber != null) {
    if (selectedNumbers.contains(clickedNumber)) {
      // 이미 선택된 번호인 경우 해제
      selectedNumbers.remove(clickedNumber);
      selectedNumbers.add(""); // 빈 칸으로 채우기
    } else if (selectedNumbers.contains("")) {
      // 빈 칸이 있는 경우 선택
      selectedNumbers.set(selectedNumbers.indexOf(""), clickedNumber);
    }
    session.setAttribute("selectedNumbers", selectedNumbers);
  }
%>

<h1>로또 번호 선택</h1>

<!-- 선택된 번호 칸 -->
<div class="selected-box">
  <% for (String number : selectedNumbers) { %>
  <div class="selected-slot">
    <%= number.isEmpty() ? "&nbsp;" : number %>
  </div>
  <% } %>
</div>

<!-- 번호 선택 버튼 -->
<div>
  <% for (int i = 1; i <= 45; i++) {
    String numberStr = String.valueOf(i);
  %>
  <form action="numbers.jsp" method="get" style="display:inline;">
    <input type="hidden" name="number" value="<%= i %>">
    <button class="number-button <%= selectedNumbers.contains(numberStr) ? "selected" : "" %>" type="submit">
      <%= i %>
    </button>
  </form>
  <% } %>
</div>
</body>
</html>
