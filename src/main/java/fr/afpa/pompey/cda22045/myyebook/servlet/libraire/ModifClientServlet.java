package fr.afpa.pompey.cda22045.myyebook.servlet.libraire;

import fr.afpa.pompey.cda22045.myyebook.dao.clientdao.ClientDAOImp;
import fr.afpa.pompey.cda22045.myyebook.dao.emprunterdao.EmprunterDAOImpl;
import fr.afpa.pompey.cda22045.myyebook.model.Emprunter;
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
import java.util.Objects;

@WebServlet(name = "ModifClientServlet", value = "/ModifClient")
@Slf4j
public class ModifClientServlet extends HttpServlet {

    @Override
    public void init() {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.getServletContext().getRequestDispatcher("/JSP/page/modifClient.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ClientDAOImp clientDAOImp = new ClientDAOImp();
        EmprunterDAOImpl emprunterDAOImpl = new EmprunterDAOImpl();
        log.warn("delete");
        HttpSession session = req.getSession();
        String csrfSession = (String) session.getAttribute("csrfToken");
        String csrfReq = (String) req.getParameter("csrf");
        log.info("csrfSession: " + csrfSession);
        log.info("csrfReq: " + csrfReq);

        Integer id = Integer.valueOf(req.getParameter("id"));

        try {
            if (clientDAOImp.get(id) != null) {
                //Verifie si le client a un emprunt
                List<Emprunter> emprunter = emprunterDAOImpl.getAll();
                //Verifie si l'emprunt contient le ID client
                for (Emprunter emp : emprunter) {
                    if (Objects.equals(emp.getClient().getClientId(), id)) {
                        log.warn("client a un emprunt");
                    } else {
                        log.warn("client n'a pas d'emprunt");
                        clientDAOImp.delete(id);
                    }
                }
            }
        } catch(SQLException e){
            resp.sendRedirect(req.getContextPath() + "/accueil");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy() {

    }
}