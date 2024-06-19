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
    <div id="log-reg">
        <h2>Gestione Prodotto</h2>
        <form id="RegForm" action="${pageContext.request.contextPath}/admin/AggiungiProdotto" enctype="multipart/form-data" method="POST" >
                <label for="nome">Nome Prodotto</label>
                <input class="inp" type="text" id="nome" name="nome"required>
                <label for="descrizione">Descrizione Prodotto</label>
                <textarea class="inp" id="descrizione" name="descrizione" required style="height:45px"></textarea>
                <label for="prezzo">Prezzo Prodotto</label>
                <input class="inp" type="number" step="0.01" id="prezzo" name="prezzo"required>
                <label for="file">Immagine Prodotto (JPEG, PNG, GIF)</label>
                <input class="inp" type="file" id="file" name="file" accept="image/png, image/gif, image/jpeg" required>
                <button id="subProd" type="submit" value="Aggiungi Prodotto">Aggiungi Prodotto</button>
        </form>
	</div>
    <%@include file="footer.jsp"%>
</body>
</html>