package fr.afpa.pompey.cda22045.myyebook.model;

import fr.afpa.pompey.cda22045.myyebook.dao.reservationdao.ReservationDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * The type Reservation test.
 */
public class ReservationTest {
    /**
     * The Reservation dao.
     */
    public ReservationDAO reservationDAO;
    /**
     * The Reservation.
     */
    public Reservation reservation;


    /**
     * Sets up.
     */
    @BeforeEach
    public void setUp() {
//        this.reservationDAO = new ReservationDAOImp();
        this.reservation = new Reservation();
    }


    /**
     * Test insert res.
     *
     * @throws SQLException the sql exception
     */
    @Test
public void testInsertRes() throws SQLException {
    // Création du client
        Compte compte = new Compte(1,"monlogin","motdepasseSecure1!","ROLE_CLIENT");

        Client client = new Client(compte,null, "Dupont", "Jean", "jean.dupont@example.com", "10 Rue de Paris", "Paris", "75001");
    client.setClientId(1);  //  Vérifie que cet ID existe dans la base

    // Création du livre
    Livre livre = new Livre();
    livre.setId(1);  //  Vérifie que cet ID existe dans la base

    // Création de la réservation sans ID car il sera généré
    Reservation reservation = new Reservation(null, client, livre, LocalDate.of(2025, 1, 16).atStartOfDay());

    // Insertion dans la base
    int generatedId = reservationDAO.insert(reservation);

    // Vérification de l'insertion
    Reservation reservationFromDB = reservationDAO.get(generatedId);

    assertNotNull(reservationFromDB, "La réservation n'a pas été trouvée dans la base");
    assertEquals(reservation.getClient().getClientId(), reservationFromDB.getClient().getClientId(), "Les clients ne correspondent pas");
    assertEquals(reservation.getLivre().getId(), reservationFromDB.getLivre().getId(), "Les livres ne correspondent pas");

    // Comparaison des dates uniquement sur la date (sans l'heure)
    assertEquals(reservation.getDatetime().toLocalDate(), reservationFromDB.getDatetime().toLocalDate(), "Les dates de réservation ne correspondent pas");
}


    /**
     * Test res get all.
     *
     * @throws SQLException the sql exception
     */
    @Test
    public void testResGetAll() throws SQLException {
      List<Reservation> reservations = reservationDAO.getAll();

      assertNotNull(reservations, "La liste des réservations ne doit pas être nulle");

      if (reservations.isEmpty()) {
          System.out.println("Aucune réservation enregistrée dans la base de données");
      } else {
//          Affichage des réservations
          reservations.forEach(System.out::println);

//          Vérification des données de la première réservation
          Reservation reservation = reservations.get(0);
          assertNotNull(reservation.getClient(), "Le client de la réservation ne doit pas être nul");
          assertNotNull(reservation.getLivre(), "Le livre de la réservation ne doit pas être nul");
          assertNotNull(reservation.getDatetime(), "La date de la réservation ne doit pas être nulle");
      }

    }

    /**
     * Test get res by id.
     *
     * @throws SQLException the sql exception
     */
    @Test
    public void testGetResByID() throws SQLException {
        Reservation resByID = reservationDAO.get(1);

        assertNotNull(resByID);

        //          Vérification des données de la première réservation
        assertNotNull(resByID.getClient(), "Le client de la réservation ne doit pas être nul");
        assertNotNull(resByID.getLivre(), "Le livre de la réservation ne doit pas être nul");
        assertNotNull(resByID.getDatetime(), "La date de la réservation ne doit pas être nulle");
        System.out.println(resByID);
    }

    @Test
    public void testUpdateRes() throws SQLException {
        // Récupérer la réservation existante et assurer qu'elle est pas nulle
        Reservation reservation = reservationDAO.get(1);
        assertNotNull(reservation, "La réservation avec l'ID 1 n'existe pas.");

        // Modifier la date
        reservation.setDatetime(LocalDate.of(2024, 1, 10).atStartOfDay());

        // Mettre à jour dans la base
        int updatedRows = reservationDAO.update(reservation);
        assertEquals(1, updatedRows, "Aucune réservation mise à jour.");

        // Vérifier la mise à jour
        Reservation updatedReservation = reservationDAO.get(1);
        assertEquals(LocalDate.of(2024, 1, 10).atStartOfDay(), updatedReservation.getDatetime(),
                "La date de la réservation n'a pas été mise à jour.");

        System.out.println(updatedReservation);
    }

    @Test
    public void testDeleteRes() throws SQLException {
        Reservation reservation = reservationDAO.get(1);

    }



}
