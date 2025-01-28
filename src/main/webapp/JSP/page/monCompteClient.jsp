<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  session="true"%>
<!-- Ajout de la librairie JSTL -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value="/assets/css/bootstrap5.css" var="bootstrap"/>
<c:url value="/assets/css/bootstrapicons.css" var="bootstrapicons"/>
<c:url value="/assets/css/style.css" var="style"/>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description"
          content="Bienvenue à MyyeBook, votre bibliothèque locale offrant une vaste collection de livres pour tous les âges et tous les goûts. Venez découvrir notre espace convivial, participer à nos événements littéraires et profiter de nos services de prêt. Rejoignez notre communauté de lecteurs passionnés dès aujourd'hui !">
    <link rel="stylesheet" href="${bootstrap}">
    <link rel="stylesheet" href="${style}">
    <link rel="stylesheet" href="${bootstrapicons}">
    <title>Mon compte - Client</title>
</head>
<%-- SERVLET: monCompteClient --%>
<body class="d-flex flex-column vh-100">
<c:import url="/WEB-INF/JSP/header.jsp" />
<main>
    <!-- *********** DEBUT DE CONTENU *********** -->
    <div class="container-fluid">
        <div class="row">
            <%-- Menu latérale pour client --%>
            <c:import url="/WEB-INF/JSP/menu_client.jsp" />
            <div class="col-8">
                <div class="d-flex justify-content-center my-3">
                    <h2>Mon compte</h2>
                </div>

                <form class="mx-auto col-lg-7"  method="POST" action="monCompteClient">
                    <div class="row">
                        <h4>Informations personnelles</h4>
                        <div class="col-lg mt-2 mt-lg-0">
                            <label for="nom" class="form-label">Nom</label>
                            <input type="text" class="form-control" id="nom" name="nom" required>
                        </div>
                        <div class="col-lg mt-2 mt-lg-0">
                            <label for="prenom" class="form-label">Prénom</label>
                            <input type="text" class="form-control" id="prenom" name="prenom" required>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-lg mt-2">
                            <label for="email" class="form-label">Email</label>
                            <input type="email" class="form-control" id="email" name="email" required>
                        </div>
                    </div>
                    <h4>Adresse</h4>
                    <div class="row">
                        <div class="col-12 mb-lg-3">
                            <label for="rue" class="form-label">Rue</label>
                            <input type="text" class="form-control" id="rue" name="rue" required>
                        </div>
                        <div class="col-lg-6 mt-2 mt-lg-0">
                            <label for="codepostal" class="form-label">Code postal</label>
                            <input type="text" class="form-control" id="codepostal" name="codepostal" required>
                        </div>
                        <div class="col-lg-6 mt-2 mt-lg-0">
                            <label for="ville" class="form-label">Ville</label>
                            <input type="text" class="form-control" id="ville" name="ville" required>
                        </div>
                    </div>
                    <div class=" d-flex justify-content-center">
                        <button type="submit" class="btn btn-outline-info fw-bold rounded-0 mt-lg-3 mb-3 mt-3">Modifier</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!-- *********** FIN DE CONTENU *********** -->
</main>
<c:import url="/WEB-INF/JSP/footer.jsp" />
</body>
</html>
