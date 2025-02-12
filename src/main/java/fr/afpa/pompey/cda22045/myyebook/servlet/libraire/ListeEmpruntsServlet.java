package fr.afpa.pompey.cda22045.myyebook.servlet.libraire;

import fr.afpa.pompey.cda22045.myyebook.dao.emprunterdao.EmprunterDAOImpl;
import fr.afpa.pompey.cda22045.myyebook.model.Emprunter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@WebServlet(name = "ListeEmpruntsServlet", value = "/ListeEmprunts")
public class ListeEmpruntsServlet extends HttpServlet {
    EmprunterDAOImpl emprunterDAOImpl = new EmprunterDAOImpl();

    @Override
    public void init() {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            List<Emprunter> emprunterList = emprunterDAOImpl.getAll();
            request.setAttribute("emprunter", emprunterList);
            //Récupérer l'url du site
            String currentURL = request.getRequestURL().toString();
            //Enregistre l'url dans la variable et envoye à la page JSP
            request.setAttribute("currentURL", currentURL);
            log.info(emprunterList.toString());
            this.getServletContext().getRequestDispatcher("/JSP/page/listeEmprunts.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Impossible d'obtenir les informations dans la base de données", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String info = request.getParameter("info");
        String idStr = request.getParameter("id");
        String empruntstatut = request.getParameter("empruntstatut" + idStr);
        Integer emprunterId = Integer.parseInt(idStr);
        //Verifie si les valeurs ne sont pas nulles
        try {
            // TODO: verifier la quantite de livre disponible avant emprunt
            if (emprunterId != null) {
                Emprunter emprunter = emprunterDAOImpl.get(emprunterId);
                if (emprunter != null) {
                    if (empruntstatut.equals("attente")) {
                        emprunter.setDatetimeRetour(null);
                        emprunter.setDatetimeEmprunt(null);
                        emprunterDAOImpl.update(emprunter);
                        response.sendRedirect(request.getContextPath() + "/ListeEmprunts?info=attente");
                    } else if (empruntstatut.equals("encours")) {
                        emprunter.setDatetimeEmprunt(LocalDateTime.now());
                        emprunter.setDatetimeRetour(null);
                        emprunterDAOImpl.update(emprunter);
                        response.sendRedirect(request.getContextPath() + "/ListeEmprunts?info=valider");
                    } else if (empruntstatut.equals("terminer")) {
                        emprunter.setDatetimeRetour(LocalDateTime.now());
                        emprunterDAOImpl.update(emprunter);
                        response.sendRedirect(request.getContextPath() + "/ListeEmprunts?info=rendre");
                    }
                }
            }
        } catch (
                Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            emprunterDAOImpl.delete(Integer.parseInt(req.getParameter("id")));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy() {
    }

}