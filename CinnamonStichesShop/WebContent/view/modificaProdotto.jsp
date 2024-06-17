<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css"
    href="${pageContext.request.contextPath}/css/page.css">
<title>Gestione Prodotto</title>
</head>
<%@include file="header.jsp"%>
<body>
    <div class="container">
        <h2>Gestione Prodotto</h2>

        <form action="${pageContext.request.contextPath}/ModificaProdotto" method="post">
            <div class="form-group">
                <label for="nomeProdotto">Nome Prodotto</label>
                <input type="text" id="nomeProdotto" name="nomeProdotto" value="${prodotto.nome}" readonly>
            </div>
            <div class="form-group">
                <label for="descrizioneProdotto">Descrizione Prodotto</label>
                <textarea id="descrizioneProdotto" name="descrizioneProdotto" required>${prodotto != null ? prodotto.descrizione : ''}</textarea>
            </div>
            <div class="form-group">
                <label for="prezzoProdotto">Prezzo Prodotto</label>
                <input type="number" step="0.01" id="prezzoProdotto" name="prezzoProdotto" value="${prodotto != null ? prodotto.costo : ''}" required>
            </div>
            <div class="form-group">
                <button type="submit" name="azione" value="modifica">Modifica Prodotto</button>
            </div>
        </form>
        
    </div>
    <%@include file="footer.jsp"%>
</body>
</html>
