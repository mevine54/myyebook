package fr.afpa.pompey.cda22045.myyebook.servlet.libraire;

import fr.afpa.pompey.cda22045.myyebook.dao.clientdao.ClientDAOImp;
import fr.afpa.pompey.cda22045.myyebook.dao.emprunterdao.EmprunterDAOImpl;
import fr.afpa.pompey.cda22045.myyebook.model.Client;
import fr.afpa.pompey.cda22045.myyebook.model.Compte;
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
        String idClient = request.getParameter("id");
        ClientDAOImp clientDAOImp = new ClientDAOImp();
        try {
            //Verifie si idClient n'est pas vide et si il contient un nombre
            if (idClient != null && idClient.matches("\\d+")) {
                int id = Integer.parseInt(idClient);
                request.setAttribute("client", clientDAOImp.get(id));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        this.getServletContext().getRequestDispatcher("/JSP/page/modifClient.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, RuntimeException {
        ClientDAOImp clientDAOImp = new ClientDAOImp();
        log.warn("post");
        HttpSession session = request.getSession();
        String csrfSession = (String) session.getAttribute("csrfToken");
        String csrfReq = (String) request.getParameter("csrf");
        log.info("csrfSession: " + csrfSession);
        log.info("csrfReq: " + csrfReq);

        Integer clientId = Integer.valueOf(request.getParameter("clientId"));
        Integer compteId = Integer.valueOf(request.getParameter("compteId"));
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String adresse = request.getParameter("adresse");
        String ville = request.getParameter("ville");
        String codePostal = request.getParameter("codePostal");
        String login = request.getParameter("login");
        String password = request.getParameter("mdp1");
        String password2 = request.getParameter("mdp2");


        //Verifie si les mots de passe sont identiques

        if (!password.equals(password2)) {
            response.sendRedirect(request.getContextPath() + "/MotifClient?info=error");
            //new RuntimeException("Les mots de passe ne sont pas identiques");
        }else{

            try {
                if (clientDAOImp.get(clientId) != null) {
                    Compte compte = new Compte(
                            compteId,
                            login,
                            password
                    );
                    Client client = new Client(
                            compte,
                            clientId,
                            nom,
                            prenom,
                            email,
                            adresse,
                            ville,
                            codePostal
                    );
                    clientDAOImp.update(client);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            response.sendRedirect(request.getContextPath() + "/ListeClient?info=success");

        }

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