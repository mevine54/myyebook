<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  session="false"%>
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
    <title>Modification d'un compte Client - Libraire</title>
</head>
<%-- SERVLET: monCompteClient --%>
<body class="d-flex flex-column vh-100">
<c:import url="/WEB-INF/JSP/header.jsp" />
<main>
    <!-- *********** DEBUT DE CONTENU *********** -->
    <div class="container-fluid">
        <div class="row">
            <%-- Menu latérale pour client --%>
            <c:import url="/WEB-INF/JSP/menu_libraire.jsp" />
            <div class="col-8">
                <div class="d-flex justify-content-center mb-3 mt-2">
                    <div class="fs-2">Modification d'un compte Client</div>
                </div>
                <form class=" mx-auto col-lg-7 mt-2" method="POST" action="${pageContext.request.contextPath}/ModifClient">
                    <a href="${pageContext.request.contextPath}/ListeClient" class="btn btn-outline-primary fw-bold rounded-0 mb-3 px-3"><i class="bi bi-arrow-left-short"></i> Retour</a>
                    <c:if test="${not empty requestScope.error}">
                        <div class="alert alert-danger" role="alert">
                            Une erreur s'est produite lors de la modification du client.
                        </div>
                    </c:if>
                    <input type="hidden" name="csrf" value="<c:out value="${requestScope.csrfToken}" />">
                    <c:if test="${not empty requestScope.client}" >
                    <input type="hidden" name="clientId" value="<c:out value="${client.clientId}" />">
                    <input type="hidden" name="compteId" value="<c:out value="${client.compte.compteId}" />">
                    <div class="row mb-3">
                        <div class="col-lg">
                            <label for="nom" class="form-label">Nom</label>
                            <input type="text" class="form-control" id="nom" name="nom" value="${client.nom}" placeholder="Entrez votre nom" required>
                        </div>
                        <div class="col-lg mt-2 mt-lg-0">
                            <label for="prenom" class="form-label">Prénom</label>
                            <input type="text" class="form-control" id="prenom" name="prenom" value="${client.prenom}" placeholder="Entrez votre prénom" required>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-lg">
                            <label for="email" class="form-label">Email</label>
                            <input type="email" class="form-control" id="email" name="email" value="${client.email}" placeholder="Entrez votre e-mail" required>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-lg">
                            <label for="adresse" class="form-label">Adresse</label>
                            <input type="text" class="form-control" id="adresse" name="adresse" value="${client.adresse}" placeholder="Entrez votre adresse" required>
                        </div>
                    </div>
                    <div class="row mb-3">

                        <div class="col-lg">
                            <label for="codePostal" class="form-label">Code Postal</label>
                            <input type="text" class="form-control" id="codePostal" name="codePostal" value="${client.codePostal}" placeholder="Entrez votre code postal" required>
                        </div>
                        <div class="col-lg">
                            <label for="ville" class="form-label">Ville</label>
                            <input type="text" class="form-control" id="ville" name="ville" value="${client.ville}" placeholder="Entrez votre ville" required>
                        </div>
                    </div>

                    <div class="row mb-3">
                        <div class="col-12 mb-3">
                            <label for="login" class="form-label">Nom d'utilisateur</label>
                            <input type="text" class="form-control" id="login" name="login" value="${client.compte.login}" placeholder="Entrez votre nom d'utilisateur" required>
                        </div>
                        <div class="col-12 col-lg-6">
                            <label for="mdp1" class="form-label">Mot de passe</label>
                            <input type="password" class="form-control" id="mdp1" name="mdp1" placeholder="Entrez votre mot de passe" required>
                        </div>
                        <div class="col-12 col-lg-6 mt-2 mt-lg-0">
                            <label for="mdp2" class="form-label">Confirmer votre mot de passe</label>
                            <input type="password" class="form-control" id="mdp2" name="mdp2" placeholder="Confirmez votre mot de passe" required>
                        </div>
                    </div>
                    <div class="d-flex justify-content-center">
                        <button type="reset" class="btn btn-outline-danger fw-bold rounded-0 mt-lg-3 mb-3 me-2">Annuler</button>
                        <button type="submit" class="btn btn-outline-info fw-bold rounded-0 mt-lg-3 mb-3">Modifier</button>
                    </div>
                    </c:if>
                </form>
            </div>
        </div>
    </div>
    <!-- *********** FIN DE CONTENU *********** -->
</main>
<c:import url="/WEB-INF/JSP/footer.jsp" />
</body>
</html>
