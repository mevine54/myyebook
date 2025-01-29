<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  session="true"%>

<!-- Ajout de la librairie JSTL -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value="/assets/css/bootstrap5.css" var="bootstrap"/>
<c:url value="/assets/css/style.css" var="style"/>
<%--<c:url value="/accueil" var="accueilUrl"/>--%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description"
          content="Bienvenue à MyyeBook, votre bibliothèque locale offrant une vaste collection de livres pour tous les âges et tous les goûts. Venez découvrir notre espace convivial, participer à nos événements littéraires et profiter de nos services de prêt. Rejoignez notre communauté de lecteurs passionnés dès aujourd'hui !">
    <link rel="stylesheet" href="${bootstrap}">
    <link rel="stylesheet" href="${style}">
    <title>Livre :  <c:out value="${requestScope.livre.titre}"/> - MyyeBook</title>
</head>
<%-- SERVLET: LivreDescriptionServlet --%>
<body class="d-flex flex-column vh-100">
<c:import url="/WEB-INF/JSP/header.jsp" />
<main>
    <div id="search-results">

        <div class="container">
            <div class="row my-5">
                <div class="col-lg-5">
                    <img src="<c:url value="/assets/upload/couverture/${requestScope.livre.image}"/>" alt="<c:out value="${requestScope.livre.titre}"/>" class="img-fluid">
                </div>
                <div class="col-7 d-flex flex-column">
                    <div class="fs-3 fw-bold mt-2">
                        <c:out value="${requestScope.livre.titre}"/>
                    </div>
                    <div class="fs-5">
                        <c:out value="${requestScope.livre.auteur.prenom}"/> <c:out
                            value="${requestScope.livre.auteur.nom}"/>
                    </div>
                    <div class="">
                        <div class="mb-lg-auto">
                            <p>
                                <c:out value="${requestScope.livre.resume}"/>
                            </p>
                        </div>
                        <div class="">
                            <div class="d-flex">
                                <button type="button" class="btn btn-outline-info fw-bold rounded-0">Emprunter ce livre
                                </button>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <!--  ************ PARTIE : LES LIVRES SIMILAIRES ************ -->
        <div class="container bg-secondary-subtle my-3 py-1">
            <div class="fs-3 fw-bold">Les livres similaires</div>
            <div class="d-flex overflow-x-auto">
                <c:forEach var="livreSimilaire" items="${requestScope.livreSimilaireList}">
                    <a href="<c:url value='/livre?id=${livreSimilaire.id}'/>">
                        <div class="my-2 mx-2">
                            <div class="card" style="width: 150px;">
                                <img src="<c:url value="/assets/upload/couverture/${livreSimilaire.image}"/>" class="card-img-top"
                                     alt="<c:out value="${livreSimilaire.titre}"/>">
                            </div>
                        </div>
                    </a>
                </c:forEach>
                <div class="my-2 mx-2">
                    <div class="card bg-white" style="width: 150px;">
                        <img src="<c:url value="/assets/upload/couverture/${requestScope.livre.image}"/>" class="card-img-top opacity-25" alt="...">
                        <div class="card-img-overlay d-flex justify-content-center align-items-center">
                            <h5 class="card-title text-center text-dark fw-bold">Voir plus</h5>
                        </div>
                    </div>
                </div>

            </div>
        </div>

    </div>
    <!-- *********** FIN DE CONTENU *********** -->
</main>
<c:import url="/WEB-INF/JSP/footer.jsp"/>
<script src="<c:url value='/assets/js/htmx.js'/>"></script>
<script src="<c:url value='/assets/js/script.js'/>"></script>
</body>
</html>