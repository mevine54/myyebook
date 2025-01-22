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
<%-- SERVLET: ListeLivreServlet --%>
<body class="d-flex flex-column justify-content-between vh-100">
<c:import url="/WEB-INF/JSP/header.jsp" />
<main>
    <div class="container-fluid">
        <div class="row">
            <c:import url="/WEB-INF/JSP/menu_libraire.jsp" />
            <div class="col-8">
                <h1 class="d-flex justify-content-center my-3">Liste des Livres</h1>
                <div class="d-flex justify-content-end"><a class="btn btn-outline-primary rounded-0 mb-3" href="${pageContext.request.contextPath}/CreeUnLivre">Créer un livre</a></div>
                <%
                    // Déclaration de la classe Livre directement dans le JSP
                    class Livre {
                        int id;
                        String titre;
                        String auteur;
                        String image;

                        Livre(int id, String titre, String auteur, String image) {
                            this.id = id;
                            this.titre = titre;
                            this.auteur = auteur;
                            this.image = image;
                        }
                    }

                    // Création d'une liste de livres
                    java.util.List<Livre> livres = new java.util.ArrayList<>();
                    livres.add(new Livre(1, "Le Petit Prince", "Antoine de Saint-Exupéry", "/assets/images/img.png"));
                    livres.add(new Livre(2, "Les Misérables", "Victor Hugo", "/assets/images/img.png"));
                    livres.add(new Livre(3, "L'Étranger", "Albert Camus", "/assets/images/img.png"));
                    livres.add(new Livre(4, "Germinal", "Émile Zola", "/assets/images/img.png"));
                    livres.add(new Livre(5, "La Peste", "Albert Camus", "/assets/images/img.png"));
                %>

                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Image</th>
                        <th>Titre</th>
                        <th>Auteur</th>
                        <th>Mise en avant</th>
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
                        <td><img class="" src="${pageContext.request.contextPath}<%= livre.image %>" alt="..." class="img-thumbnail" style="width: 50px; height: 50px;"></td>
                        <td><%= livre.titre %></td>
                        <td><%= livre.auteur %></td>
                        <td>
                            <div class="form-check form-switch fs-5">
                                <input class="form-check-input" type="checkbox" role="switch" id="switch">
                                <label class="form-check-label" for="switch"></label>
                            </div>


                        </td>
                        <td>
                            <a class="btn btn-outline-primary rounded-0" href="${pageContext.request.contextPath}/LivreModification">Modifier</a>
                            <a class="btn btn-outline-danger rounded-0" href="">Supprimer</a>
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
