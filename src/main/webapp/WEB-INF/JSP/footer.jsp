<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page import="java.time.LocalDate" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<c:url value="/accueil" var="accueilUrl"/>


<%
    LocalDate date = LocalDate.now();
%>

<footer class="bg-info py-3">
    <div class="container">
        <ul class="nav justify-content-center border-bottom pb-3 mb-3">
            <li class="nav-item"><a href="${accueilUrl}" class="nav-link px-2 text-muted">Accueil</a></li>
            <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">Créer un compte</a></li>
            <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">A Propos</a></li>
            <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">RGPD</a></li>
            <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">Contact</a></li>
        </ul>

        <div class="d-flex justify-content-center fs-2 mt-2">
            <div class="row">
                <div class="col">
                    <a href="">
                        <img class="bi bi-facebook" src="assets/images/facebook.svg" alt="facebook">

                    </a>
                </div>
                <div class="col">
                    <a href="">
                        <img class="bi bi-twitter" src="assets/images/twitter.svg" alt="twitter">
                    </a>
                </div>
                <div class="col">
                    <a href="">
                        <img class="bi bi-instagram" src="assets/images/instagram.svg" alt="instagram">
                    </a>
                </div>
                <div class="col">
                    <a href="">
                        <img class="bi bi-linkedin" src="assets/images/linkedin.svg" alt="linkedin">
                    </a>
                </div>
            </div>
        </div>
        <p class="text-center text-muted">© <%= date.getYear()%> MYYEBOOK, S.A. Tous droits réservés.</p>
    </div>
</footer>
<script src="assets/js/bootstrap5.js"></script>
