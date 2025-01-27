package fr.afpa.pompey.cda22045.myyebook.filter;

import fr.afpa.pompey.cda22045.myyebook.dao.livredao.LivreDAOImpl;
import fr.afpa.pompey.cda22045.myyebook.model.Livre;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebFilter("*")
public class RechercheFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialisation du filtre si n�cessaire
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        String search = request.getParameter("search");
        if (search != null && !search.trim().isEmpty()) {
            LivreDAOImpl livreDAOImpl = new LivreDAOImpl();
            List<Livre> livresTrouve;
            try {
                livresTrouve = livreDAOImpl.chercherTitreOuAuteur(search.trim());
                request.setAttribute("livresTrouve", livresTrouve);
                request.getRequestDispatcher("/WEB-INF/JSP/fragmentLivres.jsp").forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "Une erreur est survenue lors de la recherche des livres.");
                request.getRequestDispatcher("/WEB-INF/JSP/page/error.jsp").forward(request, response);
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        // Nettoyage du filtre si n�cessaire
    }
}