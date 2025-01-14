<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description"
          content="Bienvenue à MyyeBook, votre bibliothèque locale offrant une vaste collection de livres pour tous les âges et tous les goûts. Venez découvrir notre espace convivial, participer à nos événements littéraires et profiter de nos services de prêt. Rejoignez notre communauté de lecteurs passionnés dès aujourd'hui !">
    <link rel="stylesheet" href="assets/css/bootstrap5.css">
    <link rel="stylesheet" href="assets/css/style.css">
    <title>Modification du livre</title>
</head>
<body class="d-flex flex-column vh-100">
<%@include file="../../WEB-INF/JSP/header.jsp" %>
<main>
    <%-- INSERER LE CONTENU ICI / Modifier le titre,css,js si besoin--%>
    <h1>Liste des Livres emprunté</h1>


    <table class="table table-bordered h-100 mt-5">
        <thead>
        <tr>
            <th>Titre du livre</th>
            <th>Auteur</th>
            <th>Status</th>
        </tr>
        </thead>
        <tbody>

        <tr>
            <td>1</td>
            <td>2</td>
            <td>3</td>

        </tr>
        <tr>
            <td>1</td>
            <td>2</td>
            <td>3</td>

        </tr>
        <tr>
            <td>1</td>
            <td>2</td>
            <td>3</td>

        </tr>
        <tr>
            <td>1</td>
            <td>2</td>
            <td>3</td>

        </tr>
        <tr>
            <td>1</td>
            <td>2</td>
            <td>3</td>

        </tr>
        <tr>
            <td>1</td>
            <td>2</td>
            <td>3</td>

        </tr>
        </tbody>
    </table>

</main>
<%@include file="../../WEB-INF/JSP/footer.jsp" %>
</body>
</html>
