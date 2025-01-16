package fr.afpa.pompey.cda22045.myyebook.model;

import fr.afpa.pompey.cda22045.myyebook.exception.IdTropPetitException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmprunterTest {

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void setId() {
        Emprunter emprunter = new Emprunter();
        IdTropPetitException e = assertThrows(IdTropPetitException.class, () -> emprunter.setId(0));
        assertEquals("L'id ne peut pas etre inferieur ou egal a zero", e.getMessage());
    }

    @Test
    void setClient() {
    }

    @Test
    void setExemplaire() {
    }

    @Test
    void setLibraire() {
    }

    @Test
    void setReservation() {
    }

    @Test
    void setDatetimeEmprunt() {
    }

    @Test
    void setDatetimeRetour() {
    }

    @Test
    void testToString() {
    }

    @Test
    void getId() {
    }

    @Test
    void getReservation() {
    }

    @Test
    void getClient() {
    }

    @Test
    void getLibraire() {
    }

    @Test
    void getExemplaire() {
    }

    @Test
    void getDatetimeEmprunt() {
    }

    @Test
    void getDatetimeRetour() {
    }
}