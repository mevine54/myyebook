<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-2 px-3 bg-body-tertiary position-relative vh-100">
    <ul class="nav nav-pills flex-column mb-auto mt-3">
        <li class="nav-item">
            <!-- TODO:Changer la classe "active" quand on est sur la page sinon remplacer par la classe "link-dark" -->
            <a href="${pageContext.request.contextPath}/monCompteLibraire"
               class="nav-link ${currentURL.contains('/monCompteLibraire') ? 'active' : 'link-dark'}"
            ${currentURL.contains('/monCompteLibraire') ? 'aria-current="page"' : ''}>
                <i class="bi bi-list-check" width="16" height="16"></i>
                Mon compte Libraire
            </a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/ListeLibraire"
               class="nav-link ${currentURL.contains('/ListeLibraire') ? 'active' : 'link-dark'}"
            ${currentURL.contains('/ListeLibraire') ? 'aria-current="page"' : ''}>
                <i class="bi bi-list-check" width="16" height="16"></i>
                Liste des Libraire
            </a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/ListeClient"
               class="nav-link ${currentURL.contains('/ListeClient') ? 'active' : 'link-dark'}"
            ${currentURL.contains('/ListeClient') ? 'aria-current="page"' : ''}>
                <i class="bi bi-list-check" width="16" height="16"></i>
                Liste des clients
            </a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/ListeLivre"
               class="nav-link ${currentURL.contains('/ListeLivre') ? 'active' : 'link-dark'}"
            ${currentURL.contains('/ListeLivre') ? 'aria-current="page"' : ''}>
                <i class="bi bi-list-check" width="16" height="16"></i>
                Liste des livres
            </a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/ListeAuteur"
                class="nav-link ${currentURL.contains('/ListeAuteur') ? 'active' : 'link-dark'}"
                ${currentURL.contains('/ListeAuteur') ? 'aria-current="page"' : ''}>
                <i class="bi bi-list-check" width="16" height="16"></i>
                Liste des auteurs
            </a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/ListeCategorie"
               class="nav-link ${currentURL.contains('/ListeCategorie') ? 'active' : 'link-dark'}"
            ${currentURL.contains('/ListeCategorie') ? 'aria-current="page"' : ''}>
                <i class="bi bi-list-check" width="16" height="16"></i>
                Liste des catégories
            </a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/ListeEmprunts"
               class="nav-link ${currentURL.contains('/ListeEmprunts') ? 'active' : 'link-dark'}"
            ${currentURL.contains('/ListeEmprunts') ? 'aria-current="page"' : ''}>
                <i class="bi bi-list-check" width="16" height="16"></i>
                Liste des emprunts
            </a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/deconnexion" class="nav-link link-dark text-danger fw-bolder">
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