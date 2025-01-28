package fr.afpa.pompey.cda22045.myyebook.dao.reservationdao;


import fr.afpa.pompey.cda22045.myyebook.connectionbdd.DatabaseConnection;
import fr.afpa.pompey.cda22045.myyebook.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAOImp implements ReservationDAO {

    @Override
    public Reservation get(Integer resId) throws SQLException {
        Reservation reservation = null;
        String sql = "SELECT * FROM Reservation r " +
                "INNER JOIN Livre l ON r.liv_id = l.liv_id " +
                "INNER JOIN Client c ON r.cli_id = c.cli_id " +
                "INNER JOIN Compte cp ON c.cpt_id = cp.cpt_id " +
                "INNER JOIN Categorie cat ON l.cat_id = cat.cat_id " +
                "INNER JOIN Auteur a ON l.aut_id = a.aut_id " +
                "WHERE r.res_id = ?";

        try (
                Connection connection = DatabaseConnection.getInstanceDB();
                PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, resId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Compte compte = new Compte(
                        rs.getInt("cpt_id"),
                        rs.getString("cpt_login"),
                        rs.getString("cpt_mdp"),
                        rs.getString("cpt_role")
                );

                Client client = new Client(
                        compte,
                        rs.getInt("cli_id"),
                        rs.getString("cli_nom"),
                        rs.getString("cli_prenom"),
                        rs.getString("cli_email"),
                        rs.getString("cli_adresse"),
                        rs.getString("cli_ville"),
                        rs.getString("cli_code_postale")
                );

                Categorie categorie = new Categorie(
                        rs.getInt("cat_id"),
                        rs.getString("cat_nom")
                );

                Auteur auteur = new Auteur(
                        rs.getInt("aut_id"),
                        rs.getString("aut_nom"),
                        rs.getString("aut_prenom"),
                        rs.getString("aut_photo")
                );

                Livre livre = new Livre(
                        rs.getInt("liv_id"),
                        rs.getString("liv_titre"),
                        rs.getString("liv_resume"),
                        rs.getString("liv_photo"),
                        rs.getBoolean("liv_en_avant"),
                        auteur, categorie
                );

                reservation = new Reservation(
                        rs.getInt("res_id"),
                        client,
                        livre,
                        rs.getTimestamp("res_date").toLocalDateTime()
                );
            }
        }

        return reservation;
    }

    @Override
    public List<Reservation> getAll() throws SQLException {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM Reservation r " +
                "INNER JOIN Livre l ON r.liv_id = l.liv_id " +
                "INNER JOIN Client c ON r.cli_id = c.cli_id " +
                "INNER JOIN Compte cp ON c.cpt_id = cp.cpt_id " +
                "INNER JOIN Categorie cat ON l.cat_id = cat.cat_id " +
                "INNER JOIN Auteur a ON l.aut_id = a.aut_id";

        try (
                Connection connection = DatabaseConnection.getInstanceDB();
                PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Compte compte = new Compte(
                        rs.getInt("cpt_id"),
                        rs.getString("cpt_login"),
                        rs.getString("cpt_mdp"),
                        rs.getString("cpt_role")
                );

                Client client = new Client(
                        compte,
                        rs.getInt("cli_id"),
                        rs.getString("cli_nom"),
                        rs.getString("cli_prenom"),
                        rs.getString("cli_email"),
                        rs.getString("cli_adresse"),
                        rs.getString("cli_ville"),
                        rs.getString("cli_code_postale")
                );

                Categorie categorie = new Categorie(
                        rs.getInt("cat_id"),
                        rs.getString("cat_nom")
                );

                Auteur auteur = new Auteur(
                        rs.getInt("aut_id"),
                        rs.getString("aut_nom"),
                        rs.getString("aut_prenom"),
                        rs.getString("aut_photo")
                );

                Livre livre = new Livre(
                        rs.getInt("liv_id"),
                        rs.getString("liv_titre"),
                        rs.getString("liv_resume"),
                        rs.getString("liv_photo"),
                        rs.getBoolean("liv_en_avant"),
                        auteur,
                        categorie
                );

                Reservation reservation = new Reservation(
                        rs.getInt("res_id"),
                        client,
                        livre,
                        rs.getTimestamp("res_date").toLocalDateTime()
                );

                reservations.add(reservation);
            }
        }
        return reservations;
    }

    @Override
    public Integer insert(Reservation reservation) throws SQLException {
        String sqlInsertReservation = "INSERT INTO Reservation (res_date, liv_id, cli_id) VALUES (?, ?, ?)";
        try (
                Connection connection = DatabaseConnection.getInstanceDB();
                PreparedStatement psInsertReservation = connection.prepareStatement(sqlInsertReservation, Statement.RETURN_GENERATED_KEYS)) {
            connection.setAutoCommit(false);

            // Insérer la réservation
            psInsertReservation.setTimestamp(1, Timestamp.valueOf(reservation.getDatetime()));
            psInsertReservation.setInt(2, reservation.getLivre().getId());
            psInsertReservation.setInt(3, reservation.getClient().getClientId());
            psInsertReservation.executeUpdate();

            ResultSet generatedKeys = psInsertReservation.getGeneratedKeys();
            if (generatedKeys.next()) {
                int reservationId = generatedKeys.getInt(1);
                reservation.setResId(reservationId);
                connection.commit();
                return reservationId;
            } else {
                connection.rollback();
                throw new SQLException("Failed to insert reservation, no ID obtained.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer update(Reservation reservation) throws SQLException {
        String sqlUpdateReservation = "UPDATE Reservation SET res_date = ?, liv_id = ?, cli_id = ? WHERE res_id = ?";
        try (
                Connection connection = DatabaseConnection.getInstanceDB();
                PreparedStatement psUpdateReservation = connection.prepareStatement(sqlUpdateReservation)) {
            connection.setAutoCommit(false);

            // Mettre à jour la réservation
            psUpdateReservation.setTimestamp(1, Timestamp.valueOf(reservation.getDatetime()));
            psUpdateReservation.setInt(2, reservation.getLivre().getId());
            psUpdateReservation.setInt(3, reservation.getClient().getClientId());
            psUpdateReservation.setInt(4, reservation.getResId());

            int rowsAffected = psUpdateReservation.executeUpdate();
            if (rowsAffected > 0) {
                connection.commit();
                return reservation.getResId();
            } else {
                connection.rollback();
                throw new SQLException("Failed to update reservation, no rows affected.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer delete(int resId) throws SQLException {
        String sqlDeleteReservation = "DELETE FROM Reservation WHERE res_id = ?";
        try (
                Connection connection = DatabaseConnection.getInstanceDB();
                PreparedStatement psDeleteReservation = connection.prepareStatement(sqlDeleteReservation)) {
            connection.setAutoCommit(false);

            // Supprimer la réservation
            psDeleteReservation.setInt(1, resId);
            int rowsAffected = psDeleteReservation.executeUpdate();

            if (rowsAffected > 0) {
                connection.commit();
                return resId;
            } else {
                connection.rollback();
                throw new SQLException("Failed to delete reservation, no rows affected.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}