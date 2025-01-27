package fr.afpa.pompey.cda22045.myyebook.servlet.libraire;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "monCompteLibraireSevlet", value = "/monCompteLibraire")
public class monCompteLibraireServlet extends HttpServlet {

    @Override
    public void init() {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        //Récupérer l'url du site
        String currentURL = request.getRequestURL().toString();
        //Enregistre l'url dans la variable et envoye à la page JSP
        request.setAttribute("currentURL", currentURL);

        this.getServletContext().getRequestDispatcher("/JSP/page/libraireinfo.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO: Implement
        // Récupérer les paramètres du formulaire
        System.out.println("libraire info post");
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String rue = request.getParameter("rue");
        String codePostal = request.getParameter("codePostal");
        String ville = request.getParameter("ville");

        System.out.println("Nom: " + nom);
        System.out.println("Prénom: " + prenom);
        System.out.println("email: " + email);
        System.out.println("rue: " + rue);
        System.out.println("codePostal: " + codePostal);
        System.out.println("ville: " + ville);


        // Valider et traiter les paramètres
//        if (nom != null && !nom.isEmpty() && prenom != null && !prenom.isEmpty() && email != null && !email.isEmpty()
//                && rue != null && !rue.isEmpty() && codePostal != null && !codePostal.isEmpty() && ville != null
//                && !ville.isEmpty()) {
//            // Afficher les paramètres dans la console
//            System.out.println("Nom: " +nom);
//            System.out.println("Prénom: " +prenom);
//            System.out.println("email: " +email);
//            System.out.println("rue: " +rue);
//            System.out.println("codePostal: " +codePostal);
//            System.out.println("ville: " +ville);
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
    public void destroy() {

    }
}