<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="initial-scale=1,width=device-width">
<link rel ="stylesheet" type="text/css" href = "${pageContext.request.contextPath}/css/page.css">

<title>Registrazione</title>
</head>
<%@include file="header.jsp"%>
<body>
<div id="log-reg">
<form action="../RegistraUtenteServlet" method="POST">
	<h4>Dati Profilo</h4>
	<label>Nome</label><input id="inp" type="text" name="nome" placeholder="Nome" required>
	<label>Cognome</label><input id="inp" type="text" name="cognome" placeholder="Cognome" required>
	<label>Indirizzo</label><input id="inp" type="text" name="via" placeholder="Via" >
	<label>Cap   </label><input id="inp" type="text" name="cap" placeholder="Cap" >
	<label>Città</label> <input id="inp" type="text" name="city" placeholder="Città">
	<h4>Dati Utente</h4>
	<label>Username </label><input id="inp" type="text" name="username" placeholder="Username" required>
	<label>Email</label><input id="inp" type="email" name="email" placeholder="E-mail" required>
	<label>Password</label><input id="inp" type="password" name="password" placeholder="Password" required>
	<label>Ripeti Password</label><input id="inp" type="password" name="ripetipassword" placeholder="Ripeti password" required>
	<input id="sub" type="submit" value="Crea account"><br><br>
</form>
</div>
<%@include file ="footer.jsp" %>
</body>

</html>