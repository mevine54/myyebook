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
    <title>Modification un compte Libraire</title>
</head>
<%--
    CÔTE ADMIN
    SERVLET: LibraireEnregistrerServlet
--%>
<body class="d-flex flex-column vh-100">
<c:import url="/WEB-INF/JSP/header.jsp" />
<main>
    <div class="container-fluid">
        <div class="row">
            <c:import url="/WEB-INF/JSP/menu_libraire.jsp" />
            <div class="col-8">
                <div class="d-flex justify-content-center my-3">
                    <h2>Modification un compte Libraire</h2>
                </div>
                <form class="mx-auto col-lg-7" method="POST" action="${pageContext.request.contextPath}/ModifLibraire">
                    <c:if test="${param.info == 'error'}">
                        <div class="alert alert-danger" role="alert">
                            <i class="bi bi-exclamation-triangle-fill"></i> Erreur lors de la modification du libraire !
                        </div>
                    </c:if>
                    <a href="${pageContext.request.contextPath}/ListeLibraire" class="btn btn-outline-primary fw-bold rounded-0 mb-3 px-3"><i class="bi bi-arrow-left-short"></i> Retour</a>
                    <input type= "hidden"  name="csrf" value = "<c:out value='${requestScope.csrfToken}'/>" />
                    <c:if test="${not empty requestScope.libraire}">
                    
                        <input type="hidden" name="idLibraire" value="${libraire.libId}">
                        <input type="hidden" name="idCompte" value="${libraire.compte.compteId}">
                        <h3>Informations</h3>
                        <div class="row mb-3">
                            <div class="col">
                                <label for="nom" class="form-label">Nom</label>
                                <input type="text" class="form-control" id="nom" name="nom" value="${libraire.nom}" required>
                            </div>
                            <div class="col ">
                                <label for="prenom" class="form-label">Prénom</label>
                                <input type="text" class="form-control" id="prenom" name="prenom" value="${libraire.prenom}" required>
                            </div>
                        </div>
                        <h3>Compte</h3>
                        <div class="row mb-3">
                            <div class="col">
                                <label for="login" class="form-label">Login</label>
                                <input type="text" class="form-control" id="login" name="login" value="${libraire.compte.login}" required>
                            </div>
                            <div class="col ">
                                <label for="password" class="form-label">Password</label>
                                <input type="password" class="form-control" id="password" name="password" required>
                            </div>
                        </div>

                    </c:if>
                    <div class="d-flex justify-content-center">
                        <button type="submit" class="btn btn-outline-info rounded-0">Modifier</button>
                    </div>
                </form>
            </div>
        </div>
    </div>


</main>
<c:import url="/WEB-INF/JSP/footer.jsp" />
</body>
</html>
