<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value="/assets/css/bootstrap5.css" var="bootstrap"/>
<c:url value="/assets/css/style.css" var="style"/>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Connectez-vous à MyyeBook pour accéder à votre bibliothèque personnelle et découvrir de nouveaux livres">
    <link rel="stylesheet" href="${bootstrap}">
    <link rel="stylesheet" href="${style}">
    <title>MyyeBook - Connectez-vous pour Lire</title>
</head>
<body class="d-flex flex-column vh-100">
<%--<jsp:include page="../../WEB-INF/JSP/header.jsp">--%>
<%--    <jsp:param name="voirRechecheLivre" value="true" />--%>
<%--    <jsp:param name="param" value="true" />--%>
<%--</jsp:include>--%>
<c:import url="/WEB-INF/JSP/header.jsp" />
<main>
    <%-- INSERER LE CONTENU ICI / Modifier le titre,css,js si besoin--%>
        <div class="container">
            <form class="col-lg-5 mx-auto border border-info p-5 text-center mt-5" method="POST" action="connexion">
                <div class="col">
                    <label for="utilisateur" class="form-label">Nom d'utilisateur</label>
                    <input type="text" class="form-control" id="utilisateur" name="utilisateur" required>
                </div>
                <div class="col mt-3">
                    <label for="mdp" class="form-label">Mot de passe</label>
                    <input type="password" class="form-control" id="mdp" name="mdp" required>
                </div>
                <div class="d-flex justify-content-center mt-3">
                    <button type="submit" class="btn btn-outline-info fw-bold rounded-0">Se connecter</button>
                </div>
            </form>
        </div>
</main>


<c:import url="/WEB-INF/JSP/footer.jsp" />
<%--<%@include file="../footer.jsp"%>--%>
</body>
</html>
