<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Zaloguj sie</h1>
<form method="post" action="j_security_check">
<p>Login</p>
<input type="text" name="j_username" />
<p>Haslo </p>
<input type="password" name="j_password" /> <br />
<input type="submit" value="zaloguj" />
</form>
<a href="rejestruj">Rejestracja</a>
</body>
</html>