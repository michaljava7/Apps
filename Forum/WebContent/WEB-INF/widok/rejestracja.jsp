<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Rejestracja</title>
</head>
<body>
<h1>Rejestracja</h1>
${blad }
<form method="post">
<p>Login</p>
<input type="text" name="login" maxlength="30" >
<p>Wprowadz haslo</p>
<input type="password" name="haslo" maxlength="30" >
<p>Potwierdz haslo</p>
<input type="password" name="haslo2" maxlength="30"><br />
<input type="submit"  value="Rejestruj" />
</form>
</body>
</html>