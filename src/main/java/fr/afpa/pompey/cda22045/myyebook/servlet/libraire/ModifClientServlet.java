package fr.afpa.pompey.cda22045.myyebook.servlet.libraire;

import fr.afpa.pompey.cda22045.myyebook.dao.clientdao.ClientDAOImp;
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

@WebServlet(name = "ModifClientServlet", value = "/ModifClient")
@Slf4j
public class ModifClientServlet extends HttpServlet {

    @Override
    public void init() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        Client client;
        if (idStr != null && idStr.matches("\\d+")) {
            int id = Integer.parseInt(idStr);
            fr.afpa.pompey.cda22045.myyebook.dao.clientdao.ClientDAOImp clientDAOImp = new ClientDAOImp();
            try{
                client = clientDAOImp.get(id);
                if(client != null){
                    request.setAttribute("client", client);
                    this.getServletContext().getRequestDispatcher("/JSP/page/modifClient.jsp").forward(request, response);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/accueil");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.warn("delete");

        HttpSession session = req.getSession();
        String csrfSession = (String) session.getAttribute("csrfToken");
        String csrfReq = req.getParameter("csrf");

        log.info("csrfSession: " + csrfSession);
        log.info("csrfReq: " + csrfReq);

        if (csrfReq != null && csrfReq.equals(csrfSession)) {
            ClientDAOImp clientDAOImp = new ClientDAOImp();
            try{
                String idParam = req.getParameter("id");
                log.info("idParam: " + idParam);

                int clientId = Integer.parseInt(idParam);
                log.info("clientId: " + clientId);

                clientDAOImp.delete(clientId);
                log.info("Client ID " + clientId + " deleted.");
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().write("Client ID " + clientId + " deleted.");
            } catch (NumberFormatException nfe){
                log.error("Erreur de convertion de l'ID: " + req.getParameter("id"), nfe);
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur SQL lors de la suppression");
            } catch (SQLException se) {
                log.error("Erreur SQL lors de la suppression du client ", se);
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur SQL lors de la suppression.");
            } catch (Exception e) {
                log.error("Erreur inattendue dans doDelete", e);
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur interne.");
            }
        } else {
            log.warn("CSRF Token invalide");
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Requête non autorisée.");
        }
    }

    @Override
    public void destroy() {

    }
}