package fr.afpa.pompey.cda22045.myyebook.servlet.libraire;

import fr.afpa.pompey.cda22045.myyebook.dao.categoriedao.CategorieDAOImpl;
import fr.afpa.pompey.cda22045.myyebook.model.Categorie;
import fr.afpa.pompey.cda22045.myyebook.securite.CSRFTokenUtil;
import fr.afpa.pompey.cda22045.myyebook.utilitaires.Verification;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ModifCategorieServlet", value = "/ModifCategorie")
public class ModifCategorieServlet extends HttpServlet {
    @Override
    public void init() {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Récupère l'id de la catégorie par l'URL
        Integer id = Integer.valueOf(request.getParameter("id"));
        //Instancie la classe CategorieDAOImpl
        CategorieDAOImpl categorieDAOImpl = new CategorieDAOImpl();
        //Instancie la classe HttpSession
        HttpSession session = request.getSession(true);
        //Génère un token CSRF
        session.setAttribute("csrfToken", CSRFTokenUtil.generateCSRFToken());
        try {
            //Récupère la catégorie par l'id
            Categorie categories = categorieDAOImpl.get(id);
            //Envoie la catégorie à la page JSP
            request.setAttribute("categories", categories);
        } catch (SQLException e) {
            throw new ServletException("Cannot obtain categories from DB", e);
        }

        //Récupérer l'url du site
        String currentURL = request.getRequestURL().toString();
        //Enregistre l'url dans la variable et envoye à la page JSP
        request.setAttribute("currentURL", currentURL);

        this.getServletContext().getRequestDispatcher("/JSP/page/modifCategorie.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));
        String nomCategorie = request.getParameter("nomCategorie");
        try {
            CategorieDAOImpl categorieDAOImpl = new CategorieDAOImpl();
            Verification.CHARACTER(nomCategorie);
            Categorie categorie = new Categorie(id, nomCategorie);
            try {
                categorieDAOImpl.update(categorie);
                response.sendRedirect(request.getContextPath() + "/ListeCategorie"+"?info=successUpdate");
            } catch (SQLException e) {
                response.sendRedirect(request.getContextPath() + "/ModifCategorie"+"?info=errorDB");
                throw new RuntimeException(e);
            }
        }catch (Exception e){
            response.sendRedirect(request.getContextPath() + "/ModifCategorie"+"?info=error");
        }
    }

    @Override
    public void destroy() {

    }
}