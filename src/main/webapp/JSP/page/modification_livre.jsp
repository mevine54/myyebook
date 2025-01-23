<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value="/assets/css/bootstrap5.css" var="bootstrap"/>
<c:url value="/assets/css/style.css" var="style"/>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description"
          content="Bienvenue à MyyeBook, votre bibliothèque locale offrant une vaste collection de livres pour tous les âges et tous les goûts. Venez découvrir notre espace convivial, participer à nos événements littéraires et profiter de nos services de prêt. Rejoignez notre communauté de lecteurs passionnés dès aujourd'hui !">
    <link rel="stylesheet" href="${bootstrap}">
    <link rel="stylesheet" href="${style}">
    <title>Modification du livre</title>
</head>
<body class="d-flex flex-column vh-100">
<c:import url="/WEB-INF/JSP/header.jsp"/>
<main>
    <%-- INSERER LE CONTENU ICI / Modifier le titre,css,js si besoin--%>
    <div class="container mt-5 h-90 d-flex flex-column flex-justify-center">
        <div class="d-flex justify-content-center mb-3">
            <h2>Modification du livre </h2>
        </div>
        <form class="h-100 mx-auto col-7 d-flex flex-column justify-content-between" enctype="multipart/form-data" method="POST"
              action="LivreModification">
            <div class="row mb-3">
                <div class="col">
                    <label for="nom" class="form-label">Nom du livre</label>
                    <input type="text" class="form-control" id="nom" name="nom"  value="<c:out value="${livre.titre}"/>" required>
                </div>
                <div class="col">
                    <label for="categorie" class="form-label">Auteur</label>
                    <select class="form-select" id="auteur" name="auteur" required>
                        <option value="" selected disabled>Veuillez selectionez une option</option>
                        <c:forEach var="auteur" items="${requestScope.auteurList}">
<%--                            <c:choose>--%>
<%--                                <c:when test="${auteur.auteurId} ==${livre.auteur.auteurId} ">--%>
<%--                                    <option selected value="<c:out value="${auteur.auteurId}"/>"><c:out value="${auteur.prenom}"/> <c:out value="${auteur.nom}" /></option>--%>
<%--                                </c:when>--%>
<%--                                <c:otherwise>--%>
                                    <option value="<c:out value="${auteur.auteurId}"/>"><c:out value="${auteur.prenom}"/> <c:out value="${auteur.nom}"/></option>

<%--                                </c:otherwise>--%>
<%--                            </c:choose>--%>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="row mb-3">
                <div class="col">
                    <label for="categorie" class="form-label">Categories</label>
                    <select class="form-select" id="categorie" name="categorie" required>
                        <option value="" selected disabled>Veuillez selectionez une option</option>
                        <c:forEach var="categorie" items="${requestScope.categorieList}">
                            <option value="<c:out value="${categorie.id}"/>"><c:out value="${categorie.nom}"/></option>
                        </c:forEach>
                    </select>
                </div>
                <div class="mt-3 input-group mb-3">
                    <label class="input-group-text" for="img">Choisissez une nouvelle couverture</label>
                    <input type="file" id="img" name="img" class="form-control bi-building-fill-exclamation"
                           accept="image/png, image/jpeg" required>
                </div>
            </div>
            <%--            <h3>Resumé</h3>--%>
            <div class="row mb-3">
                <div class="col-12 mb-3">
                    <label for="resume" class="form-label">Resume</label>
                    <textarea class="form-control" style="height: 150px" id="resume" name="resume"
                              placeholder="Ecrirvez ici..."><c:out value="${livre.resume}"/></textarea>
                    <%--                    <input class="h-50" type="text" class="form-control" id="resume" name="resume" required>--%>
                </div>
            </div>
            <div class=" d-flex justify-content-around">
                <button type="submit" class="btn btn-primary">Validez</button>
                <button type="reset" class="btn btn-secondary">Annulez</button>
            </div>
        </form>

        <form class="h-100 mx-auto col-7 d-flex flex-column justify-content-between" enctype="multipart/form-data" method="POST"
              action="LivreModification">
            <input type="file" id="img2" name="img" class="form-control bi-building-fill-exclamation"
                   accept="image/png, image/jpeg" required>
            <button type="submit" class="btn btn-primary">Validez</button>

        </form>
    </div>


</main>
<c:import url="/WEB-INF/JSP/footer.jsp"/>
</body>
</html>
