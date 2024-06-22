<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/page.css">
    <script src="${pageContext.request.contextPath}/scripts/validate.js"></script>
</head>
<%@include file="header.jsp" %>
<body>
    <div id="log-reg">
        <h4>Login</h4>
        <div id="login">
        <form action="${pageContext.request.contextPath}/Login" method="POST" onsubmit="return validate()">
            <label>Username</label>
            <input class="inp" type="text" id="username" name="user" placeholder="username" required 
            pattern="[A-Za-z0-9]+" title="Caratteri consentiti A-Z,a-z,0-9" onblur="textValidate(this)"/>
            <span id="username-error" class="error-message"></span>
            <label>Password</label>
            <input class="inp" type="password" id="password" name="password" placeholder="password" required /> 
            <input class="sub" type="submit" value="LOGIN">
        </form>
        </div>
        <p>
            <a id="anchor" href="${pageContext.request.contextPath}/view/registrazione.jsp">Non sei registrato? Registrati ora.</a>
        </p>
    </div>
    <%@include file="footer.jsp" %>
</body>
</html>
