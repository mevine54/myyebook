<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value="/assets/css/bootstrap5.css" var="bootstrap"/>
<c:url value="/assets/css/style.css" var="style"/>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description"
          content="Bienvenue à MyyeBook, votre bibliothèque locale offrant une vaste collection de livres pour tous les âges et tous les goûts. Venez découvrir notre espace convivial, participer à nos événements littéraires et profiter de nos services de prêt. Rejoignez notre communauté de lecteurs passionnés dès aujourd'hui !">
    <link rel="stylesheet" href="${bootstrap}">
    <link rel="stylesheet" href="${style}">
    <title>Créer un compte Libraire - Admin</title>
</head>
<%--
    CÔTE ADMIN
    SERVLET: LibraireEnregistrerServlet
--%>
<body class="d-flex flex-column vh-100">
<c:import url="/WEB-INF/JSP/header.jsp" />
<main>
    <div class="container-fluid">
        <div class="row">
            <c:import url="/WEB-INF/JSP/menu_libraire.jsp" />
            <div class="col-8">
                <div class="d-flex justify-content-center my-3">
                    <h2>Créer un compte <strong>libraire</strong> </h2>
                </div>

                <form class=" mx-auto col-7" method="POST" action="/libraire-enregistrer">
                    <a href="${pageContext.request.contextPath}/ListeLibraire" class="btn btn-outline-primary fw-bold rounded-0 mb-3 px-3"><i class="bi bi-arrow-left-short"></i> Retour</a>
                    <input type="hidden" name="csrfToken" value="<c:out value="${sessionScope.csrfToken}" />">
                    <h3>Informations</h3>
                    <div class="row mb-3">
                        <div class="col">
                            <label for="nom" class="form-label">Nom</label>
                            <input type="text" class="form-control" id="nom" name="nom" required>
                        </div>
                        <div class="col ">
                            <label for="prenom" class="form-label">Prénom</label>
                            <input type="text" class="form-control" id="prenom" name="prenom" required>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="col">
                            <label for="email" class="form-label">Email</label>
                            <input type="email" class="form-control" id="email" name="email" required>
                        </div>
                    </div>
                    <h3>Adresse</h3>
                    <div class="row mb-3">
                        <div class="col-12 mb-3">
                            <label for="rue" class="form-label">Rue</label>
                            <input type="text" class="form-control" id="rue" name="rue" required>
                        </div>
                        <div class="col-6">
                            <label for="cp" class="form-label">Code postal</label>
                            <input type="text" class="form-control" id="cp" name="cp" required>
                        </div>
                        <div class="col-6">
                            <label for="ville" class="form-label">Ville</label>
                            <input type="text" class="form-control" id="ville" name="ville" required>
                        </div>
                    </div>
                    <div class="d-flex justify-content-center">
                        <button type="submit" class="btn btn-outline-info rounded-0">Modifier</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    
</main>
<c:import url="/WEB-INF/JSP/footer.jsp" />
</body>
</html>
