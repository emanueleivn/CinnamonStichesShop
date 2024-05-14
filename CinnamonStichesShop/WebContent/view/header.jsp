<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>CinnamonStichesShop</title>
<link rel="icon"
	href="${pageContext.request.contextPath}/images/icone/logo.ico">
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/css/header.css">
</head>

<body>
	<header>
		<div class="menu-icon">
			<a href="${pageContext.request.contextPath}/IndexServlet"> 
			<img alt="Home Page" src="${pageContext.request.contextPath}/images/icone/logo.ico" />
			</a>
		</div>
		<nav id="barra">
			<ul>
				<li><a href="${pageContext.request.contextPath}/IndexServlet"><strong>HOME</strong></a></li>
				<li><a href="${pageContext.request.contextPath}/IndexServlet"><strong>CATALOGO</strong></a></li>
			</ul>
		</nav>
		<div class="navigazione">
			<nav>
				<ul>
				<li><a href="${pageContext.request.contextPath}/LoginServlet"> 
				<img alt="Utente Login" src="${pageContext.request.contextPath}/images/icone/icona_utente.ico" />
				</a></li>
				<li><a href="${pageContext.request.contextPath}/LoginServlet">
				<img alt="Utente Carrello" src="${pageContext.request.contextPath}/images/icone/carrello.ico" />
				</a>
				</ul>
			</nav>
		</div>

	</header>

</body>
</html>