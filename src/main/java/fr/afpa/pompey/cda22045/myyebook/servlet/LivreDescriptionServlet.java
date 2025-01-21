package fr.afpa.pompey.cda22045.myyebook.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "LivreDescriptionServlet", value = "/livre")
public class LivreDescriptionServlet extends HttpServlet {

    @Override
    public void init() {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupération de l'id du livre
//        String id = request.getParameter("id");
//
//        // Vérifie si l'id est null
//        if (id == null) {
//            // Redirection vers la page d'accueil
//            this.getServletContext().getRequestDispatcher("/accueil").forward(request, response);
//        }

        //TODO: Faire une requête pour récupérer les informations du livre

        //TODO: Récupère le id catégories du livre

        //TODO : Faire une requête pour récupérer les livres de la même catégorie
        // et à afficher dans la page "Les livres similaire"




        //redirection vers la page JSP de description du livre
        this.getServletContext().getRequestDispatcher("/JSP/page/livredescription.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void destroy() {

    }
}