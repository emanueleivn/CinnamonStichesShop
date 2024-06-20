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
			<form
				action="${pageContext.request.contextPath}/admin/PaginaAmministratore"
				method="post">
				<input type="hidden" name="azioneAdmin" value="modify" />
				<button type="submit">Modifica catalogo</button>
			</form>
			<form
				action="${pageContext.request.contextPath}/admin/PaginaAmministratore"
				method="post">
				<input type="hidden" name="azioneAdmin" value="showOrders"/>
				<button type="submit">Visualizza ordini</button>
			</form>
			<form
				action="${pageContext.request.contextPath}/admin/PaginaAmministratore"
				method="post">
				<input type="hidden" name="azioneAdmin" value="showUsers" />
				<button type="submit">Visualizza utenti</button>
			</form>
		</div>

	</div>
	<footer>
<%@ include file="footer.jsp" %>
</footer>
</body>


</html>
