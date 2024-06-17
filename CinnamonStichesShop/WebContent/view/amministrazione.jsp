<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/page.css">
<title>Gestione Prodotto</title>
</head>
<%@include file="header.jsp"%>
<body>
	<div class="container">
		<div class="bottoni">
		<form action="${pageContext.request.contextPath}/PaginaAmministratore?val=add" method="GET">
			<button type="submit" name="aggiungi">Aggiungi prodotto</button>
		</form>
			<form action="${pageContext.request.contextPath}/PaginaAmministratore?val=mod" method="GET">
			<button type="submit" name="modifica">Modifica prodotto</button>
		</form>
			<form action="${pageContext.request.contextPath}/PaginaAmministratore?val=showOrders" method="GET">
			<button type="submit" name="Logout">Visualizza ordini</button>
		</form>
			<form action="${pageContext.request.contextPath}/PaginaAmministratore?val=showUsers" method="GET">
			<button type="submit" name="Logout">Visualizza utenti</button>
		</form>
			
		</div>
	</div>
	<%@include file="footer.jsp"%>
</body>

</html>
