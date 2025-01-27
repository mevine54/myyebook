package fr.afpa.pompey.cda22045.myyebook.servlet.libraire;

import fr.afpa.pompey.cda22045.myyebook.dao.auteurdao.AuteurDAOImpl;
import fr.afpa.pompey.cda22045.myyebook.dao.categoriedao.CategorieDAOImpl;
import fr.afpa.pompey.cda22045.myyebook.dao.livredao.LivreDAOImpl;
import fr.afpa.pompey.cda22045.myyebook.model.Auteur;
import fr.afpa.pompey.cda22045.myyebook.model.Categorie;
import fr.afpa.pompey.cda22045.myyebook.model.Livre;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 2,
        maxRequestSize = 1024 * 1024 * 2)
@WebServlet(name = "LivreModificationServlet", value = "/LivreModification")
@Slf4j
public class LivreModificationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    //    private final String UPLOAD_DIRECTORY= System.getProperty("user.dir");
    private final String UPLOAD_DIRECTORY = "uploads";

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
        } else {
            response.sendRedirect(request.getContextPath() + "/accueil");

        }
//        this.getServletContext().getRequestDispatcher("/accueil").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recup des parametres
        String nom = request.getParameter("nom");
        String auteur = request.getParameter("auteur");
        String categorie = request.getParameter("categorie");
        String resume = request.getParameter("resume");
        Part imgPart = request.getPart("img");

        System.out.println(nom + " " + auteur + " " + categorie + " " + resume + " " + imgPart.getSubmittedFileName());
        //TODO:    Gerer le chargement d'image

        // Enregistrement de l'image
        String fileName = imgPart.getSubmittedFileName();
        log.info("fichier uploade : {}", fileName);
        String uuid = UUID.randomUUID().toString();
        String fileExtension = fileName.substring(fileName.lastIndexOf('.'));
        String newFileName = uuid + fileExtension;
        File fichierACree = new File(getServletContext().getAttribute("dossierCouverture") + File.separator + newFileName);
        if (ImageIO.read(fichierACree) != null) {
            imgPart.write(fichierACree.getAbsolutePath());
            log.info(fichierACree.getAbsolutePath());
        } else {
            log.warn("Ce fichier n'est pas une image valide");
        }
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.warn("delete");
    }

    @Override
    public void destroy() {
    }

}

