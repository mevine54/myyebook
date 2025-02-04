package fr.afpa.pompey.cda22045.myyebook.dao;

import fr.afpa.pompey.cda22045.myyebook.dao.auteurdao.AuteurDAOImpl;
import fr.afpa.pompey.cda22045.myyebook.model.Auteur;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

@Slf4j
public class AuteurDAOImpTest {

    AuteurDAOImpl auteurDAOImp = new AuteurDAOImpl();

    @BeforeEach
    void setUp() {
    }

    @Test
    void getByIdValid() {
        try {
            Auteur auteur = auteurDAOImp.get(1);
            log.info(String.valueOf(auteur));
        } catch (SQLException e) {
            log.info("Exception SQL: " + e.getMessage() );
        }
    }

    @Test
    void getAllValid() {
        try {
            List<Auteur>  auteurs  = auteurDAOImp.getAll();
            log.info(auteurs.toString());
        } catch (SQLException e) {
            log.info("Exception SQL: " + e.getMessage() );
        }
    }

    @Test
    void insertValid() {
        try {
            Auteur auteur = new Auteur(null,"nomAuteur","prenomAuteur","/photoAuteur.jpg");
            Integer id  = auteurDAOImp.insert(auteur);
            log.info("L'id de l'auteur insere est "+ id);
        } catch (SQLException e) {
            log.info("Exception SQL: " + e.getMessage() );
        }
    }
//
    @Test
    void updateValid() {
        try {
            Auteur auteur = auteurDAOImp.get(12);
            log.info(String.valueOf(auteur));
            auteur.setNom("BLABLANom");
            auteur.setPrenom("BLABLAPrenom");
            auteur.setPhoto("/BLABLAPhoto.jpg");
            Integer id  = auteurDAOImp.update(auteur);
            log.info("L'id de l' auteur modifie est "+ id);
        } catch (SQLException e) {
            log.info("Exception SQL: " + e.getMessage() );
        }
    }


    @Test
    void deleteValid() {
        try {
            Integer id  = 12;
            auteurDAOImp.delete(id);
            log.info("L'id de l 'auteur "+ id + " a bien ete supprimer");
        } catch (SQLException e) {
            log.info("Exception SQL: " + e.getMessage() );
        }
    }
}
