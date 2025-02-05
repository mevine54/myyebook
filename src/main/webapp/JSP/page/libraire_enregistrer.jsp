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
    <title>Créer un compte Libraire</title>
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
                <form class=" mx-auto col-7" method="POST" action="libraire-enregistrer">
                    <c:if test="${param.info == 'error'}">
                        <div class="alert alert-danger" role="alert">
                            Erreur lors de la création d'un compte libraire
                        </div>
                    </c:if>
                    <a href="${pageContext.request.contextPath}/ListeLibraire" class="btn btn-outline-primary fw-bold rounded-0 mb-3 px-3"><i class="bi bi-arrow-left-short"></i> Retour</a>
                    <input type="hidden" name="csrf" value="<c:out value="${requestScope.csrfToken}" />">
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
                            <label for="login" class="form-label">Nom du compte</label>
                            <input type="text" class="form-control" id="login" name="login" required>
                        </div>
                        <div class="col ">
                            <label for="password" class="form-label">Mot de passe</label>
                            <input type="password" class="form-control" id="password" name="password" required>
                        </div>
                    </div>
                    <div class="d-flex justify-content-center">
                        <button type="submit" class="btn btn-outline-info rounded-0">Créer un compte libraire</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    
</main>
<c:import url="/WEB-INF/JSP/footer.jsp" />
</body>
</html>
