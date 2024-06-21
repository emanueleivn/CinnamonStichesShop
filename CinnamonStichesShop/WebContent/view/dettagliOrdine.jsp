<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="model.*"%>
<!DOCTYPE html>
<html>
<head>
<style>
.ordine-sezione {
    text-align: center;
    font-weight: bold;
    margin: 0px auto 20px;
    display:block;
    align-items: flex-start;
}
</style>
<meta charset="UTF-8">
<title>Dettagli ordine</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/page.css">
</head>
<%@include file="header.jsp"%>
<body>
	<div class="cart-content">
		<table class="carrello-table dettagliTable">
			<tr>
				<th>Codice Prodotto</th>
				<th></th>
				<th>Nome</th>
				<th>Quantità</th>
				<th>Totale</th>
			</tr>
			<%
			List<ProdottoCarrello> lista = (List<ProdottoCarrello>) request.getAttribute("prodottiOrdine");
			if (lista == null) {
			%>
			<tr>
				<td colspan="5" style="text-align: center">Nessun prodotto ordinato</td>
			</tr>
			<%
			} else {
			for (ProdottoCarrello product : lista) {
			%>
			<tr>
				<td><%=product.getProdotto().getCodice() %></td>
				<td>
				<div class="immagine immagineTabella">
				<img src="${pageContext.request.contextPath}/images/products/<%=product.getProdotto().getImmagine()%>" alt="Immagine prodotto">
				</div>
				</td>
				<td><%=product.getProdotto().getNome()%></td>
				<td><%=product.getQuantita() %></td>
				<td><%=product.getTot()%></td>
			</tr>
			<%
			}
			%>
		</table>
		
	</div>
	<section class="ordine-sezione">
		<%Ordine ordine = (Ordine) request.getAttribute("ordine");%>
		Indirizzo : <%=ordine.getIndirizzoSpedizione() %><br>
		Data: <%=ordine.getData()%><br>
		Codice Cliente: <%=ordine.getIdCliente()%><br>
		
	</section>
	<%
		}
		%>
</body>
<%@ include file="footer.jsp" %>
</html>