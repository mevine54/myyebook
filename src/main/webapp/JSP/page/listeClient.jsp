<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  session="false" %>
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
    <title>Liste de clients - Libraire</title>
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
            <c:import url="/WEB-INF/JSP/menu_libraire.jsp"/>
            <div class="col-8">
                <h1 class="d-flex justify-content-center my-3">Liste des clients</h1>
                <c:if test="${param.info == 'success'}">
                    <div class="alert alert-success" role="alert">
                        La modification a été pris en compte avec succès.
                    </div>
                </c:if>
                <table class="table table-bordered mt-5">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Clients</th>
                        <th>Email</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="client" items="${requestScope.clients}">
                        <tr id="row${client.clientId}">
                            <td><c:out value="${client.clientId}"/></td>
                            <td><c:out value="${client.nom}"/></td>
                            <td><c:out value="${client.email}"/></td>
                            <td>
                                <a class="btn btn-outline-primary rounded-0"
                                   href="${pageContext.request.contextPath}/ModifClient?id=${client.clientId}">Modifier</a>
                                <button type="button" class="btn btn-outline-danger rounded-0"
                                        hx-on:click="confirmDelete(<c:out value='${client.clientId}'/>,
                                        'ModifClient?id=${client.clientId}&csrf=${requestScope.csrfToken}')">
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
