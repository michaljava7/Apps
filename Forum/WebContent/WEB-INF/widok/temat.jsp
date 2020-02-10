<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Forum</title>
</head>
<body>
<h1>Temat: ${temat.tytul}</h1>
<table border="1">
<tr>
<th>Login</th>
<th width="400" >Tresc</th>
<th>Data</th>
</tr>
<tr>
<td>${temat.uzytkownik.login }</td>
<td>${temat.tresc}</td>
<td>${temat.data}</td>
<c:forEach var="wpis" items="${temat.wpisy}">
<tr>
<td>${wpis.uzytkownik.login }</td>
<td>${wpis.tresc }</td>
<td>${wpis.data }</td></tr></c:forEach>
</table>
<form method="post">
<input type="hidden" name="id" value="${temat.id }" />
<p>Wprowadz tresc odpowiedzi</p>
<textarea name="tresc" rows="10" cols="40"></textarea><br />
<input type="submit" value="OK"/>
</form>
</body>
</html>