<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description"
          content="Bienvenue à MyyeBook, votre bibliothèque locale offrant une vaste collection de livres pour tous les âges et tous les goûts. Venez découvrir notre espace convivial, participer à nos événements littéraires et profiter de nos services de prêt. Rejoignez notre communauté de lecteurs passionnés dès aujourd'hui !">
    <link rel="stylesheet" href="assets/css/bootstrap5.css">
    <link rel="stylesheet" href="assets/css/style.css">
    <title>Mes Emprunts - MyyeBook</title>
</head>
<body class="d-flex flex-column vh-100">
<%@include file="../../WEB-INF/JSP/header.jsp" %>
<main>
    <div class="container">
        <div class="text-center mb-3">
            <h3 class="mt-3">Mes Empruntes</h3>
        </div>
        <div>

            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Livre</th>
                    <th>Auteur</th>
                    <th>Statut</th>
                </tr>
                </thead>
                <tbody>
                <%-- TODO: Récupere les infos du controllers et les afficher --%>
                <tr>
                    <td>1</td>
                    <td>Le Petit Prince</td>
                    <td>Antoine de Saint-Exupéry</td>
                    <%-- TODO: le client a la possibilité d'annuler la demande d'emprunts--%>
                    <td class="text-info fw-bold">Demande d'emprunts en cours</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</main>
<%@include file="../../WEB-INF/JSP/footer.jsp" %>
</body>
</html>
