<%@page import="javax.sql.DataSource"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ordini</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/page.css">
</head>
<%@ include file="header.jsp" %>
<body>
    <div class="cart-content">
        <table class="carrello-table ordine-table">
            <tr>
                <th>Codice ordine</th>
                <th>Data</th>
                <th>Indirizzo Spedizione</th>
                <th>Totale</th>
                <th>Stato</th>
                <th></th>
            </tr>
            <%
                List<Ordine> ordini = (List<Ordine>)request.getAttribute("ordini");
                if (ordini == null || ordini.isEmpty()) {
            %>
                <tr>
                    <td colspan="6" style="text-align: center">Nessun ordine trovato</td>
                </tr>
            <%
                } else {
                    for (Ordine ordine : ordini) {
            %>
                <tr>
                    <td><%= ordine.getCodiceOrdine() %> </td>
                    <td><%= ordine.getData() %></td>
                    <td><%= ordine.getIndirizzoSpedizione() %></td>
                    <td><%= ordine.getTot() %></td>
                    <td><%= ordine.getStato() %></td>
                    <td>
                    <form action="${pageContext.request.contextPath}/DettagliOrdine" method="post">
                    	<input type="hidden" name="codice" value="<%=ordine.getCodiceOrdine()%>"/>
                    	<button id="dettagli-ordine" type="submit">Dettagli ordine...</button>
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
<%@ include file="footer.jsp" %>
</html>

