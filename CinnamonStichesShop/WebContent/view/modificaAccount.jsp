<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="initial-scale=1,width=device-width">
<link rel ="stylesheet" type="text/css" href = "${pageContext.request.contextPath}/styles/page.css">
<script src="${pageContext.request.contextPath}/scripts/validate.js"></script>
<title>Modifica Account</title>
</head>
<%@include file="header.jsp"%>
<body>
<div id="log-reg">
<form id="RegForm" action="${pageContext.request.contextPath}/ModificaAccount" method="POST" onsubmit="return validate()">
	<h4>Dati Profilo</h4>
	<label>Nome</label>
	<input class="inp" type="text" id="name"name="nome" value="${user.nome}" 
		required pattern="^[A-Za-z]+$"maxlength="100" title="Inserire il nome,max 50 lettere" onblur="textValidate(this)">
	 <span id="name-error" class="error-message"></span>
	<label>Cognome</label>
	<input class="inp" type="text" id="surname" name="cognome" value="${user.cognome}"onblur="textValidate(this)"
		required pattern="^[A-Za-z\s]+$" maxlength="100" title="Inserire il cognome, max 50 parole">
	 <span id="name-error" class="error-message"></span>
	<label>Indirizzo</label>
	<input class="inp" type="text" id="via" name="via" value="${user.via}" onblur="textValidate(this)"
    required pattern="^\w+[\w\s]+\w+$" maxlength="50">
	<span id="password-error" class="error-message"></span>
	<label>Cap</label>
	<input class="inp" type="text"id="cap" name="cap" value="${user.cap}" onblur="capValidate(this)"
	required pattern="[0-9]{5}" title="Inserisci un numero di 5 cifre">
	<span id="password-error" class="error-message"></span>
	<label>Città</label> 
	<input class="inp" type="text" id="city"name="city" value="${user.città}" onblur="textValidate(this)"
	required pattern="^[A-Za-z\s]+$" title="Inserisci città">
	<span id="password-error" class="error-message"></span>
	<h4>Dati Utente</h4>
	<label>Username </label>
	<input class="inp" type="text"id="username" name="user"value="${user.username}" onblur="textValidate(this)"
	required pattern="^\w+[\w\s]+\w+$" title="Caratteri consentiti A-Z,a-z,0-9" maxlength="20" placeholder="Inserisci username" required>
	<span id="password-error" class="error-message"></span>
	<label>Email</label>
	<input class="inp" type="email"id="email" name="email" value="${user.email}" onblur="emailValidate(this)" required>
	<span id="password-error" class="error-message"></span>
	<label>Password</label>
	<input class="inp" type="password" id="password" name="password" placeholder="Password" onblur="passwordValidate(this)"
    required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$"title="Inserire almeno una maiuscola,una minuscola e un numero">
	<span id="password-error" class="error-message"></span>
	<label>Ripeti Password</label><input class="inp" type="password" id="passwordR"name="ripetipassword" placeholder="Ripeti password"  onblur="passwordMatchValidate(document.getElementById('password').value, this.value)"  required>
	<span id="password-error" class="error-message"></span>
	<p id="errorMessage"></p> 
	<input class="sub" type="submit" value="Salva Modifiche"><br><br>
</form>
</div>
<%@include file ="footer.jsp" %>
</body>

</html>