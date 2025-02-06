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
    <title>Liste de Emprunts - Libraire</title>
</head>
<body>
<c:import url="/WEB-INF/JSP/header.jsp" />
<main>
    <%--
        COTE ADMIN
        SERVLET: ListeEmpuntsServlet
    --%>
    <div class="container-fluid">
        <div class="row">
            <c:import url="/WEB-INF/JSP/menu_libraire.jsp" />
            <div class="col-8">
                <h1 class="d-flex justify-content-center my-3">Liste des Emprunts</h1>
                <c:if test="${param.info == 'valider'}">
                    <div class="alert alert-success" role="alert">
                        La validation de l'emprunt a été effectuée avec succès !
                    </div>
                </c:if>
                <c:if test="${param.info == 'rendre'}">
                    <div class="alert alert-success" role="alert">
                        Le retour de l'emprunt a été effectué avec succès !
                    </div>
                </c:if>
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Client</th>
                        <th>Livres</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${requestScope.emprunter}" var="emprunter">
                        <tr id="row${emprunter.id}">
                            <td>${emprunter.id}</td>
                            <td>${emprunter.client.nom} ${emprunter.client.prenom}</td>
                            <td>${emprunter.exemplaire.livre.titre} ${emprunter.exemplaire.livre.auteur.nom} ${emprunter.exemplaire.livre.auteur.prenom}</td>
                            <td>
                                <form method="post" action="${pageContext.request.contextPath}/ListeEmprunts" >
                                    <input type="hidden" name="id" value="${emprunter.id}">
                                    <input type="hidden" name="csrf" value="<c:out value='${requestScope.csrfToken}'/>"/>
                                    <c:choose>
                                        <c:when test="${emprunter.datetimeRetour == null}">
                                            <input type="hidden" name="info" value="valider">
                                            <button type="submit" class="btn btn-success rounded-0">Valider l'emprunt</button>
                                        </c:when>
                                        <c:otherwise>
                                            <input type="hidden" name="info" value="rendre">
                                            <button type="submit" class="btn btn-warning rounded-0">Rendre l'emprunt</button>
                                        </c:otherwise>
                                    </c:choose>
                                </form>
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
