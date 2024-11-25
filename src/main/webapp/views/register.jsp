<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Register</title>
</head>
<body>
<h1>Register</h1>
<form action="/member" method="POST">
  <input type="hidden" name="action" value="register">
  ID: <input type="text" name="id" required><br>
  Password: <input type="password" name="password" required><br>
  Name: <input type="text" name="name" required><br>
  Email: <input type="email" name="email" required><br>
  <button type="submit">Register</button>
</form>
</body>
</html>
