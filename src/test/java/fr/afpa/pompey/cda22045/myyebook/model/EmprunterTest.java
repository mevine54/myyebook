package fr.afpa.pompey.cda22045.myyebook.model;

import fr.afpa.pompey.cda22045.myyebook.dao.emprunterdao.EmprunterDAO;
import fr.afpa.pompey.cda22045.myyebook.dao.emprunterdao.EmprunterDAOImpl;
import fr.afpa.pompey.cda22045.myyebook.exception.IdTropPetitException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class EmprunterTest {

    EmprunterDAOImpl emprunterDAOImpl;

    @BeforeEach
    void setUp(){
        emprunterDAOImpl = new EmprunterDAOImpl();
    }

    @Test
    void getByIdValid() {
        try {
            emprunterDAOImpl.get(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getAllValid() {
        try {
            emprunterDAOImpl.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void insertTest(){
        Client client = new Client();
        client.setClientId(1);
        Libraire libraire = new Libraire();
        libraire.setLibId(1);
        Reservation reservation = new Reservation();
        reservation.setResId(1);
        Exemplaire exemplaire = new Exemplaire();
        exemplaire.setExemplaireId(1);

        Emprunter emprunter = new Emprunter(
                1, client, libraire, exemplaire,
                LocalDateTime.of(2021, 6, 1, 12, 0, 0),
                LocalDateTime.of(2021, 6, 8, 12, 0, 0),
                reservation
        );
        try {
            emprunterDAOImpl.insert(emprunter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void updateTest() throws SQLException {
        Emprunter emp = emprunterDAOImpl.get(2);
        System.out.println("emp: " + emp);
        emp.setDatetimeEmprunt(LocalDate.of(2024, 12, 8).atStartOfDay());

//        int reserveID = emp.getReservation().getResId();
//        System.out.println("reserveID: " + reserveID);
//        Client client = new Client();
//        client.setClientId(1);
//        Libraire libraire = new Libraire();
//        libraire.setLibId(1);
//        Reservation reservation = new Reservation();
//        reservation.setResId(1);
//        Exemplaire exemplaire = new Exemplaire();
//        exemplaire.setExemplaireId(1);

//        Emprunter emprunter = new Emprunter(
//                1, client, libraire, exemplaire,
//                LocalDateTime.of(2021, 6, 1, 12, 0, 0),
//                LocalDateTime.of(2021, 6, 8, 12, 0, 0),
//                reservation
//        );
//        try {
//            emprunterDAOImpl.update(emprunter);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
}

    @Test
    void deleteTest(){
        try {
            emprunterDAOImpl.delete(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void setId() {
        Emprunter emprunter = new Emprunter();
        IdTropPetitException e = assertThrows(IdTropPetitException.class, () -> emprunter.setId(0));
        assertEquals("L'id ne peut pas etre inferieur ou egal a zero", e.getMessage());
    }
}