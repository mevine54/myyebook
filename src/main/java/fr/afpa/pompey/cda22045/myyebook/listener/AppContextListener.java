package fr.afpa.pompey.cda22045.myyebook.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

@WebListener
@Slf4j
public class AppContextListener implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Initilisation des dossiers de sauvegardes photo auteur et couverture
        File uploadParent = new File(new File(sce.getServletContext().getRealPath("")).getParentFile() + File.separator + "uploads_myebook");
        if (!uploadParent.exists()) uploadParent.mkdir();
        File uploadDirCouverture = new File(uploadParent +  File.separator + "couverture");
        File uploadDirImgAuteur = new File(uploadParent + File.separator + "img_auteur");
        if (!uploadDirImgAuteur.exists()) uploadDirImgAuteur.mkdir();
        if (!uploadDirCouverture.exists()) uploadDirCouverture.mkdir();
        log.info("dosier auteur: {}", uploadDirImgAuteur.getAbsolutePath());
        log.info("dosier couverture: {}", uploadDirCouverture.getAbsolutePath());
        sce.getServletContext().setAttribute("dossierAuteur",uploadDirImgAuteur.getAbsolutePath());
        sce.getServletContext().setAttribute("dossierCouverture",uploadDirCouverture.getAbsolutePath());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
    }
}
