package fr.afpa.pompey.cda22045.myyebook.servlet.libraire;


import fr.afpa.pompey.cda22045.myyebook.dao.clientdao.ClientDAOImp;
import fr.afpa.pompey.cda22045.myyebook.dao.livredao.LivreDAOImpl;
import fr.afpa.pompey.cda22045.myyebook.model.Client;
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

@Slf4j
@WebServlet(name = "ListeClientServlet", value = "/ListeClient")
public class ListeClientServlet extends HttpServlet {

    private ClientDAOImp clientDAO;

    @Override
    public void init() {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Récupérer l'url du site
        String currentURL = request.getRequestURL().toString();
        //Enregistre l'url dans la variable et envoye à la page JSP
        request.setAttribute("currentURL", currentURL);

        ClientDAOImp clientDAO = new ClientDAOImp();
        try {
            List<Client> clients = clientDAO.getAll();
            request.setAttribute("clients", clients);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        this.getServletContext().getRequestDispatcher("/JSP/page/listeClient.jsp").forward(request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        log.warn("delete");
        HttpSession session = req.getSession();
        String csrfSession = (String) session.getAttribute("csrfToken");
        String csrfReq = (String) req.getParameter("csrf");
        log.info("csrfSession: " + csrfSession);
        log.info("csrfReq: " + csrfReq);
        LivreDAOImpl livreDAOImpl = new LivreDAOImpl();
        if (csrfReq.equals(csrfSession)) {
            try {
                livreDAOImpl.delete(Integer.parseInt(req.getParameter("id")));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else{
            log.warn("csrf different");
        }
    }

    @Override
    public void destroy() {

    }
}