<%@ page import="javax.sql.DataSource"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="model.*"%>
<!DOCTYPE html>
<html>
<head>
<title>Ordini Complessivi</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/styles/page.css">
<script type="text/javascript" src= "${pageContext.request.contextPath}/scripts/cercaOrdineCliente.js"></script>
<script type="text/javascript" src= "${pageContext.request.contextPath}/scripts/cercaOrdine.js"></script>
</head>
<%@ include file="header.jsp"%>
<body>

	<div class="cart-content">
		<div class="filter-form">
			<form id="filterForm" action="${pageContext.request.contextPath}/admin/Ordini" method="POST">
				<div id="date">
					Filtra per data:&nbsp;<label for="filterDate1"> da: </label> 
					<input type="date" id="filterDate1" name="filterDate1" required> <label
						for="filterDate2">a: </label> 
					<input type="date" id="filterDate2"	name="filterDate2" required">
					<button type="submit" id="applyFilter">Applica Filtro</button>
				</div>
			</form>
				<div id="cercaCliente">
					<label for="inputCliente">Codice cliente:</label> <input type="text"
						id="inputCliente" name="searchInput"
						placeholder="Inserisci codice cliente" >
					<button id="cercaClienteButton">Cerca</button>
				</div>
				<div id="cercaOrdine">
					<label for="inputOrdine">Codice ordine:</label> <input type="text"
						id="inputOrdine" name="searchInput"
						placeholder="Inserisci codice ordine">
					<button id="cercaOrdineButton" >Cerca</button>
					<form action="${pageContext.request.contextPath}/admin/PaginaAmministratore" method="POST">
					<input type="hidden" name="azioneAdmin" value="showOrders"/>
					<button type="submit">Tutti</button>
					</form>
				</div>
		</div>

		<table class="carrello-table ordine-table" id="ordiniTable">
			<tr>
				<th>Codice ordine</th>
				<th>Codice cliente</th>
				<th>Data</th>
				<th>Indirizzo Spedizione</th>
				<th>Totale</th>
				<th></th>
			</tr>
			<%
			List<Ordine> ordini = (List<Ordine>) request.getAttribute("ordini");
			if (ordini == null || ordini.isEmpty()) {
			%>
			<tr>
				<td colspan="6" style="text-align: center">Nessun ordine
					trovato</td>
			</tr>
			<%
			} else {
			for (Ordine ordine : ordini) {
			%>
			<tr>
				<td><%=ordine.getCodiceOrdine()%></td>
				<td><%=ordine.getIdCliente()%></td>
				<td><%=ordine.getData()%></td>
				<td><%=ordine.getIndirizzoSpedizione()%></td>
				<td><%=ordine.getTot()%></td>

				<td>
					<form action="${pageContext.request.contextPath}/DettagliOrdine"
						method="post">
						<input type="hidden" name="codice"
							value="<%=ordine.getCodiceOrdine()%>" />
						<button id="dettagli-ordine" type="submit">Dettagli
							ordine</button>
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
<%@ include file="footer.jsp" %>
