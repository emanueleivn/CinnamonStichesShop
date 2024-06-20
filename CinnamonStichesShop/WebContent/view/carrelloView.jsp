<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="model.*"%>

<!DOCTYPE html>
<html>
<head>
<title>Carrello</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/page.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/prodotto.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/carrelloscript.js"></script>
</head>
<%@include file="header.jsp"%>
<body>
	<div class="cart-content">
		<table class="carrello-table">
			<tr>
				<th>Prodotto</th>
				<th></th>
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
				<td colspan="6" style="text-align: center">Nessun prodotto
					inserito</td>
			</tr>
			<%
			} else {
			for (ProdottoCarrello product : cart.getProdottiCarrello()) {
			%>
			<tr>
				<td><%=product.getProdotto().getNome()%></td>
				<td>
				<div class="immagine">
				<img src="${pageContext.request.contextPath}/images/products/<%=product.getProdotto().getImmagine()%>" alt="Immagine prodotto">
				</div>
				</td>
				<td><%=product.getProdotto().getCosto()%></td>
				<td>
					<form action="${pageContext.request.contextPath}/Carrello"
						method="POST">
						<input type="hidden" name="codice"
							value="<%=product.getProdotto().getCodice()%>" /> <input
							type="hidden" name="action" value="remove" />
						<button type="submit">Rimuovi</button>
					</form>
				</td>
				<td><input type="number"
					id="quantita_<%=product.getProdotto().getCodice()%>"
					name="quantita_<%=product.getProdotto().getCodice()%>" min="1"
					max="10" value="<%=product.getQuantita()%>"
					data-codice="<%=product.getProdotto().getCodice()%>"
					onChange="incrDecr(this)" required></td>
				<td id="totale_<%=product.getProdotto().getCodice()%>"><%=product.getTot()%></td>
			</tr>
			<%
			}
			%>
		</table>
		<div class="totale-carrello">
			Totale: <span id="totaleCarrello"><%=cart.getTotale()%></span>
		</div>
		<div class="bottoni">
			<form action="${pageContext.request.contextPath}/EffettuaOrdine" method="POST">
				<button type="submit">Acquista</button>
			</form>

			<form action="${pageContext.request.contextPath}/Carrello"
				method="POST">
				<input type="hidden" name="action" value="delete" />
				<button type="submit">Svuota Carrello</button>
			</form>
		</div>
		<%
		}
		%>
	</div>
</body>
</html>

