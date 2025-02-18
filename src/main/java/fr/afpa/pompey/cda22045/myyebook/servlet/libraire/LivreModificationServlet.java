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
import jakarta.servlet.http.*;
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
        String nomStr = request.getParameter("nom");
        Integer auteurId = Integer.valueOf(request.getParameter("auteur"));
        Integer categorieId = Integer.valueOf(request.getParameter("categorie"));
        boolean estEnavant = Boolean.parseBoolean(request.getParameter("estEnavant"));
        String resumeStr = request.getParameter("resume");
        String quantiteStr = request.getParameter("quantite");
        Part imgPart = request.getPart("img");
        String idStr = request.getParameter("id");

        log.info(nomStr + " " + auteurId + " " + categorieId + " " + resumeStr + " " + imgPart.getSubmittedFileName());

        // Enregistrement de l'image
        String fileName = imgPart.getSubmittedFileName();
        log.info("fichier uploade : {}", fileName);
        String uuid = UUID.randomUUID().toString();
        String fileExtension = fileName.substring(fileName.lastIndexOf('.'));
        String newFileName = uuid + fileExtension;
        File fichierACreeHorsTomcat = new File(getServletContext().getAttribute("dossierCouverture") + newFileName);
        File fichierAcreerDansTomcat = new File(getServletContext().getRealPath("") + File.separator + "assets" + File.separator + "upload" + File.separator + "couverture" + File.separator + newFileName);
        imgPart.write(fichierACreeHorsTomcat.getAbsolutePath());
        imgPart.write(fichierAcreerDansTomcat.getAbsolutePath());
        if (ImageIO.read(fichierACreeHorsTomcat) != null) {
            log.info(fichierACreeHorsTomcat.getAbsolutePath());
            LivreDAOImpl livreDAOImpl = new LivreDAOImpl();
            AuteurDAOImpl auteurDAOImpl = new AuteurDAOImpl();
            CategorieDAOImpl categorieDAOImpl = new CategorieDAOImpl();
            try {
                Categorie categorie = categorieDAOImpl.get(categorieId);
                Auteur auteur = auteurDAOImpl.get(auteurId);
                // Modification d'un livre
                Livre livre = livreDAOImpl.get(Integer.valueOf(idStr));
                livre.setTitre(nomStr);
                livre.setEstEnAvant(estEnavant);
                livre.setAuteur(auteur);
                livre.setCategorie(categorie);
                livre.setResume(resumeStr);
                livre.setImage(newFileName);
                livre.setQuantite(Integer.parseInt(quantiteStr));
                log.info(" Quantite : {} ",quantiteStr);
                livreDAOImpl.update(livre);
                response.sendRedirect(request.getContextPath() + "/ListeLivre?info=successUpdate");
            } catch (SQLException e) {
                response.sendRedirect(request.getContextPath() + "/LivreModification?info=errorDB");
                log.warn("Erreur lors de la modification du livre");
                throw new RuntimeException(e);
            }
        } else {
            log.warn("Ce fichier n'est pas une image valide");
            fichierACreeHorsTomcat.delete();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LivreDAOImpl livreDAOImpl = new LivreDAOImpl();
        try {
            livreDAOImpl.delete(Integer.parseInt(req.getParameter("id")));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void destroy() {
    }

}

