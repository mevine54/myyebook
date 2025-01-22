package fr.afpa.pompey.cda22045.myyebook.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "CreeUneCategorieServlet", value = "/CreeUneCategorie")
public class CreeUneCategorieServlet extends HttpServlet {

    @Override
    public void init() {


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Récupérer l'url du site
        String currentURL = request.getRequestURL().toString();
        //Enregistre l'url dans la variable et envoye à la page JSP
        request.setAttribute("currentURL", currentURL);

        this.getServletContext().getRequestDispatcher("/JSP/page/creeUneCategorie.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void destroy() {

    }
}