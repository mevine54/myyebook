<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description"
          content="Bienvenue à MyyeBook, votre bibliothèque locale offrant une vaste collection de livres pour tous les âges et tous les goûts. Venez découvrir notre espace convivial, participer à nos événements littéraires et profiter de nos services de prêt. Rejoignez notre communauté de lecteurs passionnés dès aujourd'hui !">
    <link rel="stylesheet" href="assets/css/bootstrap5.css">
    <link rel="stylesheet" href="assets/css/style.css">
    <title>MyyeBook - Votre Bibliothèque Locale pour Tous les Passionnés de Lecture</title>
</head>
<body>
<%@include file="WEB-INF/JSP/header.jsp" %>
<main>
    <!-- *********** DEBUT DE CONTENU *********** -->
    <div class="container">
        <div class="fs-3 my-3">Nos sélections</div>
        <div class="d-flex justify-content-between mx-3">
            <a href="#" class="link-underline link-underline-opacity-0 link-underline-opacity-0-hover" >
                <div class="card" style="width: 18rem;">
                    <img src="assets/images/img.png" class="card-img-top" alt="...">
                    <div class="card-body">
                        <h5 class="card-title">Titre du livre</h5>
                        <p class="card-text"><small class="text-body-secondary">Nom de l'auteur</small></p>
                    </div>
                </div>
            </a>
            <a href="#" class="link-underline link-underline-opacity-0 link-underline-opacity-0-hover" >
                <div class="card" style="width: 18rem;">
                    <img src="assets/images/img.png" class="card-img-top" alt="...">
                    <div class="card-body">
                        <h5 class="card-title">Titre du livre</h5>
                        <p class="card-text"><small class="text-body-secondary">Nom de l'auteur</small></p>
                    </div>
                </div>
            </a>
            <a href="#" class="link-underline link-underline-opacity-0 link-underline-opacity-0-hover" >
                <div class="card" style="width: 18rem;">
                    <img src="assets/images/img.png" class="card-img-top" alt="...">
                    <div class="card-body">
                        <h5 class="card-title">Titre du livre</h5>
                        <p class="card-text"><small class="text-body-secondary">Nom de l'auteur</small></p>
                    </div>
                </div>
            </a>
            <a href="#" class="link-underline link-underline-opacity-0 link-underline-opacity-0-hover" >
                <div class="card" style="width: 18rem;">
                    <img src="assets/images/img.png" class="card-img-top" alt="...">
                    <div class="card-body">
                        <h5 class="card-title">Titre du livre</h5>
                        <p class="card-text"><small class="text-body-secondary">Nom de l'auteur</small></p>
                    </div>
                </div>
            </a>
        </div>

        <div class="fs-3 my-3">Nos livres</div>
        <div class="mx-3">
            <a href=""  class="link-underline link-underline-opacity-0 link-underline-opacity-0-hover">
                <div class="card mb-3">
                    <div class="row g-0">
                        <div class="col-2 h-25">
                            <img src="assets/images/img.png" class="img-fluid rounded-start " alt="...">
                        </div>
                        <div class="col">
                            <div class="card-body">
                                <h5 class="card-title">Titre du livre</h5>
                                <p class="card-text">Lorem ipsum dolor, sit amet consectetur adipisicing elit. Magnam eligendi optio, qui consequatur, culpa architecto rerum ipsum sunt sapiente nemo aliquid illo, sit ipsa fugiat iste sint dolor cupiditate. Libero.</p>
                            </div>
                        </div>
                    </div>
                </div>
            </a>
            <a href=""  class="link-underline link-underline-opacity-0 link-underline-opacity-0-hover">
                <div class="card mb-3">
                    <div class="row g-0">
                        <div class="col-2 h-25">
                            <img src="assets/images/img.png" class="img-fluid rounded-start " alt="...">
                        </div>
                        <div class="col">
                            <div class="card-body">
                                <h5 class="card-title">Titre du livre</h5>
                                <p class="card-text">Lorem ipsum dolor, sit amet consectetur adipisicing elit. Magnam eligendi optio, qui consequatur, culpa architecto rerum ipsum sunt sapiente nemo aliquid illo, sit ipsa fugiat iste sint dolor cupiditate. Libero.</p>
                            </div>
                        </div>
                    </div>
                </div>
            </a>
        </div>
    </div>
    <!-- *********** FIN DE CONTENU *********** -->
</main>
<%@include file="WEB-INF/JSP/footer.jsp" %>
</body>
</html>
