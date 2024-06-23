<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css"
    href="${pageContext.request.contextPath}/styles/page.css">
<script src="${pageContext.request.contextPath}/scripts/validate.js"></script>
<title>Gestione Prodotto</title>
</head>
<%@include file="header.jsp"%>
<body>
     <div id="log-reg">
        <h2>Nuovo Prodotto</h2>
        <form id="RegForm" action="${pageContext.request.contextPath}/admin/AggiungiProdotto" enctype="multipart/form-data" method="POST" onsubmit="return validate()" >
                <label for="nome">Nome Prodotto</label>
                <input class="inp" type="text" id="nome" name="nome" required autofocus
                title="Inserire nome prodotto, max 50 parole" maxlength="50" oninput="textValidate(this)" required>
                <span id="nome-error" class="error-message"></span>
                <label for="descrizione">Descrizione Prodotto</label>
                <textarea class="inp" id="descrizione" name="descrizione"style="height:45px"
                title="Inserire descrizione prodotto, max 200 parole" oninput="textAreaValidate(this)" required ></textarea>
                <span id="descrizione-error" class="error-message"></span>
                <label for="prezzo">Prezzo Prodotto</label>
                <input class="inp" type="number" step="0.01" id="prezzo" name="prezzo" min="0.01" oninput="prezzoValidate(this)" required>
                <span id="prezzo-error" class="error-message"></span>
                <label for="file">Immagine Prodotto (JPEG, PNG, GIF)</label>
                <input class="inp" type="file" id="file" name="file" accept="image/png, image/gif, image/jpeg"
                title="Inserire un immagine,estensione file accettati: png,jpeg,gif"  onchange="imgValidate(this)" required>
               
                <button id="subProd" type="submit" value="Aggiungi Prodotto">Aggiungi Prodotto</button>
        </form>
	</div>
    <%@include file="footer.jsp"%>
</body>
</html>