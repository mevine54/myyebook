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
    <title>MyyeBook - Votre Bibliothèque Locale pour Tous les Passionnés de Lecture</title>
</head>
<body>
<%@include file="../header.jsp" %>
<main>
    <%-- INSERER LE CONTENU ICI / Modifier le titre,css,js si besoin--%>
</main>
<%@include file="../footer.jsp" %>
</body>
</html>
