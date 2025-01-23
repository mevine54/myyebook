<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value="/assets/css/bootstrap5.css" var="bootstrap"/>
<c:url value="/assets/css/style.css" var="style"/>
<c:url value="/assets/css/bootstrapicons.css" var="bootstrapicons"/>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description"
          content="Bienvenue à MyyeBook, votre bibliothèque locale offrant une vaste collection de livres pour tous les âges et tous les goûts. Venez découvrir notre espace convivial, participer à nos événements littéraires et profiter de nos services de prêt. Rejoignez notre communauté de lecteurs passionnés dès aujourd'hui !">
    <link rel="stylesheet" href="${bootstrap}">
    <link rel="stylesheet" href="${style}">
    <link rel="stylesheet" href="${bootstrapicons}">
    <title>Crée un auteur - Libraire</title>
</head>
<%-- SERVLET: LivreModificationServlet --%>
<body class="d-flex flex-column vh-100">
<c:import url="/WEB-INF/JSP/header.jsp" />
<main>
    <!-- *********** DEBUT DE CONTENU *********** -->
    <div class="container-fluid" >
        <div class="row">
            <!-- Menu latérale pour client -->
            <c:import url="/WEB-INF/JSP/menu_libraire.jsp" />
            <div class="col-8">
                <div class="d-flex justify-content-center my-3">
                    <h2>Crée un auteur</h2>
                </div>
                <form class="mx-auto col-lg-7" method="POST" action="CreeUnAuteur">
                    <a href="${pageContext.request.contextPath}/ListeAuteur" class="btn btn-outline-primary fw-bold rounded-0 mb-3 px-3"><i class="bi bi-arrow-left-short"></i> Retour</a>
                    <div class="row mb-3">
                        <div class="col">
                            <label for="nom" class="form-label">Nom de l'auteur</label>
                            <input type="text" class="form-control" id="nom" name="nom" required>
                        </div>
                        <div class="col">
                            <label for="prenom" class="form-label">Prénom de l'auteur</label>
                            <input type="text" class="form-control" id="prenom" name="prenom" required>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="col">
                            <label for="formFile" class="form-label">Photo de l'auteur</label>
                            <input class="form-control" type="file" id="formFile" name="image">
                        </div>
                    </div>
                    <div class="d-flex justify-content-center mt-3">
                        <button type="submit" class="btn btn-outline-info fw-bold rounded-0 mx-3 px-3">Valider</button>
                        <button type="reset" class="btn btn-outline-secondary fw-bold rounded-0 mx-3 px-3">Annuler</button>
                    </div>
                </form>
            </div>
        </div>

    </div>


</main>
<c:import url="/WEB-INF/JSP/footer.jsp" />
</body>
</html>
