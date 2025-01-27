package fr.afpa.pompey.cda22045.myyebook.servlet;

import fr.afpa.pompey.cda22045.myyebook.dao.librairedao.LibraireDAOImp;
import fr.afpa.pompey.cda22045.myyebook.dao.livredao.LivreDAOImpl;
import fr.afpa.pompey.cda22045.myyebook.model.Livre;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "AccueilServlet", value = {"/index.jsp", "/accueil"})
public class AccueilServlet extends HttpServlet {

    @Override
    public void init() {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        LivreDAOImpl livreDAOImpl = new LivreDAOImpl();
        try {
            List<Livre> livreList = livreDAOImpl.getAll();
            request.setAttribute("livres", livreList);
            List<Livre> livresEnavant = livreDAOImpl.getEnAvant();
            request.setAttribute("livresEnavant", livresEnavant);
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void destroy() {

    }
}