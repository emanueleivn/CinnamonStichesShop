<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registrazione</title>
</head>
<body>
<%@include file ="header.jsp" %>
<form action="${pageContext.request.contextPath}/RegistraUtenteServlet" method="POST">
<fieldset>
<legend>Dati Profilo</legend>
	Nome:<br><input type="text" name="nome" placeholder="Nome" required><br><br>
	Cognome:<br><input type="text" name="cognome" placeholder="Cognome" required><br><br>
	Indirizzo:<br><input type="text" name="via" placeholder="Via" > &nbsp 
	Cap:<input type="text" name="cap" placeholder="Cap" > &nbsp
	Città:<input type="text" name="city" placeholder="Città"><br><br>
</fieldset>
<fieldset>
<legend>Dati Utente</legend>
	Username: <br><input type="text" name="username" placeholder="Username" required><br><br>
	E-mail: <br><input type="email" name="email" placeholder="E-mail" required><br><br>
	Password: <br><input type="password" name="password" placeholder="Password" required><br><br>
	Ripeti Password: <br><input type="password" name="ripetipassword" placeholder="Ripeti password" required><br><br>
	<input type="submit" value="Crea account">
</fieldset>
</form>
<% String errori = (String)request.getAttribute("errors");
	if (errori != null){
%>		
	Errori: <%=errori %>
<%} %>	
<%@include file ="footer.jsp" %>
</body>
</html>