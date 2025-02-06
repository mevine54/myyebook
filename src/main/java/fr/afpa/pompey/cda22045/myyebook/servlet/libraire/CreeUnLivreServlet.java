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
@WebServlet(name = "CreeUnLivreServlet", value = "/CreeUnLivre")
@Slf4j
public class CreeUnLivreServlet extends HttpServlet {

    @Override
    public void init() {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Récupérer l'url du site
        String currentURL = request.getRequestURL().toString();
        //Enregistre l'url dans la variable et envoye à la page JSP
        request.setAttribute("currentURL", currentURL);
        try {
            CategorieDAOImpl categorieDAOImpl = new CategorieDAOImpl();
            AuteurDAOImpl auteurDAOImpl = new AuteurDAOImpl();
            List<Categorie> categorieList = null;
            categorieList = categorieDAOImpl.getAll();
            List<Auteur> auteurList = auteurDAOImpl.getAll();
            request.setAttribute("categorieList", categorieList);
            request.setAttribute("auteurList", auteurList);
            this.getServletContext().getRequestDispatcher("/JSP/page/creeUnLivre.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recup des parametres
        String nomStr = request.getParameter("nom");
        Integer auteurId = Integer.valueOf(request.getParameter("auteur"));
        Integer categorieId = Integer.valueOf(request.getParameter("categorie"));
        boolean estEnavant = Boolean.parseBoolean(request.getParameter("estEnavant"));
        String resumeStr = request.getParameter("resume");
        Part imgPart = request.getPart("img");
        String idStr = request.getParameter("id");

        log.info(nomStr + " " + auteurId + " " + categorieId + " " + resumeStr + " " + imgPart.getSubmittedFileName());
        //TODO:    Gerer le chargement d'image

        // Enregistrement de l'image
        String fileName = imgPart.getSubmittedFileName();
        log.info("fichier uploade : {}", fileName);
        String uuid = UUID.randomUUID().toString();
        String fileExtension = fileName.substring(fileName.lastIndexOf('.'));
        String newFileName = uuid + fileExtension;
        File fichierACree = new File(getServletContext().getAttribute("dossierCouverture") + newFileName);
        imgPart.write(fichierACree.getAbsolutePath());
        if (ImageIO.read(fichierACree) != null) {
            log.info(fichierACree.getAbsolutePath());
            LivreDAOImpl livreDAOImpl = new LivreDAOImpl();
            AuteurDAOImpl auteurDAOImpl = new AuteurDAOImpl();
            CategorieDAOImpl categorieDAOImpl = new CategorieDAOImpl();
            try {
                // Creation d'un livre
                Categorie categorie = categorieDAOImpl.get(categorieId);
                Auteur auteur = auteurDAOImpl.get(auteurId);
                Livre livre = new Livre(
                        null,
                        nomStr,
                        resumeStr,
                        newFileName,
                        estEnavant,
                        auteur,
                        categorie
                );
                livreDAOImpl.insert(livre);
                response.sendRedirect(request.getContextPath() + "/ListeLivre?info=success");
            } catch (SQLException e) {
                response.sendRedirect(request.getContextPath() + "/CreeUnLivre?info=errorDB");
                log.warn("Erreur lors de la création du livre");
                throw new RuntimeException(e);
            }

        } else {
            log.warn("Ce fichier n'est pas une image valide");
            fichierACree.delete();
        }
    }


    @Override
    public void destroy() {

    }
}
