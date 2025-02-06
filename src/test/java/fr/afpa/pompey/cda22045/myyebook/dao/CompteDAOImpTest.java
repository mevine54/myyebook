package fr.afpa.pompey.cda22045.myyebook.dao;

import fr.afpa.pompey.cda22045.myyebook.dao.comptedao.CompteDAOImp;
import fr.afpa.pompey.cda22045.myyebook.model.Compte;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;
@Slf4j
public class CompteDAOImpTest {

    CompteDAOImp compteDAOImp;

    @BeforeEach
    void setUp() {
        compteDAOImp = new CompteDAOImp();
    }

    @Test
    void getByIdValid() {
        try {
            Compte compte = compteDAOImp.get(1);
            log.info(String.valueOf(compte));
        } catch (SQLException e) {
            log.info("Exception SQL: " + e.getMessage() );
        }
    }

    @Test
    void getAllValid() {
        try {
            List<Compte> comptes  = compteDAOImp.getAll();
            log.info(comptes.toString());
        } catch (SQLException e) {
            log.info("Exception SQL: " + e.getMessage() );
        }
    }

    @Test
    void getParLoginValid() {
        try {
            Compte compte  = compteDAOImp.getParLogin("login2");
            log.info("getParLogin :" + compte);
        } catch (SQLException e) {
            log.info("Exception SQL: " + e.getMessage() );
        }
    }

    @Test
    void insertValid() {
        try {
            int random = (int)(Math.random() * 500000 + 1);
            Compte compte = new Compte("login"+random, "password2M@");
            Integer id  = compteDAOImp.insert(compte);
            log.info("L'id du compte insere est "+ id);
        } catch (SQLException e) {
            log.info("Exception SQL: " + e.getMessage() );
        }
    }

    @Test
    void updateValid() {
        try {
            Compte compte = new Compte(1,"loginUpdate", "passwordUpdate2M@","ROLE_CLIENT");
            Integer id  = compteDAOImp.update(compte);
            log.info("L'id du compte modifie est "+ id);
        } catch (SQLException e) {
            log.info("Exception SQL: " + e.getMessage() );
        }
    }


    @Test
    void deleteValid() {
        try {
            Integer id  = 1;
            compteDAOImp.delete(id);
            log.info("L'id du compte "+ id + " a bien ete supprimer");
        } catch (SQLException e) {
            log.info("Exception SQL: " + e.getMessage() );
        }
    }
}
