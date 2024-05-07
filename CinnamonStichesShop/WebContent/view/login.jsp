<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Page</title>
</head>
<%@include file ="header.jsp" %>
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
 <p><a href="./view/registrazione.jsp">Non sei registrato? Registrati ora.</a>
 
</fieldset>
 </form>
<br><br>

</body>
<%@include file ="footer.jsp" %>
</html>