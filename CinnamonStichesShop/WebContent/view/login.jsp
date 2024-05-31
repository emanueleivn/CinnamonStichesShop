<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<link rel ="stylesheet" type="text/css" href = "${pageContext.request.contextPath}/css/page.css">
<script src="${pageContext.request.contextPath}/scripts/validate.js"></script>
<title>Login Page</title>
</head>
<%@include file="header.jsp"%>
<body>
	<div id="log-reg">
	<h4>Login</h4>
	<form action="${pageContext.request.contextPath}/LoginServlet" method="POST" onsubmit="return validate()">
		<label>Username</label> 
		<input class="inp" type="text" id="username" name="user" placeholder="username" required 
		pattern="[A-Za-z0-9]+" title="Caratteri consentiti A-Z,a-z,0-9"/>
		<label>Password</label>
		<input class="inp" type="password" id="password" name="pass" placeholder="password" required /> 
		<input class="sub" type="submit" value="LOGIN">
	</form>
	<p>
		<a id="anchor" href="./view/registrazione.jsp">Non sei registrato? Registrati ora.</a>
	</p>
	</div>
	<%@include file="footer.jsp"%>
</body>

</html>