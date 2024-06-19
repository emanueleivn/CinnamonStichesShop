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
        <h2>Modifica Prodotto</h2>
        <form id="RegForm" action="${pageContext.request.contextPath}/admin/AggiornaProdotto" method="POST" onsubmit="return validate()" >
        		<input type="hidden" name="codice" value="${prodotto.codice}"/>
                <label for="descrizione">Descrizione Prodotto</label>
                <textarea class="inp" id="descrizione" name="descrizione" style="height:45px" title="Inserire descrizione prodotto, max 200 parole" onblur="textValidate(this)" required ></textarea>
                <span id="descrizione-error" class="error-message"></span>
                <label for="prezzo">Prezzo Prodotto</label>
                <input class="inp" type="number" step="0.01" id="prezzo" name="prezzo" placeholder="${prodotto.costo}" min="0.01" required>
                <button id="subProd" type="submit" value="Aggiungi Prodotto">Salva modifiche</button>
        </form>
	</div>
    <%@include file="footer.jsp"%>
</body>
</html>
