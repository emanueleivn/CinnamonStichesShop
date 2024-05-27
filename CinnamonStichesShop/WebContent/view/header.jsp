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
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
</head>
<body >
    <div class="navbar">
        <div class="logo-container">
            <a href="${pageContext.request.contextPath}/IndexServlet">
                <img src="${pageContext.request.contextPath}/images/icone/logo.ico" alt="Logo">
            </a>
            <div class="logo-text"> <a href="${pageContext.request.contextPath}/IndexServlet">Cinnamon Stitches</a></div>
        </div>
        <ul id="nav-list">
        	<li class="menu-item"><a href="${pageContext.request.contextPath}/IndexServlet">Home</a></li>
            <li class="menu-item"><a href="${pageContext.request.contextPath}/LoginServlet">
                Profilo</a></li>
            <li class="menu-item"><a href="${pageContext.request.contextPath}/Carrello">
            Carrello</a>
            </li>
            <li class="menu-item"><a href="${pageContext.request.contextPath}/LoginServlet">
            Preferiti</a>
            </li>
            <li class="menu-item"><a href="${pageContext.request.contextPath}/AboutServlet">Categoria</a></li>
            <li class="menu-item"><a href="${pageContext.request.contextPath}/AboutServlet">About Us</a></li>
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
</body>
</html>


