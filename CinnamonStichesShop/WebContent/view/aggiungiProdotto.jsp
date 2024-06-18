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

        <form action="${pageContext.request.contextPath}/admin/ModificaProdotto" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="nomeProdotto">Nome Prodotto</label>
                <input type="text" id="nomeProdotto" name="nomeProdotto"required>
            </div>
            <div class="form-group">
                <label for="descrizioneProdotto">Descrizione Prodotto</label>
                <textarea id="descrizioneProdotto" name="descrizioneProdotto" required></textarea>
            </div>
            <div class="form-group">
                <label for="prezzoProdotto">Prezzo Prodotto</label>
                <input type="number" step="0.01" id="prezzoProdotto" name="prezzoProdotto"required>
            </div>
            <div class="form-group">
                <label for="idCategoria">Categoria Prodotto</label>
                <input type="number" id="idCategoria" name="idCategoria" required>
            </div>
            <div class="form-group">
                <label for="file">Immagine Prodotto (JPEG, PNG)</label>
                <input type="file" id="file" name="file" accept=".jpeg, .jpg, .png">
             </div>
            <div class="form-group">
                <button type="submit" name="azione" value="aggiungi">Aggiungi Prodotto</button>
            </div>
        </form>
	</div>
    <%@include file="footer.jsp"%>
</body>
</html>