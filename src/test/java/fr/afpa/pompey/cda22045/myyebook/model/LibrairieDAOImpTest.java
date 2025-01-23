package fr.afpa.pompey.cda22045.myyebook.model;

import fr.afpa.pompey.cda22045.myyebook.dao.comptedao.CompteDAOImp;
import fr.afpa.pompey.cda22045.myyebook.dao.librairedao.LibraireDAO;
import fr.afpa.pompey.cda22045.myyebook.dao.librairedao.LibraireDAOImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

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
            System.out.println(libraire);
        } catch (SQLException e) {
            System.out.println("Exception SQL: " + e.getMessage() );
        }
    }

    @Test
    void getAllValid() {
        try {
            List<Libraire> libraires  = libraireDAOImp.getAll();
            System.out.println(libraires);
        } catch (SQLException e) {
            System.out.println("Exception SQL: " + e.getMessage() );
        }
    }

    @Test
    void insertValid() {
        try {
            Libraire libraire = new Libraire("lomdfe","mqsdqsdqscxv12@L","ROLE_LIBRAIRE","Nomlibraire","PrenomLibraire");
            Integer id  = libraireDAOImp.insert(libraire);
            System.out.println("L'id de la libraire insere est "+ id);
        } catch (SQLException e) {
            System.out.println("Exception SQL: " + e.getMessage() );
        }
    }

    @Test
    void updateValid() {
        try {
            Libraire libraire = libraireDAOImp.get(3);
            System.out.println(libraire);
            libraire.setNom("BLABLANOM");
            libraire.setPrenom("BLABLAPRENOM");
            Integer id  = libraireDAOImp.update(libraire);
            System.out.println("L'id de la libraire modifie est "+ id);
        } catch (SQLException e) {
            System.out.println("Exception SQL: " + e.getMessage() );
        }
    }


    @Test
    void deleteValid() {
        try {
            Integer id  = 2;
            libraireDAOImp.delete(id);
            System.out.println("L'id de la libraire "+ id + " a bien ete supprimer");
        } catch (SQLException e) {
            System.out.println("Exception SQL: " + e.getMessage() );
        }
    }
}
