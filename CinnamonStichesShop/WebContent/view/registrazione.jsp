<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="initial-scale=1,width=device-width">
<link rel ="stylesheet" type="text/css" href = "${pageContext.request.contextPath}/css/page.css">
<script src="${pageContext.request.contextPath}/scripts/validate.js"></script>
<title>Registrazione</title>
</head>
<%@include file="header.jsp"%>
<body>
<div id="log-reg">
<form id="RegForm" action="${pageContext.request.contextPath}/RegistraUtenteServlet" method="POST" onsubmit="return validate()">
	<h4>Dati Profilo</h4>
	<label>Nome</label><input class="inp" type="text" id="name"name="nome" placeholder="Nome" 
		required pattern="^[A-Za-z]+$"maxlength="50" title="Inserire il nome,max 50 lettere">
	<label>Cognome</label><input class="inp" type="text" id="surname" name="cognome" placeholder="Cognome" 
		required pattern="^[A-Za-z]+$" maxlength="50" title="Inserire il cognome, max 50 parole">
	<label>Indirizzo</label><input class="inp" type="text"id="via" name="via" placeholder="Via" 
	required pattern="^[A-Za-z]+$" maxlength="50">
	<label>Cap   </label><input class="inp" type="text"id="cap" name="cap" placeholder="Cap"
	required pattern="[0-9]{5}" title="Inserisci un numero di 5 cifre">
	<label>Città</label> <input class="inp" type="text" id="city"name="city" placeholder="Città" 
	required pattern="^[A-Za-z]+$" title="Inserisci città">
	<h4>Dati Utente</h4>
	<label>Username </label><input class="inp" type="text"id="username" name="username" placeholder="Username" 
	required pattern="[A-Za-z0-9]+" title="Caratteri consentiti A-Z,a-z,0-9" maxlength="20" placeholder="Inserisci username" required>
	<label>Email</label><input class="inp" type="email"id="email" name="email" placeholder="E-mail" required>
	<label>Password</label><input class="inp" type="password"id="password" name="password" placeholder="Password" required
	pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$">
	<label>Ripeti Password</label><input class="inp" type="password" id="passwordR"name="ripetipassword" placeholder="Ripeti password" required>
	<input id="sub" type="submit" value="Crea account"><br><br>
</form>
</div>
<%@include file ="footer.jsp" %>
</body>

</html>