package fr.afpa.pompey.cda22045.myyebook.servlet;

import fr.afpa.pompey.cda22045.myyebook.dao.auteurdao.AuteurDAOImpl;
import fr.afpa.pompey.cda22045.myyebook.dao.categoriedao.CategorieDAOImpl;
import fr.afpa.pompey.cda22045.myyebook.dao.livredao.LivreDAO;
import fr.afpa.pompey.cda22045.myyebook.dao.livredao.LivreDAOImpl;
import fr.afpa.pompey.cda22045.myyebook.model.Auteur;
import fr.afpa.pompey.cda22045.myyebook.model.Categorie;
import fr.afpa.pompey.cda22045.myyebook.model.Livre;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 2,
        maxRequestSize = 1024 * 1024 * 2)
@WebServlet(name = "LivreModificationServlet", value = "/LivreModification")
public class LivreModificationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final String UPLOAD_DIRECTORY= System.getProperty("user.dir");
    @Override
    public void init() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        Livre livre;
        if (idStr != null && idStr.matches("\\d+")) {
            int id = Integer.parseInt(idStr);
            LivreDAOImpl livreDAOImpl = new LivreDAOImpl();
            try {
                livre = livreDAOImpl.get(id);
                if (livre != null) {
                    CategorieDAOImpl categorieDAOImpl = new CategorieDAOImpl();
                    AuteurDAOImpl auteurDAOImpl = new AuteurDAOImpl();
                    List<Categorie> categorieList = categorieDAOImpl.getAll();
                    List<Auteur> auteurList = auteurDAOImpl.getAll();
                    request.setAttribute("categorieList", categorieList);
                    request.setAttribute("livre", livre);
                    request.setAttribute("auteurList", auteurList);
                    this.getServletContext().getRequestDispatcher("/JSP/page/modification_livre.jsp").forward(request, response);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        response.sendRedirect(request.getContextPath() + "/accueil");
//        this.getServletContext().getRequestDispatcher("/accueil").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String auteur = request.getParameter("auteur");
        String categorie = request.getParameter("categorie");
        String resume = request.getParameter("resume");
        String img = request.getParameter("img");


        //TODO:    Gerer le chargement d'image
        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
//        String uploadPath = getServletContext().getContextPath() + File.separator + UPLOAD_DIRECTORY;
        ServletContext servletContext = this.getServletConfig().getServletContext();
        File repository = (File) servletContext.getAttribute("jakarta.servlet.context.tempdir");
        System.out.println(repository.getAbsolutePath());
//        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
        System.out.println(uploadPath);
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdir();
        for (Part part : request.getParts()) {
            String fileName = part.getSubmittedFileName();
            part.write(uploadPath + File.separator + "qsdsqdqsd.jpg");
        }

        System.out.println("nom: " + nom + ", auteur: " + auteur + ", categorie: " + categorie  + ", resume: " + resume+ ", img: " + img);
    }

    @Override
    public void destroy() {
    }
//    private String getFileName(Part part) {
//        for (String content : part.getHeader("content-disposition").split(";")) {
//            if (content.trim().startsWith("filename"))
//                return content.substring(content.indexOf("=") + 2, content.length() - 1);
//        }
//        return Constants.DEFAULT_FILENAME;
//    }
}

