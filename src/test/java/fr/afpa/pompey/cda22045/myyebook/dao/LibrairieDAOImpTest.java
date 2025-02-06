package fr.afpa.pompey.cda22045.myyebook.dao;

import fr.afpa.pompey.cda22045.myyebook.dao.comptedao.CompteDAOImp;
import fr.afpa.pompey.cda22045.myyebook.dao.librairedao.LibraireDAOImp;
import fr.afpa.pompey.cda22045.myyebook.model.Compte;
import fr.afpa.pompey.cda22045.myyebook.model.Libraire;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

@Slf4j
public class LibrairieDAOImpTest {

    LibraireDAOImp libraireDAOImp;
    CompteDAOImp compteDAOImp;

    @BeforeEach
    void setUp() {
        libraireDAOImp = new LibraireDAOImp();
        compteDAOImp = new CompteDAOImp();
    }

    @Test
    void getByIdValid() {
        try {
            Libraire libraire = libraireDAOImp.get(1);
            log.info(String.valueOf(libraire));
        } catch (SQLException e) {
            log.info("Exception SQL: " + e.getMessage() );
        }
    }

    @Test
    void getAllValid() {
        try {
            List<Libraire> libraires  = libraireDAOImp.getAll();
            log.info(libraires.toString());
        } catch (SQLException e) {
            log.info("Exception SQL: " + e.getMessage() );
        }
    }

    @Test
    void insertValid() {
        try {
            Compte compte = new Compte(1,"monlogin","motdepasseSecure1!","ROLE_CLIENT");

            Libraire libraire = new Libraire(compte,null,true,"Nomlibraire","PrenomLibraire");
            Integer id  = libraireDAOImp.insert(libraire);
            log.info("L'id de la libraire insere est "+ id);
        } catch (SQLException e) {
            log.info("Exception SQL: " + e.getMessage() );
        }
    }

    @Test
    void updateValid() {
        try {
            Libraire libraire = libraireDAOImp.get(3);
            log.info(String.valueOf(libraire));
            libraire.setNom("BLABLANOM");
            libraire.setPrenom("BLABLAPRENOM");
            Integer id  = libraireDAOImp.update(libraire);
            log.info("L'id de la libraire modifie est "+ id);
        } catch (SQLException e) {
            log.info("Exception SQL: " + e.getMessage() );
        }
    }


    @Test
    void deleteValid() {
        try {
            Integer id  = 2;
            libraireDAOImp.delete(id);
            log.info("L'id de la libraire "+ id + " a bien ete supprimer");
        } catch (SQLException e) {
            log.info("Exception SQL: " + e.getMessage() );
        }
    }
}
