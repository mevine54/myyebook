<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
    <title>Liste de libraire - Libraire</title>
</head>
<body>
<c:import url="/WEB-INF/JSP/header.jsp" />
<main>
    <%--
        COTE ADMIN
        SERVLET: ListeLibraireServlet
    --%>
    <div class="container-fluid">
        <div class="row">
            <c:import url="/WEB-INF/JSP/menu_libraire.jsp" />
            <div class="col-8">
                <h1 class="d-flex justify-content-center my-3">Liste des libraires</h1>
                <c:if test="${param.info == 'success'}">
                    <div class="alert alert-success" role="alert">
                        Création du libraire réussie !
                    </div>
                </c:if>
                <c:if test="${param.info == 'successModif'}">
                    <div class="alert alert-success" role="alert">
                        Modification du libraire réussie !
                    </div>
                </c:if>
                <div class="d-flex justify-content-end"><a class="btn btn-outline-primary rounded-0 mb-3" href="${pageContext.request.contextPath}/libraire-enregistrer">Créer un libraire</a></div>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nom</th>
                            <th>Prénom</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${requestScope.libraires}" var="libraire">
                        <tr id="row${libraire.libId}">
                            <td>${libraire.libId}</td>
                            <td>${libraire.nom}</td>
                            <td>${libraire.prenom}</td>
                            <td>
                                <a class="btn btn-outline-primary rounded-0"
                                   href="ModifLibraire?id=<c:out value='${libraire.libId}'/>">Modifier</a>
                                <button type="button" class="btn btn-outline-danger rounded-0"
                                        hx-on:click="confirmDelete(<c:out value='${libraire.libId}'/>,
                                        'ModifLibraire?id=${libraire.libId}&csrf=${sessionScope.csrfToken}')"
                                >
                                    Supprimer
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</main>
<c:import url="/WEB-INF/JSP/footer.jsp" />
</body>
</html>
