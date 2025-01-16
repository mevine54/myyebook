package fr.afpa.pompey.cda22045.myyebook.dao.reservationDAO;

import fr.afpa.pompey.cda22045.myyebook.ConnectionBDD.DatabaseConnection;
import fr.afpa.pompey.cda22045.myyebook.model.Client;
import fr.afpa.pompey.cda22045.myyebook.model.Livre;
import fr.afpa.pompey.cda22045.myyebook.model.Reservation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAOImp implements ReservationDAO {
    /**
     * Get t.
     *
     * @param id the id
     * @return the t
     * @throws SQLException the sql exception
     */
    @Override
    public Reservation get(Integer id) throws SQLException {
        Reservation reservation = new Reservation();
        String selectResId = "SELECT * FROM reservation r " +
                "JOIN livre l ON r.liv_id = l.liv_id " +
                "JOIN client c ON r.cli_id = c.cli_id " +
                "WHERE r.res_id = ?";
        try(
                Connection connection = DatabaseConnection.getInstanceDB();
                PreparedStatement ps = connection.prepareStatement(selectResId))
        {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                reservation.setResId(rs.getInt("res_id"));
                reservation.setDatetime(rs.getTimestamp("res_date").toLocalDateTime());

                Livre livre = new Livre();
                livre.setId(rs.getInt("liv_id"));

                Client client = new Client();
                client.setClientId(rs.getInt("cli_id"));

                reservation.setLivre(livre);  // Association du livre
                reservation.setClient(client);  // Association du client
            }

        } catch (SQLException e) {
            throw new SQLException("Impossible de trouver la reservation", e);
        }
        return reservation;
    }


    /**
     * Gets all.
     *
     * @return the all
     * @throws SQLException the sql exception
     */
    @Override
    public List<Reservation> getAll() throws SQLException {
        List<Reservation> reservations = new ArrayList<>();
        String selectAllRes = "SELECT * FROM reservation r " +
                "JOIN livre l ON r.liv_id = l.liv_id " +
                "JOIN client c ON r.cli_id = c.cli_id ";

        try (
                Connection connection = DatabaseConnection.getInstanceDB();
                PreparedStatement ps = connection.prepareStatement(selectAllRes)
        ) {
            ResultSet rs = ps.executeQuery();


            while (rs.next()) {
                Livre livre = new Livre();
                livre.setId(rs.getInt("liv_id"));

                Client client = new Client();
                client.setClientId(rs.getInt("cli_id"));

                Reservation reservation = new Reservation(
                        rs.getInt("res_id"),
                        client,
                        livre,
                        rs.getTimestamp("res_date").toLocalDateTime()
                );

                reservations.add(reservation);
            }
        } catch (SQLException e) {
            throw new SQLException("Impossible de trouver las reservations", e);
        }


        return reservations;
    }


    /**
     * Insert int.
     *
     * @param reservation the t
     * @return the int
     * @throws SQLException the sql exception
     */
    @Override
    public int insert(Reservation reservation) throws SQLException {
        String insertRes = "INSERT INTO reservation(res_date, liv_id, cli_id) VALUES(?,?,?)";
        int newID = 0;

        try (   Connection connection = DatabaseConnection.getInstanceDB();
                PreparedStatement ps = connection.prepareStatement(insertRes, Statement.RETURN_GENERATED_KEYS)
        ) {
            ps.setTimestamp(1, Timestamp.valueOf(reservation.getDatetime()));
            ps.setInt(2, reservation.getLivre().getId());
            ps.setInt(3, reservation.getClient().getClientId());

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                newID = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new SQLException("Impossible d'inserer la reservation", e);
        }

        return newID;
    }


    /**
     * Update int.
     *
     * @param reservation the t
     * @return the int
     * @throws SQLException the sql exception
     */
    @Override
    public int update(Reservation reservation) throws SQLException {
//        A revoir si on mets a jour la reservation selon le cli_id ou la res_id
        String updateRes = "UPDATE reservation SET res_date = ? WHERE res_id = ?";

        try (
                Connection connection = DatabaseConnection.getInstanceDB();
                PreparedStatement ps = connection.prepareStatement(updateRes))
        {
            ps.setTimestamp(1, Timestamp.valueOf(reservation.getDatetime()));
            ps.setInt(2, reservation.getResId());

            return ps.executeUpdate();

        } catch (SQLException e) {
            throw new SQLException("Impossible d'actualizar la reservation", e);
        }
    }


    /**
     * Delete int.
     *
     * @param id the
     * @return the int
     * @throws SQLException the sql exception
     */
    @Override
    public int delete(Integer id) throws SQLException {
//        Voir pour ajouter DELETE ON CASCAD dans le script
        String deleteRes = "DELETE FROM reservation WHERE res_id = ?";

        try (
                Connection connection = DatabaseConnection.getInstanceDB();
                PreparedStatement ps = connection.prepareStatement(deleteRes))
        {
            ps.setInt(1, id);
            ps.executeUpdate();
        }

        return id;
    }
}
