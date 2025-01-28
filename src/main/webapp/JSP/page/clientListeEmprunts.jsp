<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  session="true"%>
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
    <title>Mes Emprunts - MyyeBook</title>
</head>
<%-- SERVLET: ClientListeEmprunts --%>
<body class="d-flex flex-column vh-100">
<c:import url="/WEB-INF/JSP/header.jsp" />
<main>
    <div class="container-fluid">
        <div class="row">
            <!-- Menu latérale pour client -->
            <c:import url="/WEB-INF/JSP/menu_client.jsp" />
            <div class="col-8">
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
        </div>
    </div>
</main>
<c:import url="/WEB-INF/JSP/footer.jsp" />

</body>
</html>
