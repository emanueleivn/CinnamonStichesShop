<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/page.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/prodotto.css">
<title>Catalogo</title>
</head>
<%@include file="header.jsp"%>
<body>
  <h1>Prodotti del Catalogo</h1>
    <div class="catalogo">
            <% 
                List<Prodotto> prodotti = (List<Prodotto>) request.getAttribute("prodotti");
                for (Prodotto prodotto : prodotti) {
            %>
                <div class="prodotto">
                    <div class="immagine">
                        <img src="${pageContext.request.contextPath}/images/products/<%= prodotto.getImmagine() %>" alt="Immagine Prodotto"/>
                    </div>
                    <div class="dettagli">
                        <p><strong>Nome:</strong> <%= prodotto.getNome() %></p>
                        <p><strong>Descrizione:</strong> <%= prodotto.getDescrizione() %></p>
                        <p><strong>Costo:</strong> <%= prodotto.getCosto() %></p>
                        <p><strong>Disponibilità:</strong> <%= prodotto.getIsDisp() ? "Disponibile" : "Non disponibile" %></p>
                        <div class="action">
                        <form action="${pageContext.request.contextPath}/Carrello" method="post">
                        	<input type="hidden" name="action" value="add"/>
                            <input type="hidden" name="codice" value="<%=prodotto.getCodice()%>"/>
                            <label for="quantita_<%= prodotto.getCodice() %>">Quantità:</label>
                            <input type="number" id="quantita_<%= prodotto.getCodice() %>" name="quantita_<%= prodotto.getCodice() %>" min="1" max="10" value="1" required>
                            <button type="submit">Aggiungi al Carrello</button>
                        </form>
                        <form action="${pageContext.request.contextPath}/AggiungiAiPreferiti" method="post">
                            <input type="hidden" name="codiceProdotto" value="<%= prodotto.getCodice() %>"/>
                            <button type="submit">Aggiungi ai Preferiti</button>
                        </form>
                        </div>
                    </div>
                </div>
            <% 
                } 
            %>
    </div>
</body>
<%@include file="footer.jsp"%>
</html>

