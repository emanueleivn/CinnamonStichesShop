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
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/page.css">
</head>
<%@include file="header.jsp"%>
<body>
<%Ordine ordine = (Ordine) request.getAttribute("ordine");%>
	<div class="cart-content">
		<table class="carrello-table dettagliTable">
		<caption style="caption-side: bottom; margin-top:10px; font-weight: bold;">
		Totale : <%=ordine.getTot()/2%>
		</caption>
			<tr>
				<th>Codice Prodotto</th>
				<th></th>
				<th>Nome</th>
				<th>Quantità</th>
			</tr>
			<%
			List<ProdottoCarrello> lista = (List<ProdottoCarrello>) request.getAttribute("prodottiOrdine");
			if (lista == null) {
			%>
			<tr>
				<td colspan="4" style="text-align: center">Nessun prodotto ordinato</td>
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
			</tr>
			<%
			}
			%>
		</table>
		
	</div>
	<section class="ordine-sezione">
	<%Utente utente = (Utente) request.getAttribute("utente");%>
		Cliente : <%=utente.getNome()%>&nbsp;<%=utente.getCognome()%><br> 
		Indirizzo : <%=ordine.getIndirizzoSpedizione() %><br>
		Data: <%=ordine.getData()%><br><br>		
	</section>
	<%
		}
		%>
</body>
<%@ include file="footer.jsp" %>
</html>