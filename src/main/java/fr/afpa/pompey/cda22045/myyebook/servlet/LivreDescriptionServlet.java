package fr.afpa.pompey.cda22045.myyebook.servlet;

import fr.afpa.pompey.cda22045.myyebook.dao.livredao.LivreDAOImpl;
import fr.afpa.pompey.cda22045.myyebook.model.Livre;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@WebServlet(name = "LivreDescriptionServlet", value = "/livre")
@Slf4j
public class LivreDescriptionServlet extends HttpServlet {

    @Override
    public void init() {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");

        if (idStr != null && idStr.matches("\\d+")) {
            int id = Integer.parseInt(idStr);
            log.info(String.valueOf(id));
            LivreDAOImpl livreDaoImpl = new LivreDAOImpl();
            Livre livre;
            List<Livre> livreSimilaireList;
            try {
                livre = livreDaoImpl.get(id);
                if (livre != null) {
                    request.setAttribute("livre", livre);
                    livreSimilaireList = livreDaoImpl.getParCategorie(livre.getCategorie().getId());
                    request.setAttribute("livreSimilaireList", livreSimilaireList);
                    this.getServletContext().getRequestDispatcher("/JSP/page/livredescription.jsp").forward(request, response);
                }
            } catch (SQLException e) {
                log.info("sql exception");
                throw new RuntimeException(e);
            }
        }
        else{
            response.sendRedirect(request.getContextPath() + "/accueil");
//        this.getServletContext().getRequestDispatcher("/accueil").forward(request, response);

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void destroy() {

    }
}