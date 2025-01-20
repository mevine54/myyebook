package fr.afpa.pompey.cda22045.myyebook.model;

import fr.afpa.pompey.cda22045.myyebook.dao.auteurdao.AuteurDAOImpl;
import fr.afpa.pompey.cda22045.myyebook.dao.categoriedao.CategorieDAOImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

public class AuteurDAOImpTest {

    AuteurDAOImpl auteurDAOImp = new AuteurDAOImpl();

    @BeforeEach
    void setUp() {
    }

    @Test
    void getByIdValid() {
        try {
            Auteur auteur = auteurDAOImp.get(1);
            System.out.println(auteur);
        } catch (SQLException e) {
            System.out.println("Exception SQL: " + e.getMessage() );
        }
    }

    @Test
    void getAllValid() {
        try {
            List<Auteur>  auteurs  = auteurDAOImp.getAll();
            System.out.println(auteurs);
        } catch (SQLException e) {
            System.out.println("Exception SQL: " + e.getMessage() );
        }
    }
//
//    @Test
//    void insertValid() {
//        try {
//            Categorie categorie = new Categorie(null,"temp");
//            Integer id  = categorieDAOImp.insert(categorie);
//            System.out.println("L'id de la  categorie insere est "+ id);
//        } catch (SQLException e) {
//            System.out.println("Exception SQL: " + e.getMessage() );
//        }
//    }
////
//    @Test
//    void updateValid() {
//        try {
//            Categorie categorie = categorieDAOImp.get(15);
//            System.out.println(categorie);
//            categorie.setNom("BLABLA");
//            Integer id  = categorieDAOImp.update(categorie);
//            System.out.println("L'id de la libraire modifie est "+ id);
//        } catch (SQLException e) {
//            System.out.println("Exception SQL: " + e.getMessage() );
//        }
//    }
//
//
//    @Test
//    void deleteValid() {
//        try {
//            Integer id  = 15;
//            categorieDAOImp.delete(id);
//            System.out.println("L'id du compte "+ id + " a bien ete supprimer");
//        } catch (SQLException e) {
//            System.out.println("Exception SQL: " + e.getMessage() );
//        }
//    }
}
