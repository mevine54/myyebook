package fr.afpa.pompey.cda22045.myyebook.servlet.libraire;

import fr.afpa.pompey.cda22045.myyebook.dao.categoriedao.CategorieDAOImpl;
import fr.afpa.pompey.cda22045.myyebook.model.Categorie;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ListeCategorieServlet", value = "/ListeCategorie")
@Slf4j
public class ListeCategorieServlet extends HttpServlet {

    @Override
    public void init() {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer l'url du site
        // String currentURL = request.getRequestURL().toString();
        // Enregistre l'url dans la variable et envoye à la page JSP
        // request.setAttribute("currentURL", currentURL);
        CategorieDAOImpl categorieDAOImpl = new CategorieDAOImpl();
//        HttpSession session = request.getSession(false);
//        session.setAttribute("csrfToken", CSRFTokenUtil.generateCSRFToken());

        try {
            List<Categorie> categories = categorieDAOImpl.getAll();
            request.setAttribute("categories", categories);
            //Récupérer l'url du site
            String currentURL = request.getRequestURL().toString();
            //Enregistre l'url dans la variable et envoye à la page JSP
            request.setAttribute("currentURL", currentURL);
            this.getServletContext().getRequestDispatcher("/JSP/page/listeCategorie.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Cannot obtain categories from DB", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Récupère les paramètres après l'envoi du formulaire
        // String id = request.getParameter("id");

        // response.sendRedirect(request.getContextPath() + "/ModifCategorie?id=" + id);
    }

    @Override
    public void destroy() {

    }
}