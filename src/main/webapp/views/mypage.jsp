<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
  <title>My Page</title>
</head>
<body>
<h1>Welcome, ${sessionScope.member.name}</h1>
<form action="/member" method="POST">
  <input type="hidden" name="action" value="update">
  Name: <input type="text" name="name" value="${sessionScope.member.name}" required><br>
  Email: <input type="email" name="email" value="${sessionScope.member.email}" required><br>
  <button type="submit">Update</button>
</form>
</body>
</html>
