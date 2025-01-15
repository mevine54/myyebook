<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- Ajout de la librairie JSTL -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="/assets/images/img.png" var="couv_livre"/>
<c:url value="/assets/css/bootstrap5.css" var="bootstrap"/>
<c:url value="/assets/css/style.css" var="style"/>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description"
          content="Bienvenue à MyyeBook, votre bibliothèque locale offrant une vaste collection de livres pour tous les âges et tous les goûts. Venez découvrir notre espace convivial, participer à nos événements littéraires et profiter de nos services de prêt. Rejoignez notre communauté de lecteurs passionnés dès aujourd'hui !">
    <link rel="stylesheet" href="assets/css/bootstrap5.css">
    <link rel="stylesheet" href="assets/css/style.css">
    <link rel="stylesheet" href="${bootstrap}">
    <link rel="stylesheet" href="${style}">
    <title>MyyeBook - Votre Bibliothèque Locale pour Tous les Passionnés de Lecture</title>
</head>
<body class="d-flex flex-column vh-100 justify-content-between">
<c:url value="WEB-INF/JSP/header.jsp" />
<main>

    <!-- *********** DEBUT DE CONTENU *********** -->
    <div class="container-fluid container-lg">
        <div class="fs-3 my-3">Nos sélections</div>
        <div class="d-flex justify-content-between overflow-x-auto px-lg-5">
            <a href="" class="link-underline link-underline-opacity-0 link-underline-opacity-0-hover shadow-lg mb-lg-5 rounded">
                <div class="card" style="width: 18rem;">
                    <img src="<c:url value='/assets/images/img.png'/>" class="card-img-top" alt="...">
                    <div class="card-body">
                        <h5 class="card-title">Titre du livre</h5>
                        <p class="card-text"><small class="text-body-secondary">Nom de l'auteur</small></p>
                    </div>
                </div>
            </a>
            <a href="#" class="link-underline link-underline-opacity-0 link-underline-opacity-0-hover shadow-lg mb-lg-5 rounded" >
                <div class="card" style="width: 18rem;">
                    <img src="<c:url value='/assets/images/img.png'/>" class="card-img-top" alt="...">
                    <div class="card-body">
                        <h5 class="card-title">Titre du livre</h5>
                        <p class="card-text"><small class="text-body-secondary">Nom de l'auteur</small></p>
                    </div>
                </div>
            </a>
            <a href="#" class="link-underline link-underline-opacity-0 link-underline-opacity-0-hover shadow-lg mb-lg-5 rounded" >
                <div class="card" style="width: 18rem;">
                    <img src="<c:url value='/assets/images/img.png'/>" class="card-img-top" alt="...">
                    <div class="card-body">
                        <h5 class="card-title">Titre du livre</h5>
                        <p class="card-text"><small class="text-body-secondary">Nom de l'auteur</small></p>
                    </div>
                </div>
            </a>
            <a href="#" class="link-underline link-underline-opacity-0 link-underline-opacity-0-hover shadow-lg mb-lg-5 rounded" >
                <div class="card" style="width: 18rem;">
                    <img src="<c:url value='/assets/images/img.png'/>" class="card-img-top" alt="...">
                    <div class="card-body">
                        <h5 class="card-title">Titre du livre</h5>
                        <p class="card-text"><small class="text-body-secondary">Nom de l'auteur</small></p>
                    </div>
                </div>
            </a>
        </div>

        <div class="fs-3 my-3">Nos livres</div>
        <div class="mx-3">
            <a href="" class="link-underline link-underline-opacity-0 link-underline-opacity-0-hover ">
                <div class="card mb-3 shadow rounded">
                    <div class="row g-0">
                        <div class="col-lg-2 col-5 h-25">
                            <img src="<c:url value='/assets/images/img.png'/>" class="img-fluid rounded-start " alt="...">
                        </div>
                        <div class="col-lg col-5">
                            <div class="card-body">
                                <h5 class="card-title">Titre du livre</h5>
                                <p class="card-text d-none d-lg-block">Lorem ipsum dolor sit amet consectetur adipisicing elit. Quam, officia libero! Recusandae eos accusamus deserunt itaque nemo fugit, unde in libero, delectus deleniti quasi nulla rerum reiciendis sapiente. Sunt, rerum!</p>
                                <p class="card-text d-block d-lg-none text-truncate">Lorem ipsum dolor, sit amet consectetur adipisicing elit. Magnam eligendi optio, qui consequatur, culpa architecto rerum ipsum sunt sapiente nemo aliquid illo, sit ipsa fugiat iste sint dolor cupiditate. Libero.</p>

                            </div>
                        </div>
                    </div>
                </div>
            </a>
            <a href="" class="link-underline link-underline-opacity-0 link-underline-opacity-0-hover">
                <div class="card mb-3 shadow rounded">
                    <div class="row g-0">
                        <div class="col-lg-2 col-5 h-25">
                            <img src="<c:url value='/assets/images/img.png'/>" class="img-fluid rounded-start " alt="...">
                        </div>
                        <div class="col-lg col-5">
                            <div class="card-body">
                                <h5 class="card-title">Titre du livre</h5>
                                <p class="card-text d-none d-lg-block">Lorem ipsum dolor sit amet consectetur adipisicing elit. Quam, officia libero! Recusandae eos accusamus deserunt itaque nemo fugit, unde in libero, delectus deleniti quasi nulla rerum reiciendis sapiente. Sunt, rerum!</p>
                                <p class="card-text d-block d-lg-none text-truncate">Lorem ipsum dolor, sit amet consectetur adipisicing elit. Magnam eligendi optio, qui consequatur, culpa architecto rerum ipsum sunt sapiente nemo aliquid illo, sit ipsa fugiat iste sint dolor cupiditate. Libero.</p>

                            </div>
                        </div>
                    </div>
                </div>
            </a>
        </div>
    </div>
    <!-- *********** FIN DE CONTENU *********** -->
</main>
<c:url value="WEB-INF/JSP/footer.jsp" />
<script src="<c:url value='/assets/js/bootstrap5.js'/>"></script>
</body>
</html>
