<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<header>
    <nav class="navbar navbar-expand-lg bg-info">
        <div class="container">
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo03"
                    aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <a class="navbar-brand fs-2 fw-bold d-flex" href="${pageContext.request.contextPath}/accueil" >
                <div class="text-white">MYYE</div>
                <div class="test-dark">BOOK</div>
            </a>
            <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
                <form class="form-control p-0" role="search">
                    <input class="form-control m-0" type="search" placeholder="Rechercher un livre..." aria-label=""
                           style="height: 60px;">
                </form>

                <div class="d-flex flex-wrap justify-content-center">
                    <img src="assets/images/img.png" class="rounded-circle img-thumbnail img-nav"
                         alt="Image d'un livre">
                    Emprunts
                </div>
                <div class="d-flex flex-wrap justify-content-center">
                    <img src="assets/images/img.png" class="rounded-circle img-thumbnail img-nav"
                         alt="Image d'un livre">
                    Enreg./Conn.
                </div>
            </div>
        </div>
    </nav>
</header>