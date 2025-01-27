package fr.afpa.pompey.cda22045.myyebook.servlet;

import fr.afpa.pompey.cda22045.myyebook.dao.clientdao.ClientDAOImp;
import fr.afpa.pompey.cda22045.myyebook.dao.comptedao.CompteDAOImp;
import fr.afpa.pompey.cda22045.myyebook.dao.librairedao.LibraireDAOImp;
import fr.afpa.pompey.cda22045.myyebook.model.Client;
import fr.afpa.pompey.cda22045.myyebook.model.Compte;
import fr.afpa.pompey.cda22045.myyebook.model.Libraire;
import fr.afpa.pompey.cda22045.myyebook.securite.CSRFTokenUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

@Slf4j
@WebServlet(name = "connexionServlet", value = "/connexion")
public class ConnexionServlet extends HttpServlet {

    @Override
    public void init() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Rediriger vers la page de connexion si l'utilisateur n'est pas connecté
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("role") != null) {
            response.sendRedirect("monCompteLibraire.jsp");
        } else {
            this.getServletContext().getRequestDispatcher("/JSP/page/connexion.jsp").forward(request, response);

        }
//        request.getSession(false);
//        HttpSession session = request.getSession(true);
//        request.setAttribute("csrfToken", CSRFTokenUtil.generateCSRFToken());
//        System.out.println(request.getAttribute("csrfToken"));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String utilisateur = request.getParameter("utilisateur");
        String mdp = request.getParameter("mdp");
        log.info("utilisateur: " + utilisateur + " mdp: " + mdp);


        CompteDAOImp compteDAOImpl = new CompteDAOImp();
        LibraireDAOImp libraireDAOImpl = new LibraireDAOImp();
        ClientDAOImp clientDAOImp = new ClientDAOImp();
        HttpSession session = null;
        Compte compte = null;
        try {
            compte = compteDAOImpl.getParLogin(utilisateur);
            if (compte != null && (compte.getRole().equals("ROLE_LIBRAIRE") || compte.getRole().equals("ROLE_LIBRAIRE_ATTENTE"))) {
                Libraire libraire = libraireDAOImpl.getParCompteId(compte.getCompteId());
                if (libraire != null) {
                    session = request.getSession(true);
                }
                log.info("libraire: " + libraire);
            } else if (compte != null && compte.getRole().equals("ROLE_CLIENT")) {
                Client client = clientDAOImp.getParCompteId(compte.getCompteId());
                log.info("client: " + client);
            }
        } catch (SQLException e) {
            log.error("Erreur lors de la récupération du compte", e);
            response.sendRedirect("connexion.jsp?error=true");
            return;
        }
        //TODO: Verifier mdp hashee
        if (compte != null && compte.getPassword().equals(mdp)) {
            if (session == null) {
                session = request.getSession(true);
            }
            log.info("utilisateur: " + utilisateur + " compte: " + compte);
            session.setAttribute("utilisateur", utilisateur);
            session.setAttribute("role", compte.getRole()); //Définit le rôle de l'utilisateur
            if (compte.getRole().equals("ROLE_LIBRAIRE")) {
                response.sendRedirect("monCompteLibraire");
            }
            else if (compte.getRole().equals("ROLE_CLIENT")) {
                response.sendRedirect("monCompteClient");
            }
            log.info("role session :" + session.getAttribute("role"));
        } else if (compte == null) {
            // Rediriger vers la page d'erreur avec un code de statut HTTP
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Vous n'avez pas de compte.");
        }


//            // Définir le rôle de l'utilisateur
//            if ("lib1".equals(utilisateur) && "P@ssw0rd1".equals(mdp)) {
////                "ROLE_LIBRAIRE","ROLE_LIBRAIRE_ATTENTE","ROLE_CLIENT"
//                session.setAttribute("role", "ROLE_LIBRAIRE");
//                response.sendRedirect("moncomptelibraire.jsp");
//            } else {
//                session.setAttribute("role", "ROLE_CLIENT");
//                response.sendRedirect("moncompteclient.jsp");
//            }
//        } else {
//            // Rediriger vers la page de connexion avec un message d'erreur
//            response.sendRedirect("connexion.jsp?error=true");
//        }
    }


    @Override
    public void destroy() {
    }
}