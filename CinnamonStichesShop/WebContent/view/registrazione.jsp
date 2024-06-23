<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="initial-scale=1,width=device-width">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/styles/page.css">
<script src="${pageContext.request.contextPath}/scripts/validate.js"></script>
<title>Registrazione</title>

</head>
<%@include file="header.jsp"%>
<body onload="document.getElementById('name').focus()">
	<div id="log-reg">
		 <form id="RegForm" action="${pageContext.request.contextPath}/Registrazione" method="POST" onsubmit="return validate()">
        <h4>Dati Profilo</h4>
        <label>Nome</label>
        <input class="inp" type="text" id="name" name="nome" placeholder="Nome" required maxlength="100" title="Inserire il nome, max 50 lettere" oninput="textValidate(this)">
        <span id="name-error" class="error-message"></span>
        <label>Cognome</label>
        <input class="inp" type="text" id="surname" name="cognome" placeholder="Cognome" required maxlength="100" title="Inserire il cognome, max 50 parole" oninput="textValidate(this)">
        <span id="surname-error" class="error-message"></span>
        <label>Indirizzo spedizione</label>
        <input class="inp" type="text" id="via" name="via" placeholder="Via" required maxlength="50" oninput="textValidate(this)">
        <span id="via-error" class="error-message"></span>
        <label>Cap</label>
        <input class="inp" type="text" id="cap" name="cap" placeholder="Cap" required pattern="[0-9]{5}" title="Inserisci un numero di 5 cifre" oninput="capValidate(this)">
        <span id="cap-error" class="error-message"></span>
        <label>Città</label>
        <input class="inp" type="text" id="city" name="city" placeholder="Città" required title="Inserisci città" oninput="textValidate(this)">
        <span id="city-error" class="error-message"></span>
        <h4>Dati Utente</h4>
        <label>Username</label>
        <input class="inp" type="text" id="username" name="user" placeholder="Username" required maxlength="20" required oninput="textValidate(this)">
        <span id="username-error" class="error-message"></span>
        <label>Email</label>
        <input class="inp" type="email" id="email" name="email" placeholder="E-mail" required oninput="emailValidate(this)">
        <span id="email-error" class="error-message"></span>
        <label>Password</label>
        <input class="inp" type="password" id="password" name="password" placeholder="Password" required title="Inserire almeno una maiuscola, una minuscola e un numero" oninput="passwordValidate(this)">
        <span id="password-error" class="error-message"></span>
        <label>Ripeti Password</label>
        <input class="inp" type="password" id="passwordR" name="ripetipassword" placeholder="Ripeti password" oninput="passwordMatchValidate(document.getElementById('password').value, this.value)" required>
        <span id="passwordR-error" class="error-message"></span>
        <p id="errorMessage"></p>
        <input class="sub" type="submit" value="Registrati"><br><br>
    </form>

	</div>
	<%@include file="footer.jsp"%>
</body>

</html>