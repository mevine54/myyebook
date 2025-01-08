package fr.afpa.pompey.cda22045.myyebook.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import jakarta.servlet.http.HttpServlet;

@WebServlet(name = "enregistrerClientServlet", value = "/client-enregistrer")
public class ClientEnregistrerServlet extends HttpServlet {


    @Override
    public void init() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO : CHECK IF USER IS CONNECTED
        // if user connected redirect :  client -> /index.jsp | libraire -> "/libraire/:id_libraire/livre"


        // ELSE
        this.getServletContext().getRequestDispatcher("/JSP/page/clientenregistrer.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String utilisateur = request.getParameter("utilisateur");
        String mdp1 = request.getParameter("mdp1");
        String mdp2 = request.getParameter("mdp2");
        System.out.println(" nom: " + nom + ", prenom: " + prenom + ", utilisateur: " + utilisateur + ", email: " + email + ", mdp: " + mdp1 + ", mdp2: " + mdp2 );

        // TODO:  Verifier info et rediriger
    }

    @Override
    public void destroy() {
    }

}