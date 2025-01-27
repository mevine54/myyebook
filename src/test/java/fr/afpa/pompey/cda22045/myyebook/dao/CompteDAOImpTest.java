package fr.afpa.pompey.cda22045.myyebook.dao;

import fr.afpa.pompey.cda22045.myyebook.dao.comptedao.CompteDAOImp;
import fr.afpa.pompey.cda22045.myyebook.model.Compte;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

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
            System.out.println(compte);
        } catch (SQLException e) {
            System.out.println("Exception SQL: " + e.getMessage() );
        }
    }

    @Test
    void getAllValid() {
        try {
            List<Compte> comptes  = compteDAOImp.getAll();
            System.out.println(comptes);
        } catch (SQLException e) {
            System.out.println("Exception SQL: " + e.getMessage() );
        }
    }

    @Test
    void getParLoginValid() {
        try {
            Compte compte  = compteDAOImp.getParLogin("login2");
            System.out.println("getParLogin :" + compte);
        } catch (SQLException e) {
            System.out.println("Exception SQL: " + e.getMessage() );
        }
    }

    @Test
    void insertValid() {
        try {
            int random = (int)(Math.random() * 500000 + 1);
            Compte compte = new Compte("login"+random, "password2M@");
            Integer id  = compteDAOImp.insert(compte);
            System.out.println("L'id du compte insere est "+ id);
        } catch (SQLException e) {
            System.out.println("Exception SQL: " + e.getMessage() );
        }
    }

    @Test
    void updateValid() {
        try {
            Compte compte = new Compte(1,"loginUpdate", "passwordUpdate2M@","ROLE_CLIENT");
            Integer id  = compteDAOImp.update(compte);
            System.out.println("L'id du compte modifie est "+ id);
        } catch (SQLException e) {
            System.out.println("Exception SQL: " + e.getMessage() );
        }
    }


    @Test
    void deleteValid() {
        try {
            Integer id  = 1;
            compteDAOImp.delete(id);
            System.out.println("L'id du compte "+ id + " a bien ete supprimer");
        } catch (SQLException e) {
            System.out.println("Exception SQL: " + e.getMessage() );
        }
    }
}
