<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-2 px-3 bg-body-tertiary position-relative vh-100">
    <ul class="nav nav-pills flex-column mb-auto mt-3">
        <li>
            <a href="${pageContext.request.contextPath}/monCompteClient"
               class="nav-link ${currentURL.contains('/monCompteClient') ? 'active' : 'link-dark'}"
            ${currentURL.contains('/monCompteClient') ? 'aria-current="page"' : ''}>
                <i class="bi bi-list-check" width="16" height="16"></i>
                Mon compte
            </a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/mesemprunts"
               class="nav-link ${currentURL.contains('/mesemprunts') ? 'active' : 'link-dark'}"
            ${currentURL.contains('/mesemprunts') ? 'aria-current="page"' : ''}>
                <i class="bi bi-list-check" width="16" height="16"></i>
                Mes emprunts
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