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
<title>Modifica Catalogo</title>
</head>
<%@include file="header.jsp"%>
<body>
  <h1>Prodotti del Catalogo</h1>
  <div class="catalogo">
      <div class="prodotto nuovo-prodotto">
                 <form action="${pageContext.request.contextPath}/admin/PaginaAmministratore" method="post">
                      <input type="hidden" name="azioneAdmin" value="add"/>
                      <button type="submit">Aggiungi nuovo prodotto</button>
                  </form>
      </div>
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
                  <form action="${pageContext.request.contextPath}/admin/ModificaProdotto" method="post">
                      <input type="hidden" name="codice" value="<%= prodotto.getCodice() %>"/>
                      <input type="hidden" name="azioneAdmin" value="update"/>
                      <button type="submit">Modifica</button>
                  </form>
                  <form action="${pageContext.request.contextPath}/admin/ModificaProdotto" method="post">
                      <input type="hidden" name="codice" value="<%= prodotto.getCodice() %>"/>
                      <input type="hidden" name="azioneAdmin" value="delete"/>
                      <button type="submit">Elimina</button>
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
