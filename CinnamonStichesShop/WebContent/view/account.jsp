<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/styles/page.css">
<title>Il Mio Account</title>
</head>
<%@include file="header.jsp"%>
<body>
	<%if (session.getAttribute("user") != null){ %>
	<div class="container container-account">
		<h2>Dettagli Utente</h2>
		<table>
			<tr>
				<th>Nome</th>
				<td>${user.nome}</td>
			</tr>
			<tr>
				<th>Cognome</th>
				<td>${user.cognome}</td>
			</tr>
			<tr>
				<th>Email</th>
				<td>${user.email}</td>
			</tr>
			<tr>
				<th>Username</th>
				<td>${user.username}</td>
			</tr>
			<tr>
				<th>Via</th>
				<td>${user.via}</td>
			</tr>
			<tr>
				<th>CAP</th>
				<td>${user.cap}</td>
			</tr>
			<tr>
				<th>Città</th>
				<td>${user.città}</td>
			</tr>
			<tr>
				<th>Amministratore</th>
				<td>${user.isAdmin ? "Sì" : "No"}</td>
			</tr>
		</table>
			<div class="bottoni">
		<form action="${pageContext.request.contextPath}/Logout" method="GET">
			<button type="submit" name="Logout">Logout</button>
		</form>
		
		<form action="${pageContext.request.contextPath}/AccountManage" method="POST">
			<button type="submit" name="Manage">Modifica</button>
		</form>
		
		<form action="${pageContext.request.contextPath}/Ordini" method="POST">
			<button type="submit" name="Ordini">Visualizza Ordini</button>
		</form>
		</div>
	</div>
	<%} else response.sendRedirect("${pageContext.request.contextPath}/Login");%>
	<%@include file="footer.jsp"%>
</body>
</html>
