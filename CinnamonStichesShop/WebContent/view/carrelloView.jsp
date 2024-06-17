<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="model.*"%>
<!DOCTYPE html>
<html>
<head>
<title>Carrello</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/page.css">
</head>
<%@include file="header.jsp"%>
<body>
	<div class="cart-content">
		<table class="carrello-table">
			<tr>
				<th>Prodotto</th>
				<th>Prezzo</th>
				<th>Rimuovi</th>
				<th>Quantità</th>
				<th>Totale</th>
			</tr>
			<%
			Carrello cart = (Carrello) session.getAttribute("carrello");
			if (cart == null || cart.getNumeroProdotti() == 0) {
			%>
			<tr>
				<td colspan="5" style="text-align: center">Nessun prodotto
					inserito</td>
			</tr>
			<%
			} else {
			for (ProdottoCarrello product : cart.getProdottiCarrello()) {
			%>
			<tr>
				<td><%=product.getProdotto().getNome()%></td>
				<td><%=product.getProdotto().getCosto()%></td>
				<td>
					<form action="/Carrello" method="POST">
						<input type="hidden" name="productId"
							value="<%=product.getProdotto().getCodice()%>" /> <input
							type="hidden" name="action" value="remove" />
						<button type="submit">Rimuovi</button>
					</form>
				</td>
				<td><%=product.getQuantita()%></td>
				<td><%=product.getTot()%></td>
			</tr>
			<%}%>
		</table>
		<div class="bottoni">
			<form action="/EffettuaOrdineServlet" method="POST">
				<button type="submit">Acquista</button>
			</form>

			<form action="//Carrello" method="POST">
				<input type="hidden" name="action" value="svuota" />
				<button type="submit">Svuota Carrello</button>
			</form>
		</div>
		<%}%>
	</div>

</body>
</html>
