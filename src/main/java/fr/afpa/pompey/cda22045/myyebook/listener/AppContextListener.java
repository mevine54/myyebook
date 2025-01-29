package fr.afpa.pompey.cda22045.myyebook.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

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
        sce.getServletContext().setAttribute("dossierAuteur",uploadDirImgAuteur.getAbsolutePath() +  File.separator);
        sce.getServletContext().setAttribute("dossierCouverture",uploadDirCouverture.getAbsolutePath()  +  File.separator);
        File destinationDir = new File(sce.getServletContext().getRealPath("") + File.separator + "assets" + File.separator + "upload");
        // Cr�er le dossier de destination s'il n'existe pas
        if (!destinationDir.exists()) {
            destinationDir.mkdirs();
        }

        // Copier les fichiers
        try {
            copyDirectory(uploadParent, destinationDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Copier les fichiers avec les répertoires inversés
        File destinationDir = new File(new File(sce.getServletContext().getRealPath("")).getParentFile() + File.separator + "uploads_myebook");
        File sourceDir = new File(sce.getServletContext().getRealPath("") + File.separator + "assets" + File.separator + "upload");

        try {
            copyDirectory(sourceDir, destinationDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ServletContextListener.super.contextDestroyed(sce);
    }

    // M�thode pour copier les fichiers d'un dossier � un autre
    private void copyDirectory(File sourceDir, File destDir) throws IOException {
        if (sourceDir.isDirectory()) {
            if (!destDir.exists()) {
                destDir.mkdir();
            }
            String[] children = sourceDir.list();
            for (String child : children) {
                copyDirectory(new File(sourceDir, child), new File(destDir, child));
            }
        } else {
            Files.copy(sourceDir.toPath(), destDir.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
