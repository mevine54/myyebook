package fr.afpa.pompey.cda22045.myyebook.model;

import fr.afpa.pompey.cda22045.myyebook.dao.categoriedao.CategorieDAOImpl;
import fr.afpa.pompey.cda22045.myyebook.dao.comptedao.CompteDAOImp;
import fr.afpa.pompey.cda22045.myyebook.dao.librairedao.LibraireDAOImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

public class CategorieDAOImpTest {

    CategorieDAOImpl categorieDAOImp = new CategorieDAOImpl();


    @BeforeEach
    void setUp() {
    }

    @Test
    void getByIdValid() {
        try {
            Categorie categorie = categorieDAOImp.get(1);
            System.out.println(categorie);
        } catch (SQLException e) {
            System.out.println("Exception SQL: " + e.getMessage() );
        }
    }

    @Test
    void getAllValid() {
        try {
            List<Categorie> categories  = categorieDAOImp.getAll();
            System.out.println(categories);
        } catch (SQLException e) {
            System.out.println("Exception SQL: " + e.getMessage() );
        }
    }

    @Test
    void insertValid() {
        try {
            Categorie categorie = new Categorie(null,"temp");
            Integer id  = categorieDAOImp.insert(categorie);
            System.out.println("L'id de la  categorie insere est "+ id);
        } catch (SQLException e) {
            System.out.println("Exception SQL: " + e.getMessage() );
        }
    }
//
    @Test
    void updateValid() {
        try {
            Categorie categorie = categorieDAOImp.get(15);
            System.out.println(categorie);
            categorie.setNom("BLABLA");
            Integer id  = categorieDAOImp.update(categorie);
            System.out.println("L'id de la libraire modifie est "+ id);
        } catch (SQLException e) {
            System.out.println("Exception SQL: " + e.getMessage() );
        }
    }


    @Test
    void deleteValid() {
        try {
            Integer id  = 15;
            categorieDAOImp.delete(id);
            System.out.println("L'id du compte "+ id + " a bien ete supprimer");
        } catch (SQLException e) {
            System.out.println("Exception SQL: " + e.getMessage() );
        }
    }
}
