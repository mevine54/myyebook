<%@ page import="java.time.LocalDate" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value="/accueil" var="accueilUrl"/>
<c:url value='/assets/images/facebook.svg' var="facebook"/>
<c:url value='/assets/images/linkedin.svg' var="linkedin"/>
<c:url value='/assets/images/instagram.svg' var="instagram"/>
<c:url value='/assets/images/twitter.svg' var="twitter"/>


<%
    LocalDate date = LocalDate.now();
%>

<footer class="bg-info mt-auto py-3 mt-auto">
    <div class="container">
        <ul class="nav justify-content-center border-bottom pb-3 mb-3">
            <li class="nav-item"><a href="${accueilUrl}" class="nav-link px-2 text-muted">Accueil</a></li>
            <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">Créer un compte</a></li>
            <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">A Propos</a></li>
            <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">RGPD</a></li>
        </ul>

        <div class="d-flex justify-content-center fs-2 mt-2">
            <div class="row">
                <div class="col">
                    <a href="">
                        <img class="bi bi-facebook link-opacity-100-hover" src="${facebook}"  alt="facebook">

                    </a>
                </div>
                <div class="col">
                    <a href="">
                        <img class="bi bi-twitter link-opacity-100-hover" src="${twitter}" alt="twitter">
                    </a>
                </div>
                <div class="col">
                    <a href="">
                        <img class="bi bi-instagram link-opacity-100-hover" src="${instagram}" alt="instagram">
                    </a>
                </div>
                <div class="col">
                    <a href="">
                        <img class="bi bi-linkedin link-opacity-100-hover" src="${linkedin}"alt="linkedin">
                    </a>
                </div>
            </div>
        </div>
        <p class="text-center text-muted mb-0">© <%= date.getYear()%> MYYEBOOK, S.A. Tous droits réservés.</p>
    </div>
</footer>
<script src="assets/js/bootstrap5.js"></script>
