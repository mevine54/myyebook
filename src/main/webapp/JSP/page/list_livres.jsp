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
    <title>Liste des livres</title>
</head>
<body class="d-flex flex-column justify-content-between vh-100">
<c:import url="/WEB-INF/JSP/header.jsp" />
<main>
    <%-- INSERER LE CONTENU ICI / Modifier le titre,css,js si besoin--%>
    <div class="container-fluid">
        <div class="row">
            <c:import url="/WEB-INF/JSP/menu_libraire.jsp" />
            <div class="col-8">
                <h1>Liste des Livres</h1>

                <%
                    // Déclaration de la classe Livre directement dans le JSP
                    class Livre {
                        int id;
                        String titre;
                        String auteur;

                        Livre(int id, String titre, String auteur) {
                            this.id = id;
                            this.titre = titre;
                            this.auteur = auteur;
                        }
                    }

                    // Création d'une liste de livres
                    java.util.List<Livre> livres = new java.util.ArrayList<>();
                    livres.add(new Livre(1, "Le Petit Prince", "Antoine de Saint-Exupéry"));
                    livres.add(new Livre(2, "Les Misérables", "Victor Hugo"));
                    livres.add(new Livre(3, "L'Étranger", "Albert Camus"));
                    livres.add(new Livre(4, "Germinal", "Émile Zola"));
                    livres.add(new Livre(5, "La Peste", "Albert Camus"));
                %>

                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Titre</th>
                        <th>Auteur</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        // Générer les lignes du tableau avec la liste de livres
                        for (Livre livre : livres) {
                    %>
                    <tr>
                        <td><%= livre.id %></td>
                        <td><%= livre.titre %></td>
                        <td><%= livre.auteur %></td>
                        <td>
                            <button class="btn btn-primary">Modifier</button>
                            <button class="btn btn-danger">Supprimer</button>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</main>
<c:import url="/WEB-INF/JSP/footer.jsp" />
</body>
</html>
