package fr.afpa.pompey.cda22045.myyebook.servlet.libraire;

import fr.afpa.pompey.cda22045.myyebook.dao.librairedao.LibraireDAOImp;
import fr.afpa.pompey.cda22045.myyebook.model.Libraire;
import fr.afpa.pompey.cda22045.myyebook.utilitaires.Verification;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "enregistrerLibraireServlet", value = "/libraire-enregistrer")
public class LibraireEnregistrerServlet extends HttpServlet {
    LibraireDAOImp libraireDAOImp = new LibraireDAOImp();
    @Override
    public void init() {}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Récupérer l'url du site
        String currentURL = request.getRequestURL().toString();
        //Enregistre l'url dans la variable et envoye à la page JSP
        request.setAttribute("currentURL", currentURL);

        this.getServletContext().getRequestDispatcher("/JSP/page/libraire_enregistrer.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Récupérer les informations du formulaire
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        System.out.println("nom: " +nom + " Prenom " + prenom + " login " + login + " password " + password);

        //Verifier les informations passent au regex
        Verification.CHARACTER(nom);
        Verification.CHARACTER(prenom);



        // Enregistrer le libraire
        Libraire libraire = new Libraire(
                login,
                password,
                true,
                nom,
                prenom
        );

        // Enregistrer le libraire dans la base de données
        try {
            libraireDAOImp.insert(libraire);
            System.out.println("Libraire enregistré");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'enregistrement du libraire");
            throw new RuntimeException(e);
        }


        // Rediriger vers la page de liste des libraires
        response.sendRedirect(request.getContextPath() + "/ListeLibraire"+"?info=success");
    }

    @Override
    public void destroy(){}
}

