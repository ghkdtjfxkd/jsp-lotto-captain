<%--
  Created by IntelliJ IDEA.
  User: syt11
  Date: 2024. 11. 25.
  Time: 오후 5:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원가입</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/member/signup/signup-form.css">
</head>
<body>
<div class="container">

        <form action="/member/signup/save" method="POST">
            <div class="signup_container">
                <div class="signup_input">
                    <p class = "signup_input_label">ID</p>
                    <input type="text" name="id" class="signup_input_box" required>
                </div>

                <div class="signup_input">
                    <p class = "signup_input_label">NAME</p>
                    <input type="text" name="name" class="signup_input_box" required>
                </div>

                <div class="signup_input">
                    <p class = "signup_input_label">PASSWORD</p>
                    <input type="password" name="password" class="signup_input_box" required>
                </div>

                <div class="signup_input">
                    <p class = "signup_input_label">PASSWORD CONFIRM</p>
                    <input type="password" name="passwordConfirm" class = "signup_input_box" required>
                </div>

                <div class="signup_input">
                    <p class = "signup_input_label">EMAIL</p>
                    <input type="email" name="email" class="signup_input_box" required>
                </div>
                
                <button type="submit" class="signup_button">회원가입</button>
            </div>
        </form>
</div>
</body>
</html>


