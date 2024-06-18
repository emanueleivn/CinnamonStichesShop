<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Errore</title>
</head>
<%@include file="header.jsp"%>
<body>
	<%
	String msg = (String) request.getAttribute("errorMessage");
	if (msg == null)
		msg = "";
	%>
	<div style="text-align: center">
	<h1 style="color: red">Errore</h1>
	<h3><%=msg%></h3>
	</div>
	
	
</body>

</html>