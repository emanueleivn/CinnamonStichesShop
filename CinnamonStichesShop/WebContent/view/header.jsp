<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CinnamonStichesShop</title>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;700&display=swap" rel="stylesheet">
    <link rel="icon" href="${pageContext.request.contextPath}/images/icone/logo.ico">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/styles/header.css">
</head>
<body>
    <div class="navbar">
        <div class="logo-container">
            <a href="${pageContext.request.contextPath}/HomePage">
                <img src="${pageContext.request.contextPath}/images/icone/logo.ico" alt="Logo">
            </a>
            <div class="logo-text"> <a href="${pageContext.request.contextPath}/HomePage">Cinnamon Stitches</a></div>
        </div>
        <ul id="nav-list">
            <li class="menu-item"><a href="${pageContext.request.contextPath}/HomePage">Home</a></li>
            <li class="menu-item">
                <% if (session.getAttribute("isLogged") == Boolean.FALSE) { %>
                    <a href="<%= request.getContextPath() %>/Login">Accedi</a>
                <% } else { %>
                    <a href="${pageContext.request.contextPath}/Account">Profilo</a>
                <% } %>
            </li>
            <li class="menu-item"><a href="${pageContext.request.contextPath}/Carrello">Carrello</a></li>
            <li class="menu-item"><a href="${pageContext.request.contextPath}/Preferiti">Preferiti</a></li>
            <li class="menu-item"><a href="${pageContext.request.contextPath}/Catalogo">Catalogo</a></li>
            <li class="menu-item">
            <% if ((session.getAttribute("isLogged") == Boolean.TRUE) && (session.getAttribute("isAdmin") == Boolean.TRUE)){%>
                    <a href="${pageContext.request.contextPath}/admin/PaginaAmministratore">Manage</a>
            <% }else{ %>
            <a href="${pageContext.request.contextPath}/AboutUs">About</a></li>
            <% } %>
        </ul>
        <div class="menu-icon" onclick="toggleMenu()">
            <div></div>
            <div></div>
            <div></div>
        </div>
    </div>
</body>
<script>
    function toggleMenu() {
        const navList = document.getElementById('nav-list');
        navList.classList.toggle('collapsed');
    }
</script>
</html>


