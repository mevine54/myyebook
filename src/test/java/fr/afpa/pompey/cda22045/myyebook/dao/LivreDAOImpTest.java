package fr.afpa.pompey.cda22045.myyebook.dao;

import fr.afpa.pompey.cda22045.myyebook.dao.auteurdao.AuteurDAOImpl;
import fr.afpa.pompey.cda22045.myyebook.dao.categoriedao.CategorieDAOImpl;
import fr.afpa.pompey.cda22045.myyebook.dao.livredao.LivreDAOImpl;
import fr.afpa.pompey.cda22045.myyebook.model.Auteur;
import fr.afpa.pompey.cda22045.myyebook.model.Categorie;
import fr.afpa.pompey.cda22045.myyebook.model.Livre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

public class LivreDAOImpTest {

    LivreDAOImpl livreDAOImp = new LivreDAOImpl();
    AuteurDAOImpl auteurDAOImp = new AuteurDAOImpl();
    CategorieDAOImpl categorieDAOImp = new CategorieDAOImpl();

    @BeforeEach
    void setUp() {
    }

    @Test
    void getByIdValid() {
        try {
            Livre livre = livreDAOImp.get(1);
            System.out.println(livre);
        } catch (SQLException e) {
            System.out.println("Exception SQL: " + e.getMessage() );
        }
    }

    @Test
    void getAllValid() {
        try {
            List<Livre> livres  = livreDAOImp.getAll();
            System.out.println(livres);
        } catch (SQLException e) {
            System.out.println("Exception SQL: " + e.getMessage() );
        }
    }

    @Test
    void insertValid() {
        try {
            Auteur auteur = auteurDAOImp.get(1);
            Categorie categorie = categorieDAOImp.get(1);
            Livre livre = new Livre(null,"titreLivre","resume losdfsdfdsf","image.jpg",false,auteur,categorie);
            Integer id  = livreDAOImp.insert(livre);
            System.out.println("L'id du livre insere est "+ id);
        } catch (SQLException e) {
            System.out.println("Exception SQL: " + e.getMessage() );
        }
    }

    @Test
    void updateValid() {
        try {
            Livre livre = livreDAOImp.get(11);
            System.out.println(livre);
            livre.setTitre("BLABLA_titre");
            livre.setResume("BLABLA resume");
            livre.setImage("/BLABLA.jpg");
            Integer id  = livreDAOImp.update(livre);
            System.out.println("L'id du livre modifie est "+ id);
        } catch (SQLException e) {
            System.out.println("Exception SQL: " + e.getMessage() );
        }
    }


    @Test
    void deleteValid() {
        try {
            Integer id  = 11;
            livreDAOImp.delete(id);
            System.out.println("L'id du livre "+ id + " a bien ete supprimer");
        } catch (SQLException e) {
            System.out.println("Exception SQL: " + e.getMessage() );
        }
    }
}
