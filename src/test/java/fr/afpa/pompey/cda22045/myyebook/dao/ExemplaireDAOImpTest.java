package fr.afpa.pompey.cda22045.myyebook.dao;

import fr.afpa.pompey.cda22045.myyebook.dao.exemplairedao.ExemplaireDAOImpl;
import fr.afpa.pompey.cda22045.myyebook.dao.livredao.LivreDAOImpl;
import fr.afpa.pompey.cda22045.myyebook.model.Exemplaire;
import fr.afpa.pompey.cda22045.myyebook.model.Livre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

public class ExemplaireDAOImpTest {

    LivreDAOImpl livreDAOImpl = new LivreDAOImpl();
//    CategorieDAOImpl categorieDAOImp = new CategorieDAOImpl();
//    AuteurDAOImpl auteurDAOImp = new AuteurDAOImpl();
    ExemplaireDAOImpl exemplaireDAOImpl = new ExemplaireDAOImpl();


    @BeforeEach
    void setUp() {
    }

    @Test
    void getByIdValid() {
        try {
            Exemplaire exemplaire = exemplaireDAOImpl.get(1);
            System.out.println(exemplaire);
        } catch (SQLException e) {
            System.out.println("Exception SQL: " + e.getMessage() );
        }
    }

    @Test
    void getAllValid() {
        try {
            List<Exemplaire> exemplaires  = exemplaireDAOImpl.getAll();
            System.out.println(exemplaires);
        } catch (SQLException e) {
            System.out.println("Exception SQL: " + e.getMessage() );
        }
    }

    @Test
    void insertValid() {
        try {
            Livre livre = livreDAOImpl.get(1);
            Exemplaire exemplaire = new Exemplaire(null,livre);
            System.out.println(livre);
            Integer id  = exemplaireDAOImpl.insert(exemplaire);
            System.out.println("L'id de l'exemplaire insere est "+ id);
        } catch (SQLException e) {
            System.out.println("Exception SQL: " + e.getMessage() );
        }
    }
//
    @Test
    void updateValid() {
        try {
            Exemplaire exemplaire = exemplaireDAOImpl.get(11);
            Livre livre = livreDAOImpl.get(2);
            exemplaire.setLivre(livre);
            int id = exemplaireDAOImpl.update(exemplaire);
            System.out.println("L'id de l'exemplaire modifie est "+ id);
        } catch (SQLException e) {
            System.out.println("Exception SQL: " + e.getMessage() );
        }
    }
//
//
    @Test
    void deleteValid() {
        try {
            Integer id = exemplaireDAOImpl.delete(14);
            System.out.println("L'id de l'exemplaire "+ id + " a bien ete supprimer");
        } catch (SQLException e) {
            System.out.println("Exception SQL: " + e.getMessage() );
        }
    }
}
