package fr.afpa.pompey.cda22045.myyebook.servlet;

import fr.afpa.pompey.cda22045.myyebook.connectionbdd.DatabaseConnection;
import fr.afpa.pompey.cda22045.myyebook.dao.categoriedao.CategorieDAOImpl;
import fr.afpa.pompey.cda22045.myyebook.model.Categorie;
import fr.afpa.pompey.cda22045.myyebook.securite.CSRFTokenUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ListeCategorieServlet", value = "/ListeCategorie")
@Slf4j
public class ListeCategorieServlet extends HttpServlet {

    @Override
    public void init() {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategorieDAOImpl categorieDAOImpl = new CategorieDAOImpl();
//        HttpSession session = request.getSession(true);
//        session.setAttribute("csrfToken", CSRFTokenUtil.generateCSRFToken());
        log.info("listecategorie ");

        try {
            List<Categorie> categories = categorieDAOImpl.getAll();
            log.info("categories: " + categories);
            request.setAttribute("categories", categories);
            this.getServletContext().getRequestDispatcher("/JSP/page/listeCategorie.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Cannot obtain categories from DB", e);
        }


        //Récupérer l'url du site
        String currentURL = request.getRequestURL().toString();
        //Enregistre l'url dans la variable et envoye à la page JSP
        request.setAttribute("currentURL", currentURL);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Récupère les paramètres après l'envoi du formulaire
//        String id = request.getParameter("id");
//        String action = request.getParameter("action");
//        if("modifier".equals(action)){
//            modifier(request, response, id);
//        }else if("supprimer".equals(action)){
//            supprimer(request, response, id);
//        }
    }

    @Override
    public void destroy() {

    }

//    private void modifier(HttpServletRequest request, HttpServletResponse response, String id) throws ServletException, IOException {
//        CategorieDAOImpl categorieDAOImpl = new CategorieDAOImpl();
////        HttpSession session = request.getSession(true);
////        session.setAttribute("csrfToken", CSRFTokenUtil.generateCSRFToken());
//        try {
//            Categorie categories = categorieDAOImpl.get(Integer.valueOf(id));
//            if(categories == null){
//                response.sendRedirect(request.getContextPath() + "/ListeCategorie"+"?info=errorDB");
//            }else{
//                request.setAttribute("categories", categories);
//                //Redirection vers la page ModifCategorie
//                response.sendRedirect(request.getContextPath() + "/ModifCategorie?id="+id);
//
//
//            }
//        } catch (SQLException e) {
//            throw new ServletException("Cannot obtain categories from DB", e);
//        }
//    }
//
//    private void supprimer(HttpServletRequest request, HttpServletResponse response, String id) throws ServletException, IOException {
//
//        CategorieDAOImpl categorieDAOImpl = new CategorieDAOImpl();
//        try {
//            categorieDAOImpl.delete(Integer.valueOf(id));
//            response.sendRedirect(request.getContextPath() + "/ListeCategorie"+"?info=successDelete");
//        } catch (SQLException e) {
//            throw new ServletException("Cannot obtain categories from DB", e);
//        }
//    }
}