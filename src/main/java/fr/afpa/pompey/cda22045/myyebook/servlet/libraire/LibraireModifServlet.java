package fr.afpa.pompey.cda22045.myyebook.servlet.libraire;

import java.io.IOException;
import java.sql.SQLException;

import fr.afpa.pompey.cda22045.myyebook.dao.categoriedao.CategorieDAOImpl;
import fr.afpa.pompey.cda22045.myyebook.dao.comptedao.CompteDAO;
import fr.afpa.pompey.cda22045.myyebook.dao.comptedao.CompteDAOImp;
import fr.afpa.pompey.cda22045.myyebook.dao.librairedao.LibraireDAOImp;
import fr.afpa.pompey.cda22045.myyebook.model.Categorie;
import fr.afpa.pompey.cda22045.myyebook.model.Compte;
import fr.afpa.pompey.cda22045.myyebook.model.Libraire;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@WebServlet(name = "LibraireModifServlet", value = "/ModifLibraire")
@Slf4j
public class LibraireModifServlet extends HttpServlet {

    @Override
    public void init() {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        Libraire libraires;
        if (idStr != null && idStr.matches("\\d+")) {
            int id = Integer.parseInt(idStr);
            LibraireDAOImp libraireDAOImp = new LibraireDAOImp();
            try {
                libraires = libraireDAOImp.get(id);
                if (libraires != null) {
                    Libraire libraire = libraireDAOImp.get(id);

                    request.setAttribute("libraire", libraire);
                    this.getServletContext().getRequestDispatcher("/JSP/page/modifLibraire.jsp").forward(request, response);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/accueil");

        }
//        this.getServletContext().getRequestDispatcher("/accueil").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO: Implement
        // Récupérer les paramètres du formulaire
        log.info("libraire info post");
        String idLibraire = request.getParameter("idLibraire");
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String idCompte = request.getParameter("idCompte");
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (nom == null || prenom == null || login == null || password == null) {
            response.sendRedirect(request.getContextPath() + "/ModifLibraire?info=error");
            this.getServletContext().getRequestDispatcher("/JSP/page/modifLibraire.jsp").forward(request, response);
        } else {
            CompteDAO compteDAOImpl = new CompteDAOImp();
            LibraireDAOImp libraireDAOImp = new LibraireDAOImp();
            try {
                Compte compte = compteDAOImpl.get(Integer.parseInt(idCompte));

                if (compte != null) {
                    Libraire libraire = libraireDAOImp.get(Integer.parseInt(idLibraire));
                    libraire.setNom(nom);
                    libraire.setPrenom(prenom);

//                    Libraire libraire = new Libraire(
//                            compte,
//                            Integer.parseInt(idLibraire),
//                            login,
//                            password,
//                            true,
//                            nom,
//                            prenom
//                    );

                    libraireDAOImp.update(libraire);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            response.sendRedirect(request.getContextPath() + "/ListeLibraire?info=successModif");
        }


        // Valider et traiter les paramètres
//        if (nom != null && !nom.isEmpty() && prenom != null && !prenom.isEmpty() && email != null && !email.isEmpty()
//                && rue != null && !rue.isEmpty() && codePostal != null && !codePostal.isEmpty() && ville != null
//                && !ville.isEmpty()) {
//            // Afficher les paramètres dans la console
//            log.info("Nom: " +nom);
//            log.info("Prénom: " +prenom);
//            log.info("email: " +email);
//            log.info("rue: " +rue);
//            log.info("codePostal: " +codePostal);
//            log.info("ville: " +ville);
//
//            // Rediriger vers une page de confirmation ou afficher une réponse
////            response.sendRedirect(request.getContextPath() + "/libraireinfo.jsp");
//        } else {
//            // Gérer lese erreurs de validation
//            request.setAttribute("erreur", "Veuillez remplir tous les champs.");
////            this.getServletContext().getRequestDispatcher("/JSP/page/libraireinfo.jsp").forward(request, response);
//        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.warn("delete");
        HttpSession session = req.getSession();
        String csrfSession = (String) session.getAttribute("csrfToken");
        String csrfReq = (String) req.getParameter("csrf");
        log.info("csrfSession: " + csrfSession);
        log.info("csrfReq: " + csrfReq);

        LibraireDAOImp libraireDAOImp = new LibraireDAOImp();
        if (csrfReq.equals(csrfSession)) {
            try {
                libraireDAOImp.delete(Integer.parseInt(req.getParameter("id")));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            log.warn("csrf different");
        }
    }

    @Override
    public void destroy() {

    }
}