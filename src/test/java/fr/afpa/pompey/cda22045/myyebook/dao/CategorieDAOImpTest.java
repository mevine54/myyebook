package fr.afpa.pompey.cda22045.myyebook.dao;

import fr.afpa.pompey.cda22045.myyebook.dao.categoriedao.CategorieDAOImpl;
import fr.afpa.pompey.cda22045.myyebook.model.Categorie;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

@Slf4j
public class CategorieDAOImpTest {

    CategorieDAOImpl categorieDAOImp = new CategorieDAOImpl();


    @BeforeEach
    void setUp() {
    }

    @Test
    void getByIdValid() {
        try {
            Categorie categorie = categorieDAOImp.get(1);
            log.info(String.valueOf(categorie));
        } catch (SQLException e) {
            log.info("Exception SQL: " + e.getMessage() );
        }
    }

    @Test
    void getAllValid() {
        try {
            List<Categorie> categories  = categorieDAOImp.getAll();
            log.info(categories.toString());
        } catch (SQLException e) {
            log.info("Exception SQL: " + e.getMessage() );
        }
    }

    @Test
    void insertValid() {
        try {
            Categorie categorie = new Categorie(null,"temp");
            Integer id  = categorieDAOImp.insert(categorie);
            log.info("L'id de la  categorie insere est "+ id);
        } catch (SQLException e) {
            log.info("Exception SQL: " + e.getMessage() );
        }
    }
//
    @Test
    void updateValid() {
        try {
            Categorie categorie = categorieDAOImp.get(15);
            log.info(String.valueOf(categorie));
            categorie.setNom("BLABLA");
            Integer id  = categorieDAOImp.update(categorie);
            log.info("L'id de la libraire modifie est "+ id);
        } catch (SQLException e) {
            log.info("Exception SQL: " + e.getMessage() );
        }
    }


    @Test
    void deleteValid() {
        try {
            Integer id  = 15;
            categorieDAOImp.delete(id);
            log.info("L'id du compte "+ id + " a bien ete supprimer");
        } catch (SQLException e) {
            log.info("Exception SQL: " + e.getMessage() );
        }
    }
}
