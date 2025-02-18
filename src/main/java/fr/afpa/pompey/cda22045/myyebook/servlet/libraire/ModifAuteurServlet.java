package fr.afpa.pompey.cda22045.myyebook.servlet.libraire;

import fr.afpa.pompey.cda22045.myyebook.dao.auteurdao.AuteurDAOImpl;
import fr.afpa.pompey.cda22045.myyebook.model.Auteur;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;


@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 2,
        maxRequestSize = 1024 * 1024 * 2)
@WebServlet(name = "ModifAuteur", value = "/ModifAuteur")
@Slf4j
public class ModifAuteurServlet extends HttpServlet {

    @Override
    public void init() {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Récupérer l'url du site
        String currentURL = request.getRequestURL().toString();
        //Enregistre l'url dans la variable et envoye à la page JSP
        request.setAttribute("currentURL", currentURL);

        this.getServletContext().getRequestDispatcher("/JSP/page/modifAuteur.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupération des paramètres

        String idStr = request.getParameter("id");
        String nomStr = request.getParameter("nom");
        String prenomStr = request.getParameter("prenom");
        Part imgPart = request.getPart("image");
        AuteurDAOImpl auteurDAOImpl = new AuteurDAOImpl();

        // Enregistrement de l'image
        String fileName = imgPart.getSubmittedFileName();
        log.info("fichier uploade : {}", fileName);
        String uuid = UUID.randomUUID().toString();
        String fileExtension = fileName.substring(fileName.lastIndexOf('.'));
        String newFileName = uuid + fileExtension;
        File fichierACreeHorsTomcat = new File(getServletContext().getAttribute("dossierAuteur") + newFileName);
        File fichierAcreerDansTomcat = new File(getServletContext().getRealPath("") + File.separator + "assets" + File.separator + "upload" + File.separator + "img_auteur" + File.separator + newFileName);
        log.info("fichier cree: {}", fichierACreeHorsTomcat);
        imgPart.write(fichierACreeHorsTomcat.getAbsolutePath());
        imgPart.write(fichierAcreerDansTomcat.getAbsolutePath());
        if (ImageIO.read(fichierACreeHorsTomcat) != null) {
            log.info(fichierACreeHorsTomcat.getAbsolutePath());

            try {
                Auteur auteur = auteurDAOImpl.get(Integer.valueOf(idStr));
                auteur.setNom(nomStr);
                auteur.setPrenom(prenomStr);
                auteur.setPhoto(newFileName);
                auteurDAOImpl.update(auteur);
                response.sendRedirect(request.getContextPath() + "/ListeAuteur?info=successUpdate");
            } catch (SQLException e) {
                response.sendRedirect(request.getContextPath() + "/ModifAuteur?info=errorDB");
                throw new RuntimeException(e);
            }
        } else {
            log.warn("Ce n'est pas une image valide");
            fichierACreeHorsTomcat.delete();
        }
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.warn("delete");
        AuteurDAOImpl auteurDAOImpl = new AuteurDAOImpl();

        try {
            auteurDAOImpl.delete(Integer.parseInt(req.getParameter("id")));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void destroy() {

    }
}