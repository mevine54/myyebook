<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Connectez-vous à MyyeBook pour accéder à votre bibliothèque personnelle et découvrir de nouveaux livres">
    <link rel="stylesheet" href="assets/css/bootstrap5.css">
    <link rel="stylesheet" href="assets/css/style.css">
    <title>MyyeBook - Connectez-vous pour Lire</title>
</head>
<body>
<%--<jsp:include page="../../WEB-INF/JSP/header.jsp">--%>
<%--    <jsp:param name="voirRechecheLivre" value="true" />--%>
<%--    <jsp:param name="param" value="true" />--%>
<%--</jsp:include>--%>
<%@include file="../../WEB-INF/JSP/header.jsp"%>
<main>
    <%-- INSERER LE CONTENU ICI / Modifier le titre,css,js si besoin--%>
        <div class="container">
            <form class=" mx-auto col-7" method="POST" action="connexion">
                <div class="row mb-3">
                    <div class="col-12">
                        <label for="utilisateur" class="form-label">Nom d'utilisateur</label>
                        <input type="text" class="form-control" id="utilisateur" name="utilisateur" required>
                    </div>
                    <div class="col-12">
                        <label for="mdp" class="form-label">Mot de passe</label>
                        <input type="password" class="form-control" id="mdp" name="mdp" required>
                    </div>
                </div>
                <div class="d-flex justify-content-center">
                    <button type="submit" class="btn btn-primary">Se connecter</button>
                </div>
            </form>
        </div>
</main>


<%@include file="../../WEB-INF/JSP/footer.jsp"%>
<%--<%@include file="../footer.jsp"%>--%>
</body>
</html>
