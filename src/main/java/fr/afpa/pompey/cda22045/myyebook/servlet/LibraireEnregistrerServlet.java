package fr.afpa.pompey.cda22045.myyebook.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "enregistrerLibraireServlet", value = "/libraire-enregistrer")
public class LibraireEnregistrerServlet extends HttpServlet {
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
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
//        String rue = request.getParameter("rue");
//        String codePostal = request.getParameter("cp");
//        String ville = request.getParameter("ville");
//        System.out.println("nom: " +nom + " Prenom " + prenom + " email: " + email + " rue " + rue + " CP " + codePostal + " ville " + ville);
    }

    @Override
    public void destroy(){}

}

