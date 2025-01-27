package fr.afpa.pompey.cda22045.myyebook.servlet.client;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "ClientListeEmprunts", value = "/mesemprunts")
public class ClientListeEmprunts extends HttpServlet {

    @Override
    public void init() {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //TODO: Si l'utilisateur n'est pas connecté, rediriger vers la page de connexion

        //Récupérer l'url du site
        String currentURL = request.getRequestURL().toString();
        //Enregistre l'url dans la variable et envoye à la page JSP
        request.setAttribute("currentURL", currentURL);

        //TODO : Faire une requête pour récupérer les emprunts du client
        // et à afficher dans la page "Mes emprunts"
        this.getServletContext().getRequestDispatcher("/JSP/page/clientListeEmprunts.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void destroy() {

    }
}