package fr.afpa.pompey.cda22045.myyebook.servlet.libraire;

import fr.afpa.pompey.cda22045.myyebook.dao.categoriedao.CategorieDAOImpl;
import fr.afpa.pompey.cda22045.myyebook.model.Categorie;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ModifCategorieServlet", value = "/ModifCategorie")
@Slf4j
public class ModifCategorieServlet extends HttpServlet {
    @Override
    public void init() {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        HttpSession session = request.getSession(false);
        Categorie categories;
        if (idStr != null && idStr.matches("\\d+")) {
            int id = Integer.parseInt(idStr);
            CategorieDAOImpl categorieDAOImpl = new CategorieDAOImpl();
            try {
//                if ( session!=null ){
//                    request.setAttribute("csrfToken",session.getAttribute("csrfToken"));
//                }
                categories = categorieDAOImpl.get(id);
                if (categories != null) {
                    Categorie categorie = categorieDAOImpl.get(id);

                    request.setAttribute("categories", categorie);
                    this.getServletContext().getRequestDispatcher("/JSP/page/modifCategorie.jsp").forward(request, response);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/accueil");

        }

        //Récupérer l'url du site
        // String currentURL = request.getRequestURL().toString();
        //Enregistre l'url dans la variable et envoye à la page JSP
        // request.setAttribute("currentURL", currentURL);

        // this.getServletContext().getRequestDispatcher("/JSP/page/modifCategorie.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nomCategorie = request.getParameter("nomCategorie");
        String id = request.getParameter("id");

        //Verif si id existe dans la table
        CategorieDAOImpl categorieDAOImpl = new CategorieDAOImpl();
        if(nomCategorie == null || nomCategorie.isEmpty()){
            response.sendRedirect(request.getContextPath() + "/ModifCategorie"+"?info=error");
        }
        try {
            Categorie categorie1 = categorieDAOImpl.get(Integer.parseInt(id));
            if(categorie1 == null){
//                response.sendRedirect(request.getContextPath() + "/accueil");
            }else{
                Categorie categorie = new Categorie(
                        Integer.parseInt(id),
                        nomCategorie
                );
                categorieDAOImpl.update(categorie);
//                response.sendRedirect(request.getContextPath() + "/ListeCategorie+"+"?info=successUpdate");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CategorieDAOImpl categorieDAOImpl = new CategorieDAOImpl();
        try {
            categorieDAOImpl.delete(Integer.parseInt(req.getParameter("id")));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy() {

    }
}