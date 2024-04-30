<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Page</title>
</head>
<body>

<form action="../LoginServlet" method="POST">
<fieldset>
<legend>Login</legend>

<label>Username:</label><br>
<input type="text" name="user" placeholder="username" required="required"/><br>
<label>Password:</label><br>
<input type="password" id="password" name="pass" placeholder="password" required="required"/><br><br>
  <input type="submit" value="LOGIN">
 
 <%if(request.getAttribute("error") != null) {%>
    <p style='color: red;'>Username o password non corretti!</p>
 <% } %>
 <p><a href="registrazione.jsp">Non sei registrato? Registrati ora.</a>
 
</fieldset>
 </form>
<br><br>

<%@include file ="footer.jsp" %>

</body>
</html>