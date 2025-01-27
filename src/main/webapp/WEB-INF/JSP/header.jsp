<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<c:url value="/accueil" var="accueilUrl"/>
<%--<c:url value="/mesemprunts" var="mesempruntsUrl" />--%>
<c:url value="/connexion" var="connexionUrl" />
<c:url value="/client-enregistrer" var="clientenregistrerUrl" />
<c:url value="/livre-recherche" var="livreRechercheUrl" />
<c:url value="/deconnexion" var="deconnexionUrl" />
<c:set value="false" var="isConnected"/>



<header>
    <nav class="navbar navbar-expand-lg py-1 py-lg-3 py-xl-4">

        <div class="container-fluid container-lg">
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo03"
                    aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <a class="navbar-brand fs-2 fw-bold d-flex" href="${accueilUrl}" >
                <div class="m-0 titre">
                    <span class="text-white">MYYE</span>
                    <span class="text-dark">BOOK</span>
                </div>
            </a>
            <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
                <div class="form-control p-0 mt-2 mt-md-0" role="search">
                    <input id="search" class="form-control m-0 barnav" type="search" placeholder="Rechercher un livre..." name="search"
                           hx-post="${livreRechercheUrl}"
                           hx-trigger="input changed delay:500ms, keyup[key=='Enter'], load"
                           hx-target="#search-results"
                           hx-validate="true"
                           required
                    >
                </div>

                <div class="d-flex justify-content-center flex-column flex-lg-row">
                    <c:choose>
                        <c:when test="${not empty sessionScope.role}">
                            <a href="<c:out value="${deconnexionUrl}"/>" class="d-flex flex-wrap justify-content-lg-center align-items-center m-2 m-lg-0 link-underline link-underline-opacity-0 link-underline-opacity-0-hover text-white">
                                <img src="<c:url value='/assets/images/logout.svg'/>" class="rounded-circle img-thumbnail me-2" alt="Image d'un livre">
                                Se déconnecter
                            </a>
                        </c:when>
                        <c:otherwise>
                            <a href="${clientenregistrerUrl}" class="d-flex flex-wrap justify-content-lg-center align-items-center m-2 m-lg-0 link-underline link-underline-opacity-0 link-underline-opacity-0-hover text-white">
                                <img src="<c:url value='/assets/images/person-plus.svg'/>" class="rounded-circle img-thumbnail me-2" alt="Image d'un livre">
                                S'inscrire
                            </a>
                            <a href="${connexionUrl}" class="d-flex flex-wrap justify-content-lg-center align-items-center m-2 m-lg-0 link-underline link-underline-opacity-0 link-underline-opacity-0-hover text-white">
                                <img src="<c:url value='/assets/images/exit_to_app.svg'/>" class="rounded-circle img-thumbnail me-2" alt="Image d'un livre">
                                Se connecter
                            </a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </nav>
</header>