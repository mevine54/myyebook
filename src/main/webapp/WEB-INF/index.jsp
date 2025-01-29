<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true"%>
<!-- Ajout de la librairie JSTL -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="/assets/images/img.png" var="couv_livre"/>
<c:url value="/assets/css/bootstrap5.css" var="bootstrap"/>
<c:url value="/assets/css/style.css" var="style"/>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description"
          content="Bienvenue à MyyeBook, votre bibliothèque locale offrant une vaste collection de livres pour tous
          les âges et tous les goûts. Venez découvrir notre espace convivial, participer à nos événements littéraires
          et profiter de nos services de prêt. Rejoignez notre communauté de lecteurs passionnés dès aujourd'hui !">
    <link rel="stylesheet" href="${bootstrap}">
    <link rel="stylesheet" href="${style}">
    <title>MyyeBook - Votre Bibliothèque Locale pour Tous les Passionnés de Lecture</title>
</head>
<body class="d-flex flex-column vh-100 justify-content-between">
<c:import url="/WEB-INF/JSP/header.jsp" />

<main>

    <!-- *********** DEBUT DE CONTENU *********** -->
    <div class="container-fluid container-lg"  id="search-results">
        <h2 class="fs-3 my-3">Nos sélections</h2>
        <div class="row">
            <c:forEach var="livreEnAvant" items="${requestScope.livresEnavant}">
                <div class="col-12 col-md-6 col-lg-3 mb-4 px-5 px-sm-3 px-lg-2">
                    <a href="<c:url value='/livre?id=${livreEnAvant.id}'/>"
                       class="link-underline link-underline-opacity-0 link-underline-opacity-0-hover shadow-lg rounded">
                        <div class="card h-100">
                            <img src="<c:url value="/assets/upload/couverture/${livreEnAvant.image}"/>"  class="card-img-top" alt="<c:out value="${livreEnAvant.titre}"/>">
                            <div class="card-body d-flex align-items-start justify-content-around flex-column">
                                <h4 class="card-title"><c:out value="${livreEnAvant.titre}"/></h4>
                                <p class="card-text d-none d-lg-block"><small class="text-body-secondary"><c:out
                                        value="${livreEnAvant.auteur.prenom}"/> <c:out
                                        value="${livreEnAvant.auteur.nom}"/></small></p>
                            </div>
                        </div>
                    </a>
                </div>
            </c:forEach>
            <h2 class="fs-3 my-3">Nos livres</h2>
            <div class="mx-3">
                <c:forEach var="livre" items="${requestScope.livres}">

                    <a href="<c:url value='/livre?id=${livre.id}'/>" class="link-underline link-underline-opacity-0 link-underline-opacity-0-hover">
                        <div class="card mb-3 shadow rounded">
                            <div class="row g-0">
                                <div class="col-lg-2 col-5 h-25">
                                    <img src="<c:url value="/assets/upload/couverture/${livre.image}"/>"  class="img-fluid rounded-start"
                                         alt="<c:out value='${livre.titre}'/>">
                                </div>
                                <div class="col-lg col-5">
                                    <div class="card-body">
                                        <h4 class="card-title"><c:out value="${livre.titre}"/></h4>
                                        <p class="card-text d-none d-lg-block"><c:out value="${livre.resume}"/></p>
                                        <p class="card-text d-block d-lg-none text-truncate"><c:out
                                                value="${livre.resume}"/>.</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </a>
                </c:forEach>
            </div>
        </div>
    </div>
    <!-- *********** FIN DE CONTENU *********** -->
</main>
<c:import url="/WEB-INF/JSP/footer.jsp"/>
<%--<script src="<c:url value='/assets/js/bootstrap5.js'/>"></script>--%>
<%--<script src="<c:url value='/assets/js/htmx.js'/>"></script>--%>
<%--<script src="<c:url value='/assets/js/script.js'/>"></script>--%>
</body>
</html>
