package fr.afpa.pompey.cda22045.myyebook.dao;

import fr.afpa.pompey.cda22045.myyebook.dao.clientdao.ClientDAOImp;
import fr.afpa.pompey.cda22045.myyebook.dao.livredao.LivreDAOImpl;
import fr.afpa.pompey.cda22045.myyebook.dao.reservationdao.ReservationDAOImp;
import fr.afpa.pompey.cda22045.myyebook.model.Client;
import fr.afpa.pompey.cda22045.myyebook.model.Reservation;
import fr.afpa.pompey.cda22045.myyebook.model.Livre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

public class ReservationDAOImpTest {

    LivreDAOImpl livreDAOImpl = new LivreDAOImpl();
//    CategorieDAOImpl categorieDAOImp = new CategorieDAOImpl();
    ClientDAOImp clientDAOImp = new ClientDAOImp();
    ReservationDAOImp reservationDAOImpl = new ReservationDAOImp();


    @BeforeEach
    void setUp() {
    }

    @Test
    void getByIdValid() {
        try {
            Reservation reservation = reservationDAOImpl.get(1);
            System.out.println(reservation);
        } catch (SQLException e) {
            System.out.println("Exception SQL: " + e.getMessage() );
        }
    }

    @Test
    void getAllValid() {
        try {
            List<Reservation> reservations  = reservationDAOImpl.getAll();
            System.out.println(reservations);
        } catch (SQLException e) {
            System.out.println("Exception SQL: " + e.getMessage() );
        }
    }

    @Test
    void insertValid() {
        try {
            Livre livre = livreDAOImpl.get(1);
            Client client = clientDAOImp.get(1);
            Reservation reservation = new Reservation(null,client,livre);
            System.out.println(livre);
            Integer id  = reservationDAOImpl.insert(reservation);
            System.out.println("L'id de la reservation insere est "+ id);
        } catch (SQLException e) {
            System.out.println("Exception SQL: " + e.getMessage() );
        }
    }
//
    @Test
    void updateValid() {
        try {
            Reservation reservation = reservationDAOImpl.get(5);
            Livre livre = livreDAOImpl.get(2);
            reservation.setLivre(livre);
            int id = reservationDAOImpl.update(reservation);
            System.out.println("L'id de l'reservation modifie est "+ id);
        } catch (SQLException e) {
            System.out.println("Exception SQL: " + e.getMessage() );
        }
    }
//
//
    @Test
    void deleteValid() {
        try {
            Integer id = reservationDAOImpl.delete(5);
            System.out.println("L'id de l'reservation "+ id + " a bien ete supprimer");
        } catch (SQLException e) {
            System.out.println("Exception SQL: " + e.getMessage() );
        }
    }
}
