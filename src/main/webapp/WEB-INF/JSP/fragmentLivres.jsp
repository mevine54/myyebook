<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="container-fluid container-lg" id="search-results">
    <h2 class="fs-3 my-3">Resultat: </h2>
    <div class="row">
        <c:forEach var="livre" items="${requestScope.livresTrouve}">
        <div class="col-12 col-md-6 col-lg-3 mb-4 px-5 px-sm-3 px-lg-2">
            <a href="<c:url value='/livre?id=${livre.id}'/>"
               class="link-underline link-underline-opacity-0 link-underline-opacity-0-hover shadow-lg rounded">
                <div class="card h-100">
                    <img src="<c:url value="/assets/upload/couverture/${livre.image}"/>" class="card-img-top" alt="...">
                    <div class="card-body d-flex align-items-start flex-column justify-content-around">
                        <h5 class="card-title"><c:out value="${livre.titre}"/></h5>
                        <p class="card-text"><small class="text-body-secondary"><c:out
                                value="${livre.auteur.prenom}"/> <c:out
                                value="${livre.auteur.nom}"/></small></p>
                    </div>
                </div>
            </a>
        </div>
        </c:forEach>
    </div>
</div>
