<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  session="false"%>

<!-- Ajout de la librairie JSTL -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value="/assets/css/style.css" var="style"/>
<c:url value="/assets/css/bootstrap5.css" var="bootstrap"/>
<c:url value="/assets/css/bootstrapicons.css" var="bootstrapicons"/>
<%--<c:url value="/JSP/page/libraire/1/info-modif" var="postlibinfo"/>--%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description"
          content="Bienvenue à MyyeBook, votre bibliothèque locale offrant une vaste collection de livres pour tous les âges et tous les goûts. Venez découvrir notre espace convivial, participer à nos événements littéraires et profiter de nos services de prêt. Rejoignez notre communauté de lecteurs passionnés dès aujourd'hui !">
    <link rel="stylesheet" href="${bootstrap}">
    <link rel="stylesheet" href="${style}">
    <link rel="stylesheet" href="${bootstrapicons}">
    <title>Mon Compte Libraire - Myyebook</title>
</head>
<%-- SERVLET: LibraireModifServlet --%>
<body>
<c:import url="/WEB-INF/JSP/header.jsp" />

<main>
    <div class="container-fluid">
        <div class="row">
            <c:import url="/WEB-INF/JSP/menu_libraire.jsp" />
            <div class="col-8">
                <div class="d-flex justify-content-center">
                    <h2 class="my-3">Mon compte Libraire</h2>
                </div>

                <c:if test="${not empty erreur}">
                    <div class="alert alert-danger">
                            ${erreur}
                    </div>
                </c:if>

                <form class=" mx-auto col-7"  method= "POST" action="/monCompteLibraire">
                    <div class="row mb-3">
                        <h4>Informations</h4>
                        <div class="col">
                            <label for="nom" class="form-label">Nom</label>
                            <input type="text" class="form-control" id="nom" name="nom" required>
                        </div>
                        <div class="col">
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
                    <h4>Adresse</h4>
                    <div class="row mb-3">
                        <div class="col-12 mb-3">
                            <label for="rue" class="form-label">Rue</label>
                            <input type="text" class="form-control" id="rue" name="rue" required>
                        </div>
                        <div class="col-6">
                            <label for="codepostal" class="form-label">Code postal</label>
                            <input type="text" class="form-control" id="codepostal" name="codepostal" required>
                        </div>
                        <div class="col-6">
                            <label for="ville" class="form-label">Ville</label>
                            <input type="text" class="form-control" id="ville" name="ville" required>
                        </div>
                    </div>
                    <div class="">
                        <button type="submit" class="btn btn-primary">Modifier</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</main>
<c:import url="/WEB-INF/JSP/footer.jsp" />

</body>
</html>
