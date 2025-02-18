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
    <title>Crée un livre - Libraire</title>
</head>
<%-- SERVLET: LivreModificationServlet --%>
<body class="d-flex flex-column vh-100">
<c:import url="/WEB-INF/JSP/header.jsp" />
<main>
    <!-- *********** DEBUT DE CONTENU *********** -->
    <div class="container-fluid" >
        <div class="row">
            <!-- Menu latérale pour client -->
            <c:import url="/WEB-INF/JSP/menu_libraire.jsp" />
            <div class="col-8">
                <div class="d-flex justify-content-center my-3">
                    <h2>Crée un livre</h2>
                </div>
                <form class="mx-auto col-lg-7" enctype="multipart/form-data" method="POST" action="CreeUnLivre">
                    <input type= "hidden"  name="csrf" value = "<c:out value='${requestScope.csrfToken}'/>" />
                    <a href="${pageContext.request.contextPath}/ListeLivre" class="btn btn-outline-primary fw-bold rounded-0 mb-3 px-3"><i class="bi bi-arrow-left-short"></i> Retour</a>
                    <div class="row mb-3">
                        <div class="col">
                            <label for="nom" class="form-label">Nom du livre</label>
                            <input type="text" class="form-control" id="nom" name="nom"  required>
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
                        <div class="col-12">
                            <label for="categorie" class="form-label">Categories</label>
                            <select class="form-select" id="categorie" name="categorie" required>
                                <option value="" selected disabled>Veuillez selectionez une option</option>
                                <c:forEach var="categorie" items="${requestScope.categorieList}">
                                    <option value="<c:out value="${categorie.id}"/>"><c:out value="${categorie.nom}"/></option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col">
                            <div class="form-group">
                                <label for="quantite">Quantité (0-100):</label>
                                <input type="number" class="form-control" id="quantite" name="quantite" min="0" max="100" />
                            </div>
                        </div>
                        <div class="col-12 mt-3 input-group mb-3">
                            <label class="input-group-text" for="img">Choisissez une nouvelle couverture</label>
                            <input type="file" id="img" name="img" class="form-control bi bi-file-image fill-exclamation"
                                   accept="image/png, image/jpeg" required>
                        </div>
                    </div>
                    <%--            <h3>Resumé</h3>--%>
                    <div class="row mb-3">
                        <div class="col-12 mb-3">
                            <label for="resume" class="form-label">Resume</label>
                            <textarea class="form-control" style="height: 150px" id="resume" name="resume"
                                      placeholder="Ecrivez ici..."></textarea>
                        </div>
                    </div>
                    <div class="d-flex justify-content-center mt-3">
                        <button type="submit" class="btn btn-outline-info fw-bold rounded-0 mx-3 px-3">Valider</button>
                        <button type="reset" class="btn btn-outline-secondary fw-bold rounded-0 mx-3 px-3">Annuler</button>
                    </div>
                </form>
            </div>
        </div>

    </div>


</main>

<c:import url="/WEB-INF/JSP/footer.jsp" />
</body>
</html>
