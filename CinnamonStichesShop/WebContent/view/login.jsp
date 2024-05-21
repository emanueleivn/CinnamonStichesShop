<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<link rel ="stylesheet" type="text/css" href = "${pageContext.request.contextPath}/css/page.css">
<title>Login Page</title>
</head>
<%@include file="header.jsp"%>
<body>
	<div id="log-reg">
	<h4>Login</h4>
	<form action="../LoginServlet" method="POST">
		<label>Username:</label> 
		<input id="inp" type="text" name="user" placeholder="username" required="required" />
		<label>Password:</label>
		<input id="inp" type="password" id="password" name="pass" placeholder="password" required="required" /> 
		<input id="sub" type="submit" value="LOGIN">
	</form>
	<p>
		<a id="anchor" href="./view/registrazione.jsp">Non sei registrato? Registrati ora.</a>
	</p>
	</div>
	<%@include file="footer.jsp"%>
</body>

</html>