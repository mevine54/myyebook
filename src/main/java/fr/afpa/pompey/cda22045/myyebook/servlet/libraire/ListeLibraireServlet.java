package fr.afpa.pompey.cda22045.myyebook.servlet.libraire;

import fr.afpa.pompey.cda22045.myyebook.dao.categoriedao.CategorieDAOImpl;
import fr.afpa.pompey.cda22045.myyebook.dao.librairedao.LibraireDAOImp;
import fr.afpa.pompey.cda22045.myyebook.model.Categorie;
import fr.afpa.pompey.cda22045.myyebook.model.Libraire;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ListeLibraireServlet", value = "/ListeLibraire")
public class ListeLibraireServlet extends HttpServlet {

    @Override
    public void init() {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LibraireDAOImp libraireDAOImp = new LibraireDAOImp();
//        HttpSession session = request.getSession(true);
//        session.setAttribute("csrfToken", CSRFTokenUtil.generateCSRFToken());

        try {
            List<Libraire> libraires = libraireDAOImp.getAll();
            request.setAttribute("libraires", libraires);
            //Récupérer l'url du site
            String currentURL = request.getRequestURL().toString();
            //Enregistre l'url dans la variable et envoye à la page JSP
            request.setAttribute("currentURL", currentURL);
            this.getServletContext().getRequestDispatcher("/JSP/page/listeLibraire.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Cannot obtain libraire from DB", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void destroy() {

    }
}