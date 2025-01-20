<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%--<%@ page session="false" %>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description"
          content="Bienvenue à MyyeBook, votre bibliothèque locale offrant une vaste collection de livres pour tous les âges et tous les goûts. Venez découvrir notre espace convivial, participer à nos événements littéraires et profiter de nos services de prêt. Rejoignez notre communauté de lecteurs passionnés dès aujourd'hui !">
    <link rel="stylesheet" href="assets/css/bootstrap5.css">
    <link rel="stylesheet" href="assets/css/style.css">
    <title>MyyeBook - Votre Bibliothèque Locale pour Tous les Passionnés de Lecture</title>
</head>
<body class="d-flex flex-column vh-100">
<c:import url="/WEB-INF/JSP/header.jsp" />

<main>
    <%-- INSERER LE CONTENU ICI / Modifier le titre,css,js si besoin--%>
    <!-- *********** DEBUT DE CONTENU *********** -->
     <div class="container">
        <div class="d-flex justify-content-center mb-3 mt-2">
            <div class="fs-2">Créer un compte</div>
        </div>
        <form class=" mx-auto col-lg-7 mt-2"  method="POST" action="client-enregistrer">
            <div class="row mb-3">
                <div class="col-lg">
                    <label for="nom" class="form-label">Nom</label>
                    <input type="text" class="form-control" id="nom" name="nom" placeholder="Entrez votre nom" required>
                </div>
                <div class="col-lg mt-2 mt-lg-0">
                    <label for="prenom" class="form-label">Prénom</label>
                    <input type="text" class="form-control" id="prenom" name="prenom" placeholder="Entrez votre prénom" required>
                </div>
            </div>
            <div class="row mb-3">
                <div class="col-lg">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" class="form-control" id="email" name="email" placeholder="Entrez votre e-mail" required>
                </div>
            </div>
            <div class="row mb-3">
                <div class="col-12 mb-3">
                    <label for="utilisateur" class="form-label">Nom d'utilisateur</label>
                    <input type="text" class="form-control" id="utilisateur" name="utilisateur" placeholder="Entrez votre nom d'utilisateur" required>
                </div>
                <div class="col-12 col-lg-6">
                    <label for="mdp1" class="form-label">Mot de passe</label>
                    <input type="text" class="form-control" id="mdp1" name="mdp1" placeholder="Entrez votre mot de passe" required>
                </div>
                <div class="col-12 col-lg-6 mt-2 mt-lg-0">
                    <label for="mdp2" class="form-label">Confirmer votre mot de passe</label>
                    <input type="text" class="form-control" id="mdp2" name="mdp2" placeholder="Confirmez votre mot de passe" required>
                </div>
            </div>
            <div class="d-flex justify-content-center">
                <button type="submit" class="btn btn-outline-info fw-bold rounded-0 mt-lg-3 mb-3">Créer mon compte</button>
            </div>
        </form>
    </div>

    <!-- *********** FIN DE CONTENU *********** -->
</main>
<c:import url="/WEB-INF/JSP/footer.jsp" />

</body>
</html>
