<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home </title>
</head>
<body>
<div style="text-align: center" class="">
<a href="login">Login</a>
<a href="register">Rejestracja</a>
<a href="userpage">User Page</a>
<a href="products/All?count=${size}">Products</a>
<a href="products1/All">Matrix products</a>
</div>
<h1>Hello ${nick}</h1>
<ul>
<c:forEach var="product" begin="0" end="1" items="${products}">
<c:if test="${product.category eq 'pralka'}">
<li>${product.name} ${product.category}</li>
</c:if>

</c:forEach>
</ul>
</body>
</html>