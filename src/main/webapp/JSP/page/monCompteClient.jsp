<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- Ajout de la librairie JSTL -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value="/assets/" var="assets"/>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description"
          content="Bienvenue à MyyeBook, votre bibliothèque locale offrant une vaste collection de livres pour tous les âges et tous les goûts. Venez découvrir notre espace convivial, participer à nos événements littéraires et profiter de nos services de prêt. Rejoignez notre communauté de lecteurs passionnés dès aujourd'hui !">
    <link rel="stylesheet" href="${assets}css/bootstrap">
    <link rel="stylesheet" href="${assets}css/style">
    <title>Mon compte - MyyeBook</title>
</head>
<body>
<%@include file="../../WEB-INF/JSP/header.jsp" %>
<main>
    <div class="container">

        <div class="d-flex justify-content-center mb-3">
            <h2>Mon compte</h2>
        </div>

        <form class=" mx-auto col-7"  method="POST" action="">
            <div class="row mb-3">
                <h4>Informations personnelles</h4>
<%--                <jsp:useBean id="user" type="fr.afpa.pompey.cda22045.myyebook.servlet.MonCompteClientServlet" scope="request"/>--%>
                <div class="col">
                    <label for="nom" class="form-label">Nom</label>
                    <input type="text" class="form-control" id="nom" name="" required>
                </div>
                <div class="col">
                    <label for="prenom" class="form-label">Prénom</label>
                    <input type="text" class="form-control" id="prenom" required>
                </div>
            </div>
            <div class="row mb-3">
                <div class="col">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" class="form-control" id="email" required>
                </div>
            </div>
            <h4>Adresse</h4>
            <div class="row mb-3">
                <div class="col-12 mb-3">
                    <label for="rue" class="form-label">Rue</label>
                    <input type="text" class="form-control" id="rue" required>
                </div>
                <div class="col-6">
                    <label for="codepostal" class="form-label">Code postal</label>
                    <input type="text" class="form-control" id="codepostal" required>
                </div>
                <div class="col-6">
                    <label for="ville" class="form-label">Ville</label>
                    <input type="text" class="form-control" id="ville" required>
                </div>
            </div>
            <div class="">
                <button type="submit" class="btn btn-primary">Modifier</button>
            </div>
        </form>
    </div>
</main>
<%@include file="../../WEB-INF/JSP/footer.jsp" %>
</body>
</html>
