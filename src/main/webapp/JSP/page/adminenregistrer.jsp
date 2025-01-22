<%--
  Created by IntelliJ IDEA.
  User: USER-3
  Date: 16/01/2025
  Time: 15:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:url value="/create-admin" var="createAdminUrl"/>
<c:url value="/assets/css/bootstrap5.css" var="bootstrap"/>
<c:url value="/assets/css/style.css" var="style"/>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${bootstrap}">
    <link rel="stylesheet" href="${style}">
    <title>Créer un compte admin - MyyeBook</title>
</head>
<%-- SERVLET: AdminEnregistrerServlet  --%>
<body class="d-flex flex-column vh-100">
<c:import url="/WEB-INF/JSP/header.jsp" />
<main class="mt-4">
    <div class="container">
        <div class="d-flex justify-content-center mb-3">
            <h2>Créer un compte admin</h2>
        </div>
        <form class="mx-auto col-7" method="POST" action="${createAdminUrl}">
            <input type="hidden" name="csrfToken" value="<c:out value="${sessionScope.csrfToken}" />">
            <div class="row mb-3">
                <div class="col">
                    <label for="username" class="form-label">Nom d'utilisateur</label>
                    <input type="text" class="form-control" id="username" name="username" required>
                </div>
            </div>
            <div class="row mb-3">
                <div class="col">
                    <label for="password" class="form-label">Mot de passe</label>
                    <input type="password" class="form-control" id="password" name="password" required>
                </div>
            </div>
            <div class="d-flex justify-content-center">
                <button type="submit" class="btn btn-primary">Créer</button>
            </div>
        </form>
    </div>
</main>
<c:import url="/WEB-INF/JSP/footer.jsp" />
</body>
</html>

