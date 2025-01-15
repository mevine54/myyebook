<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:url value="/accueil" var="accueilUrl"/>
<%--<c:url value="/mesemprunts" var="mesempruntsUrl" />--%>
<c:url value="/connexion" var="connexionUrl" />
<c:url value="/client-enregistrer" var="client-enregistrerUrl" />

<header>
    <nav class="navbar navbar-expand-lg bg-info">
        <div class="container-fluid container-lg">
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo03"
                    aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <a class="navbar-brand fs-2 fw-bold d-flex" href="${accueilUrl}" >
                <div class="text-white">MYYE</div>
                <div class="text-dark">BOOK</div>
            </a>
            <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
                <form class="form-control p-0 mt-2 mt-md-0" role="search">
                    <input class="form-control m-0 barnav" type="search" placeholder="Rechercher un livre...">
                </form>

                <div class="d-flex justify-content-center flex-column flex-lg-row">
                    <c:if test="${empty role}">
                    <a href="${connexionUrl}" class="d-flex flex-wrap justify-content-lg-center align-items-center m-2 m-lg-0 link-underline link-underline-opacity-0 link-underline-opacity-0-hover">
                        <img src="assets/images/img.png" class="rounded-circle img-thumbnail me-2 img-nav"
                             alt="Image d'un livre">
                        Se connecter
                    </a>
                    </c:if>
                    <c:if test="${empty role}">
                    <a href="${client-enregistrerUrl}" class="d-flex flex-wrap justify-content-lg-center align-items-center m-2 m-lg-0 link-underline link-underline-opacity-0 link-underline-opacity-0-hover">
                        <img src="assets/images/img.png" class="rounded-circle img-thumbnail me-2 img-nav"
                             alt="Image d'un livre">
                        Enreg./Conn.
                    </a>
                    </c:if>
                </div>
            </div>
        </div>
    </nav>
</header>