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
    <title>Liste de Emprunts - Libraire</title>
</head>
<body>
<c:import url="/WEB-INF/JSP/header.jsp"/>
<main>
    <%--
        COTE ADMIN
        SERVLET: ListeEmpuntsServlet
    --%>
    <div class="container-fluid">
        <div class="row">
            <c:import url="/WEB-INF/JSP/menu_libraire.jsp"/>
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
                <c:if test="${param.info == 'attente'}">
                    <div class="alert alert-success" role="alert">
                        L'emprunt a été mis en attente !
                    </div>
                </c:if>
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Client</th>
                        <th>Titre</th>
                        <th>Auteur</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${requestScope.emprunter}" var="emprunt">
                        <tr id="row<c:out value='${emprunt.id}'/>">
                            <td><c:out value="${emprunt.id}"/></td>
                            <td><c:out value="${emprunt.client.nom}"/> <c:out
                                    value="${emprunt.client.prenom}"/></td>
                            <td><c:out value="${emprunt.livre.titre}"/> <c:out
                                    value="${emprunt.livre.auteur.nom}"/></td>
                            <td><c:out value="${emprunt.livre.auteur.prenom}"/></td>
                            <td>
                                <form method="post"
                                      action="<c:out value='${pageContext.request.contextPath}/ListeEmprunts'/>">
                                    <input type="hidden" name="id" value="<c:out value='${emprunt.id}'/>">
                                    <input type="hidden" name="csrf"
                                           value="<c:out value='${requestScope.csrfToken}'/>"/>
                                        <%--                                    <c:choose>--%>
                                        <%--                                        <c:when test="${emprunt.datetimeRetour == null}">--%>
                                        <%--                                            <input type="hidden" name="info" value="valider">--%>
                                        <%--                                            <button type="submit" class="btn btn-success rounded-0">Valider l'emprunt</button>--%>
                                        <%--                                        </c:when>--%>
                                        <%--                                        <c:otherwise>--%>
                                        <%--                                            <input type="hidden" name="info" value="rendre">--%>
                                        <%--                                            <button type="submit" class="btn btn-warning rounded-0">Rendre l'emprunt</button>--%>
                                        <%--                                        </c:otherwise>--%>
                                        <%--                                    </c:choose>--%>
                                    <div class="btn-group" role="group" aria-label="Basic radio toggle button group">
                                        <input type="radio" class="btn-check" name="empruntstatut${emprunt.id}" value="attente"
                                               id="empruntattente${emprunt.id}" autocomplete="off"
                                               <c:if test="${ empty emprunt.datetimeEmprunt  && empty emprunt.datetimeRetour }">checked</c:if>>
                                        <label class="btn btn-outline-primary"
                                               for="empruntattente${emprunt.id}">Attente</label>

                                        <input type="radio" class="btn-check" name="empruntstatut${emprunt.id}" value="encours"
                                               id="empruntvalid${emprunt.id}" autocomplete="off"
                                               <c:if test="${ not empty emprunt.datetimeEmprunt  && empty emprunt.datetimeRetour }">checked</c:if>>
                                        <label class="btn btn-outline-primary" for="empruntvalid${emprunt.id}">En
                                            cours</label>

                                        <input type="radio" class="btn-check" name="empruntstatut${emprunt.id}" value="terminer"
                                               id="empruntterminer${emprunt.id}" autocomplete="off"
                                               <c:if test="${ not empty emprunt.datetimeEmprunt && not empty emprunt.datetimeRetour }">checked</c:if>
                                               <c:if test="${ empty emprunt.datetimeEmprunt  && empty emprunt.datetimeRetour }">disabled</c:if>>
                                        <label class="btn btn-outline-primary" for="empruntterminer${emprunt.id}">Terminer</label>

                                    </div>
                                    <input type="submit" class="ms-2 btn btn-outline-success rounded-0"
                                           value="Sauvegarder"/>
                                    <button type="button" class="ms-1  btn btn-outline-danger rounded-0"
                                            hx-on:click="confirmDelete(<c:out value='${emprunt.id}'/>, 'ListeEmprunts?id=${emprunt.id}&csrf=${requestScope.csrfToken}')"
                                    >
                                        Supprimer
                                    </button>
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
<c:import url="/WEB-INF/JSP/footer.jsp"/>
</body>
</html>
