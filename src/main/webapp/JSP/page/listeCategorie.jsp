<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
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
    <title>Liste des catégories - Libraire</title>
</head>
<body>
<%@include file="/WEB-INF/JSP/header.jsp" %>
<main>
    <%--
        COTE ADMIN
        SERVLET: ListeLibraireServlet
    --%>
    <div class="container-fluid">
        <div class="row">
            <c:import url="/WEB-INF/JSP/menu_libraire.jsp" />
            <div class="col-8">
                <h1 class="d-flex justify-content-center my-3">Liste des catégories</h1>
                <c:if test="${param.info == 'success'}">
                    <div class="alert alert-success text-center" role="alert">
                        <i class="bi bi-info-circle-fill"></i> Création de catégorie réussie !
                    </div>
                </c:if>
                <c:if test="${param.info == 'successUpdate'}">
                    <div class="alert alert-success text-center" role="alert">
                        <i class="bi bi-info-circle-fill"></i> Modification de catégorie réussie !
                    </div>
                </c:if>
                <c:if test="${param.info == 'errorRecupDB'}">
                    <div class="alert alert-warning text-center" role="alert">
                        <i class="bi bi-info-circle-fill"></i> Impossible de récupérer les données !
                    </div>
                </c:if>
                <c:if test="${param.info == 'errorDB'}">
                    <div class="alert alert-danger text-center" role="alert">
                        <i class="bi bi-info circle-fill"></i> Erreur d'enregistrement dans la base de données !
                    </div>
                </c:if>
                <c:if test="${param.info == 'successDelete'}">
                    <div class="alert alert-success text-center" role="alert">
                        <i class="bi bi-info circle-fill"></i> Suppression de catégorie réussie !
                    </div>
                </c:if>
                <div class="d-flex justify-content-end"><a class="btn btn-outline-primary rounded-0" href="${pageContext.request.contextPath}/CreeUneCategorie">Créer un catégorie</a></div>
                <table class="table table-bordered mt-5">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nom de catégorie</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="categorie" items="${requestScope.categories}">
<%--                        mettre id dans l'ordre--%>

                        <tr id="row<c:out value='${categorie.id}'/>" >
                            <td>${categorie.id}</td>
                            <td>${categorie.nom}</td>
                            <td class="d-flex">
                                <a class="btn btn-outline-primary rounded-0"
                                   href="ModifCategorie?id=<c:out value='${categorie.id}'/>">Modifier</a>
                                <button type="button" class="ms-xxl-2  btn btn-outline-danger rounded-0"
                                        hx-on:click="confirmDelete(<c:out value='${categorie.id}'/>, 'ModifCategorie?id=${categorie.id}&csrf=${requestScope.csrfToken}')"
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
<%@include file="/WEB-INF/JSP/footer.jsp" %>
</body>
</html>
