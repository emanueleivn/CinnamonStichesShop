<%@page import="javax.sql.DataSource"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="model.*"%>
<!DOCTYPE html>
<html>
<head>
<title>Utenti</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/styles/prodotto.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/styles/page.css">
</head>

<%@ include file="header.jsp"%>
<body>
	<div class="cart-content">
		<table class="carrello-table ordine-table utenti-table">
			<tr>
				<th>Codice utente</th>
				<th>Username</th>
				<th>Email</th>
				<th>Nome</th>
				<th>Cognome</th>
				<th>Admin</th>
			</tr>
			<%
			List<Utente> utenti = (List<Utente>) request.getAttribute("utenti");
			if (utenti == null || utenti.isEmpty()) {
			%>
			<tr>
				<td colspan="6" style="text-align: center">Nessun utente
					trovato</td>
			</tr>
			<%
			} else {
			for (Utente utente : utenti) {
			%>
			<tr>
				<td><%=utente.getId()%></td>
				<td><%=utente.getUsername()%></td>
				<td><%=utente.getEmail()%></td>
				<td><%=utente.getNome()%></td>
				<td><%=utente.getCognome()%></td>
				<td><%=utente.getIsAdmin()%></td>
			</tr>
			<%
			}
			}
			%>
		</table>
	</div>
</body>
<footer style="flex: 2;">
	<%@ include file="footer.jsp"%>
</footer>

</html>
