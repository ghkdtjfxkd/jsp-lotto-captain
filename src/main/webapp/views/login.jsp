<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Login</title>
</head>
<body>
<h1>Login</h1>
<form action="/member" method="POST">
  <input type="hidden" name="action" value="login">
  ID: <input type="text" name="id" required><br>
  Password: <input type="password" name="password" required><br>
  <button type="submit">Login</button>
</form>
</body>
</html>
