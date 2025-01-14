package fr.afpa.pompey.cda22045.myyebook.model;

import fr.afpa.pompey.cda22045.myyebook.dao.comptedao.CompteDAOImp;
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
}
