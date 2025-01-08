<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
<body>
<%@include file="../../WEB-INF/JSP/header.jsp" %>
<main>
    <%-- INSERER LE CONTENU ICI / Modifier le titre,css,js si besoin--%>

    <div class="container">
        <div class="d-flex justify-content-center mb-3">
            <h2>Créer un compte</h2>
        </div>
        <form class=" mx-auto col-7" method="POST" action="client-enregistrer">
            <div class="row mb-3">
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
            <div class="row mb-3">
                <div class="col-12 mb-3">
                    <label for="utilisateur" class="form-label">Utilisateur</label>
                    <input type="text" class="form-control" id="utilisateur" name="utilisateur" required>
                </div>
                <div class="col-6">
                    <label for="mdp1" class="form-label">Mot de passe</label>
                    <input type="password" class="form-control" id="mdp1" name="mdp1" required>
                </div>
                <div class="col-6">
                    <label for="mdp2" class="form-label">Confirmer votre mot de passe</label>
                    <input type="password" class="form-control" id="mdp2" name="mdp2" required>
                </div>
            </div>
            <div class="d-flex justify-content-center">
                <button type="submit" class="btn btn-primary">Créer mon compte</button>
            </div>
        </form>
    </div>
</main>
<%@include file="../../WEB-INF/JSP/footer.jsp" %>
</body>
</html>
