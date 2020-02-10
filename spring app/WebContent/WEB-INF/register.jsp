<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.0/css/bootstrap.min.css"
	integrity="sha384-PDle/QlgIONtM1aqA2Qemk5gPOE7wFq8+Em+G/hmo5Iq0CCmYZLv3fVRDJ4MMwEA" crossorigin="anonymous">
<title>Register user</title>
</head>
<body>
	<div style="margin: auto; width: 500px; padding-top: 100px">
		<form:form method="post" modelAttribute="user">

			<div class="form-group">
				<label>User Name</label>
				<form:input path="userName" placeholder="User Name" type="text" class="form-control" />
				<form:errors path="userName" style="color:red" class="form-text text-muted" />
			</div>

			<div class="form-group">
				<label>Password</label>
				<form:input path="password" placeholder="Password" type="password" class="form-control" />
				<form:errors path="password" style="color:red" class="form-text text-muted" />
				<br/>
				<label>Confirm password</label>
				<form:input path="confirmPassword" placeholder="Confirm Password" type="password" class="form-control" />
				<form:errors path="confirmPassword" style="color:red" class="form-text text-muted" />
			</div>

			<div class="form-group">
				<label>Email</label>
				<form:input path="email" placeholder="email" type="text" class="form-control" />
				<form:errors path="email" style="color:red" class="form-text text-muted" />
			</div>
			<form:button type="submit" class="btn btn-success">Register</form:button>
		</form:form>
	</div>
</body>
</html>