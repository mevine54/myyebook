package fr.afpa.pompey.cda22045.myyebook.servlet;

import fr.afpa.pompey.cda22045.myyebook.dao.auteurdao.AuteurDAOImpl;
import fr.afpa.pompey.cda22045.myyebook.model.Auteur;
import fr.afpa.pompey.cda22045.myyebook.model.Categorie;
import fr.afpa.pompey.cda22045.myyebook.model.Libraire;
import fr.afpa.pompey.cda22045.myyebook.utilitaires.Verification;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "CreeUnAuteurServlet", value = "/CreeUnAuteur")
public class CreeUnAuteurServlet extends HttpServlet {
    private AuteurDAOImpl auteurDAOImpl;

    @Override
    public void init() {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Récupérer l'url du site
        String currentURL = request.getRequestURL().toString();
        //Enregistre l'url dans la variable et envoye à la page JSP
        request.setAttribute("currentURL", currentURL);

        this.getServletContext().getRequestDispatcher("/JSP/page/creeUnAuteur.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Récupérer les informations du formulaire
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        System.out.println("nom: " +nom + " Prenom " + prenom);

        try{
            Verification.CHARACTER(nom);
            Verification.CHARACTER(prenom);
            Auteur auteur = new Auteur(nom, prenom, "image.png");
            try {
                auteurDAOImpl.insert(auteur);
                response.sendRedirect(request.getContextPath() + "/ListeCategorie"+"?info=success");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }catch (Exception e){
            response.sendRedirect(request.getContextPath() + "/CreeUneCategorie"+"?info=error");
        }

    }

    @Override
    public void destroy() {

    }
}