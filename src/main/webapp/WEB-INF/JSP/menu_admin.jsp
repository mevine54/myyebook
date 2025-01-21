<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-2 px-3 bg-body-tertiary position-relative vh-100">
    <ul class="nav nav-pills flex-column mb-auto mt-3">
        <li class="nav-item">
            <!-- TODO:Changer la classe "active" quand on est sur la page sinon remplacer par la classe "link-dark" -->
            <a href="#" class="nav-link active" aria-current="page">
                <i class="bi bi-person" src="" width="16" height="16"></i>
                Mon compte
            </a>
        </li>
        <li>
            <a href="#" class="nav-link link-dark">
                <i class="bi bi-list-check" width="16" height="16"></i>
                Liste des libraires
            </a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/libraire-enregistrer"
               class="nav-link ${currentURL.contains('/libraire-enregistrer') ? 'active' : 'link-dark'}"
                ${currentURL.contains('/libraire-enregistrer') ? 'aria-current="page"' : ''}>
                <i class="bi bi-list-check" width="16" height="16"></i>
                Créer un compte Libraire
            </a>
        </li>
        <li>
            <a href="#" class="nav-link link-dark">
                <i class="bi bi-list-check" width="16" height="16"></i>
                Liste des clients
            </a>
        </li>
        <li>
            <a href="#" class="nav-link link-dark">
                <i class="bi bi-list-check" width="16" height="16"></i>
                Liste des livres
            </a>
        </li>
        <li>
            <a href="#" class="nav-link link-dark">
                <i class="bi bi-list-check" width="16" height="16"></i>
                Liste des auteurs
            </a>
        </li>
        <li>
            <a href="#" class="nav-link link-dark">
                <i class="bi bi-list-check" width="16" height="16"></i>
                Liste des catégories
            </a>
        </li>
        <li>
            <a href="#" class="nav-link link-dark text-danger fw-bolder">
                <i class="bi bi-box-arrow-left" width="16" height="16"></i>
                Déconnexion
            </a>
        </li>
    </ul>
    <hr>
    <div class="dropdown position-absolute bottom-0 pb-3">
        <a href="#" class="link-dark text-decoration-none" aria-expanded="false"></a>
        <img src="https://github.com/mdo.png" alt="" width="32" height="32" class="rounded-circle me-2">
        <strong>Nom_de_utilisateur</strong>
        </a>
    </div>
</div>