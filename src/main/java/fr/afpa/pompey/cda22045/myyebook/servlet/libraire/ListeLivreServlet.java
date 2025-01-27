package fr.afpa.pompey.cda22045.myyebook.servlet.libraire;

import fr.afpa.pompey.cda22045.myyebook.dao.livredao.LivreDAOImpl;
import fr.afpa.pompey.cda22045.myyebook.model.Livre;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ListeLivreServlet", value = "/ListeLivre")
public class ListeLivreServlet extends HttpServlet {
    @Override
    public void init() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Récupérer l'url du site
        String currentURL = request.getRequestURL().toString();
        //Enregistre l'url dans la variable et envoye à la page JSP
        request.setAttribute("currentURL", currentURL);

        LivreDAOImpl livreDAOImpl = new LivreDAOImpl();

        try {
            List<Livre> livres = livreDAOImpl.getAll();
            request.setAttribute("livres",livres);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        this.getServletContext().getRequestDispatcher("/JSP/page/list_livres.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    public void destroy() {
    }

}

