<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
</head>
<body>

<form action="../LoginServlet" method="post">
<fieldset>
<legend>Login</legend>

<label>Username:</label><br>
<input type="text" name="user" placeholder="username" required="required"/><br>
<label>Password:</label><br>
<input type="password" id="password" name="pass" placeholder="password" required="required"/><br><br>
  <input type="submit" value="LOGIN"/>
 <%if(request.getAttribute("error") != null) {%>
    <p style='color: red;'>Username o password non corretti!</p>
 <% } %>
 <p><a href="Registrazione.jsp">Non sei registrato? Registrati ora.</a>
 </fieldset>
</form>
</body>
</html>