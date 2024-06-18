<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="model.*"%>
<!DOCTYPE html>
<html>
<head>
<title>Preferiti</title>
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
				<th></th>
				<th>Prodotto</th>
				<th>Prezzo</th>
				<th></th>
				<th></th>
			</tr>
			<%
			List<Prodotto> preferiti = (List<Prodotto>) request.getAttribute("preferiti");
			if (preferiti == null || preferiti.isEmpty()) {
			%>
			<tr>
				<td colspan="5" style="text-align: center">Nessun prodotto
					inserito</td>
			</tr>
			<%
			} else {
			for (Prodotto product : preferiti) {
			%>
			<tr>
				<td>
					<div class="immagine">
						<img
							src="${pageContext.request.contextPath}/images/products/<%= product.getImmagine() %>"
							alt="Immagine Prodotto" />
					</div>
				</td>
				<td><%=product.getNome()%></td>
				<td><%=product.getCosto()%></td>
				<td>
					<form action="/Preferiti" method="post">
						<input type="hidden" name="action"
							value="remove">
						<input type="hidden" name="productId"
							value="<%=product.getCodice()%>">
						<button type="submit">Rimuovi</button>
					</form>
				</td>
				<td>
					<form action="/Preferiti" method="post">
					<input type="hidden" name="action"
							value="addCart">
						<input type="hidden" name="productId"
							value="<%=product.getCodice()%>">
						<button type="submit">Aggiungi al carrello</button>
					</form>
				</td>
			</tr>
			<%
			}
			}
			%>
		</table>
	</div>
</body>
</html>
