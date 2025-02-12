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
    <title>Liste des livres empruntés - Myyebook</title>
</head>
<%-- SERVLET: ListeLivreEmprunter --%>
<body class="d-flex flex-column vh-100">
<c:import url="/WEB-INF/JSP/header.jsp" />
<main>
    <%-- INSERER LE CONTENU ICI / Modifier le titre,css,js si besoin--%>
    <div class="container-fluid">
        <div class="row">
            <c:import url="/WEB-INF/JSP/menu_libraire.jsp" />
            <div class="col-8">
                <h1 class="d-flex justify-content-center my-3">Liste des Livres emprunté</h1>
                <table class="table table-bordered mt-5">
                    <thead>
                    <tr>
                        <th>Client</th>
                        <th>Livre</th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>

                    <tr>
                        <td>Jean Dupont</td>
                        <td>
                            <a href="">
                                Star Wars
                                George Lucas
                            </a>
                        </td>

                        <td>Demande d'emprunt</td>
                        <td>
                            <a class="btn btn-outline-primary rounded-0">Valide l'emprunt</a>
                            <a class="btn btn-outline-danger rounded-0">Annule l'emprunt</a>
                        </td>

                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</main>
<c:import url="/WEB-INF/JSP/footer.jsp" />
</body>
</html>
