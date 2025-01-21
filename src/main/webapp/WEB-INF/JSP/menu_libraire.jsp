<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-2 px-3 bg-body-tertiary position-relative vh-100">
    <ul class="nav nav-pills flex-column mb-auto">
        <li class="nav-item">
            <!-- TODO:Changer la classe "active" quand on est sur la page sinon remplacer par la classe "link-dark" -->
            <a href="${pageContext.request.contextPath}/libraire/1/info-modif"
               class="nav-link ${currentURL.contains('/libraire/1/info-modif') ? 'active' : 'link-dark'}"
                ${currentURL.contains('/libraire/1/info-modif') ? 'aria-current="page"' : ''}>
                <i class="bi bi-list-check" width="16" height="16"></i>
                Mon compte Libraire
            </a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/ListeLivreEmprunter"
               class="nav-link ${currentURL.contains('/ListeLivreEmprunter') ? 'active' : 'link-dark'}"
                ${currentURL.contains('/ListeLivreEmprunter') ? 'aria-current="page"' : ''}>
                <i class="bi bi-list-check" width="16" height="16"></i>
                Livres en emprunts
            </a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/ListeLivre-servlet"
               class="nav-link ${currentURL.contains('/ListeLivre-servlet') ? 'active' : 'link-dark'}"
                ${currentURL.contains('/ListeLivre-servlet') ? 'aria-current="page"' : ''}>
                <i class="bi bi-list-check" width="16" height="16"></i>
                Liste des livres
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