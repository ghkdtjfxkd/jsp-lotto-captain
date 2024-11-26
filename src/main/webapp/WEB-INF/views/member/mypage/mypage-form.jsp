<%--
  Created by IntelliJ IDEA.
  User: syt11
  Date: 2024. 11. 26.
  Time: 오후 1:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="after-update" method="POST">
    ID: <input type="text" name="id" required><br>
    Name: <input type="text" name="name" required><br>
    Password: <input type="password" name="password" required><br>
    Email: <input type="email" name="email" required><br>
    <button type="submit">Register</button>
</form>

</body>
</html>
