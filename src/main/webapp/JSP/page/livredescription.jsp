<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- Ajout de la librairie JSTL -->
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
    <%-- TODO: A modifier le titre du document en récupérant les info du controller --%>
    <title>Livre : TitreDuLivre - MyyeBook</title>
</head>
<body class="d-flex flex-column vh-100">
<%@include file="../../WEB-INF/JSP/header.jsp" %>
<main>
    <!-- *********** DEBUT DE CONTENU *********** -->
    <div class="container">
        <div class="row my-5">
            <div class="col-3">
                <img src="assets/images/couverturelivre.jpg" alt="Couverture du livre" class="img-fluid">
            </div>
            <div class="col-9 d-flex flex-column">
                <div class="fs-3">
                    Titre du livre
                </div>
                <div class="fs-5">
                    Nom de l'auteur
                </div>
                <div class="">
                    <div class="mb-auto">
                        <p>
                            Lorem ipsum dolor sit amet consectetur adipisicing elit. Corporis labore culpa natus recusandae, error consequatur, nihil doloribus modi commodi dolorem ducimus sequi quidem et, provident inventore quod perferendis nemo non?
                        </p>
                    </div>
                    <div class="mt-auto">
                        <div class="d-flex justify-content-end align-items-end h-100">
                            <button type="button" class="btn btn-info">Emprunter ce livre</button>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
    <!--  ************ PARTIE : LES LIVRES SIMILAIRES ************ -->
    <div class="container bg-secondary-subtle my-3 py-1">
        <div class="fs-3 fw-bold">Les livres similaires</div>
        <div class="d-flex flex-wrap">
            <%--
            TODO:
                Récupère les informations du controller et génère une boucle for
                    en utilisant le modèle d'afficher le livre :
                    <div class="my-2 mx-2">
                        <div class="card" style="width: 150px;">
                            <img src="assets/images/couverturelivre.jpg" class="card-img-top" alt="Couverture du livre">
                        </div>
                    </div>
            --%>
            <div class="my-2 mx-2">
                <div class="card" style="width: 150px;">
                    <img src="assets/images/couverturelivre.jpg" class="card-img-top" alt="Couverture du livre">
                </div>
            </div>
            <div class="my-2 mx-2">
                <div class="card" style="width: 150px;">
                    <img src="assets/images/couverturelivre.jpg" class="card-img-top" alt="Couverture du livre">
                </div>
            </div>
            <div class="my-2 mx-2">
                <div class="card" style="width: 150px;">
                    <img src="assets/images/couverturelivre.jpg" class="card-img-top" alt="Couverture du livre">
                </div>
            </div>
            <div class="my-2 mx-2">
                <div class="card" style="width: 150px;">
                    <img src="assets/images/couverturelivre.jpg" class="card-img-top" alt="Couverture du livre">
                </div>
            </div>
            <div class="my-2 mx-2">
                <div class="card" style="width: 150px;">
                    <img src="assets/images/couverturelivre.jpg" class="card-img-top" alt="Couverture du livre">
                </div>
            </div>
            <div class="my-2 mx-2">
                <div class="card" style="width: 150px;">
                    <img src="assets/images/couverturelivre.jpg" class="card-img-top" alt="Couverture du livre">
                </div>
            </div>
            <div class="my-2 mx-2">
                <div class="card bg-white" style="width: 150px;">
                    <img src="assets/images/couverturelivre.jpg" class="card-img-top opacity-25" alt="Couverture du livre">
                    <div class="card-img-overlay d-flex justify-content-center align-items-center">
                        <h5 class="card-title text-center text-dark fw-bold">Voir plus</h5>
                    </div>
                </div>
            </div>

        </div>
    </div>
    <!-- *********** FIN DE CONTENU *********** -->
</main>
<%@include file="../../WEB-INF/JSP/footer.jsp" %>
</body>
</html>
