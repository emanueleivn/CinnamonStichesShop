<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Carrello</title>
     <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;700&display=swap" rel="stylesheet">
    <link rel="icon" href="${pageContext.request.contextPath}/images/icone/logo.ico">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/page.css">
</head>
<body>
	<%@include file="header.jsp" %>
	
	
	
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
                List<Prodotto> cart = (List<Prodotto>) session.getAttribute("cart");
                if (cart == null || cart.isEmpty()) {
            %>
                <tr>
                    <td colspan="5" style="text-align: center">Nessun prodotto inserito</td>
                </tr>
            <%
                } else {
                    for (Prodotto product : cart) {
                        double total = product.getCosto();
            %>
                <tr>
                    <td><%= product.getNome() %></td>
                    <td><%= product.getCosto() %></td>
                    <td>
                        <form action="removeProduct" method="post">
                            <input type="hidden" name="productId" value="<%= product.getCodice() %>">
                            <button type="submit">Rimuovi</button>
                        </form>
                    </td>
                    <td><%= total %></td>
                </tr>
            <%
                    }
                }
            %>
    </table>
    </div>
</body>
</html>
