package fr.afpa.pompey.cda22045.myyebook.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "LivreModificationServlet", value = "/LivreModification")
public class LivreModificationServlet extends HttpServlet {
    @Override
    public void init() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Récupérer l'url du site
        String currentURL = request.getRequestURL().toString();
        //Enregistre l'url dans la variable et envoye à la page JSP
        request.setAttribute("currentURL", currentURL);

        this.getServletContext().getRequestDispatcher("/JSP/page/modification_livre.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String auteur = request.getParameter("auteur");
        String categorie = request.getParameter("categorie");
        String resume = request.getParameter("resume");

        /*
            Gerer la chargment d'image
        */

        System.out.println("nom: " + nom + ", auteur: " + auteur + ", categorie: " + categorie + ", resume: " + resume);
    }

    @Override
    public void destroy() {
    }

}

