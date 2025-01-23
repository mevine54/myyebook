package fr.afpa.pompey.cda22045.myyebook.servlet;

import fr.afpa.pompey.cda22045.myyebook.dao.categoriedao.CategorieDAOImpl;
import fr.afpa.pompey.cda22045.myyebook.model.Categorie;
import fr.afpa.pompey.cda22045.myyebook.utilitaires.Verification;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ModifCategorieServlet", value = "/ModifCategorie")
public class ModifCategorieServlet extends HttpServlet {
    @Override
    public void init() {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Récupérer l'url du site
        String currentURL = request.getRequestURL().toString();
        //Enregistre l'url dans la variable et envoye à la page JSP
        request.setAttribute("currentURL", currentURL);

        this.getServletContext().getRequestDispatcher("/JSP/page/modifCategorie.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategorieDAOImpl categorieDAOImpl = new CategorieDAOImpl();
        String nomCategorie = request.getParameter("nomCategorie");

        try{
            Verification.CHARACTER(nomCategorie);
            Categorie categorie = new Categorie(nomCategorie);
            try {
                categorieDAOImpl.update(categorie);
                response.sendRedirect(request.getContextPath() + "/ListeCategorie"+"?info=successUpdate");
            } catch (SQLException e) {
                response.sendRedirect(request.getContextPath() + "/CreeUneCategorie"+"?info=errorDB");
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