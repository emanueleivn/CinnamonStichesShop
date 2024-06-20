<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.File, java.util.List, java.util.ArrayList, java.util.Collections"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="initial-scale=1,width=device-width">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/page.css">
 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/aos/2.3.4/aos.css">

<title>CinnamonStichesShop</title>
</head>
<%@include file="header.jsp"%>
<body>
 <div class="center">
        <div class="text">
            <h1>Cinnamon Stitches</h1>
            <p>Handmade with love</p>
        </div>
    </div>
     
    <section class="prodotti-shuffle">
     <%
        String imagePath = application.getRealPath("/images/products");
        File imageDir = new File(imagePath);
        List<String> imageList = new ArrayList<>();
        
        if (imageDir.exists() && imageDir.isDirectory()) {
            File[] files = imageDir.listFiles((dir, name) -> name.toLowerCase().endsWith(".jpg") || name.toLowerCase().endsWith(".png") || name.toLowerCase().endsWith(".jpeg"));
            for (File file : files) {
                imageList.add(file.getName());
            }
        }
        
        Collections.shuffle(imageList);
        List<String> selectedImages = imageList.subList(0, Math.min(5, imageList.size()));
        
        for (String imageName : selectedImages) {
    %>
    <div class="card">
    	<img src="${pageContext.request.contextPath}/images/products/<%= imageName %>" alt="Immagine Prodotto">
    	</div>
    	 <%
        }
    %>
    </section>
   <h2 id="firstlink" data-aos="fade-up"><a href="${pageContext.request.contextPath}/Catalogo">Sfoglia il catalogo...</a></h2>
 <div id="aboutproduct">
    <div class="product-feature" data-aos="fade-up">
        <img id="im1" src="${pageContext.request.contextPath}/images/indeximages/handmade.jpg" alt="Immagine1">
            <h3>Capi Artigianali Fatti a Mano</h3>
            <p>Ogni capo è creato con passione e attenzione ai dettagli, garantendo unicità e qualità superiore. La tradizione artigianale si riflette in ogni punto lavorato a mano.</p>
    </div>
    <div class="product-feature" data-aos="fade-up">
         <img id="im2" src="${pageContext.request.contextPath}/images/indeximages/made in italy.jpg" alt="Immmagine2">
            <h3>Made in Italy</h3>
            <p>I nostri prodotti sono interamente realizzati in Italia, rispettando le tradizioni locali e utilizzando materiali di alta qualità. Il nostro impegno è offrire il meglio del Made in Italy.</p>
    </div>
    <div class="product-feature l" data-aos="fade-up">
        <img id="im1" src="${pageContext.request.contextPath}/images/indeximages/uncinetto.jpg" alt="Immagine3">
            <h3>Di Grande Qualità</h3>
            <p>Utilizziamo solo filati e materiali pregiati per garantire che ogni articolo non solo sia bello, ma anche durevole e comodo da indossare.</p>
    </div>
    <div class="product-feature r" data-aos="fade-up">
         <img id="im2" src="${pageContext.request.contextPath}/images/indeximages/ecofriendly.jpg" alt="Immagine4">
            <h3>Eco-Friendly</h3>
            <p>Siamo attenti all'ambiente: tutti i nostri prodotti sono realizzati con materiali naturali e sostenibili, riducendo l'impatto ambientale e promuovendo uno stile di vita eco-friendly.</p>
    </div>
</div>

<%@include file="footer.jsp"%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/aos/2.3.4/aos.js"></script>
<script>
  AOS.init();
</script>
</body>
</html>