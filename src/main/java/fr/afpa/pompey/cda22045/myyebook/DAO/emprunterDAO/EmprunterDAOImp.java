package fr.afpa.pompey.cda22045.myyebook.DAO.emprunterDAO;

import fr.afpa.pompey.cda22045.myyebook.ConnectionBDD.DatabaseConnection;
import fr.afpa.pompey.cda22045.myyebook.model.Auteur;
import fr.afpa.pompey.cda22045.myyebook.model.Client;
import fr.afpa.pompey.cda22045.myyebook.model.Emprunter;
import lombok.Data;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Types.DATE;

public class EmprunterDAOImp implements EmprunterDAO {
    /**
     * Get t.
     *
     * @param id the id
     * @return the t
     * @throws SQLException the sql exception
     */
    @Override
    public Emprunter get(int id) throws SQLException {
        Emprunter emprunter = new Emprunter();
        String sql = "SELECT * FROM emprunt WHERE CLIENT_CL_ID = ?";
        try(
                Connection connection = DatabaseConnection.getInstanceDB();
                PreparedStatement ps = connection.prepareStatement(sql))
        {
            ps.setInt(1, emprunter.getCliId());
            ps.setInt(2, emprunter.getLivId());
            ps.setInt(3, emprunter.getLibId());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return emprunter;
    }


    /**
     * Gets all.
     *
     * @return the all
     * @throws SQLException the sql exception
     */
    @Override
    public List<Emprunter> getAll() throws SQLException {
        List<Emprunter> emprunters = new ArrayList<>();
        String sql = "SELECT * FROM EMPRUNT";

        try (
                Connection connection = DatabaseConnection.getInstanceDB();
                PreparedStatement ps = connection.prepareStatement(sql);
        ) {

            ResultSet rs = ps.executeQuery();
            LocalDate empDate = null;
            Date sqlDate = rs.getDate("EMP_DATE");
            if (sqlDate != null) {
                empDate = sqlDate.toLocalDate();
            }

            while (rs.next()) {
                assert empDate != null;
                emprunters.add(new Emprunter(
                        rs.getInt("EMP_ID"),
                        empDate,
                        rs.getInt("CLIENT_CLI_ID"),
                        rs.getInt("LIVRE_LIV_ID"),
                        rs.getInt("LIBRAIRE_LIB_ID")
                ));
            }

        } catch (SQLException e) {
            throw new SQLException("Erreur lors de la récupération des emprunts.", e);
        }
        return emprunters;
    }


    /**
     * Insert int.
     *
     * @param emprunter the t
     * @return the int
     * @throws SQLException the sql exception
     */
    @Override
    public int insert(Emprunter emprunter) throws SQLException {

        String sql = "INSERT INTO EMPRUNT ( EMP_DATE, CLIENT_CLI_ID, LIVRE_LIV_ID, LIBRAIRE_LIB_ID) VALUES ( ?, ?, ?, ?)";
        int newID = 0;

        try (Connection connection = DatabaseConnection.getInstanceDB();
             PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS))
        {

            ps.setDate(1, Date.valueOf(emprunter.getEmpDate()));
            ps.setInt(2, emprunter.getCliId());
            ps.setInt(3, emprunter.getLibId());
            ps.setInt(4, emprunter.getLivId());

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                newID = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return newID;
    }


    /**
     * Update int.
     *
     * @param emprunter the t
     * @return the int
     * @throws SQLException the sql exception
     */
    @Override
    public int update(Emprunter emprunter) throws SQLException {
        String sql = "UPDATE EMPRUNT SET EMP_DATE, CLIENT_CLI_ID = ?, LIVRE_LIV_ID = ?, LIBRAIRE_LIB_ID = ? WHERE EMP_ID = ?";

        try(
                Connection connection = DatabaseConnection.getInstanceDB();
                PreparedStatement ps = connection.prepareStatement(sql))
        {
            if (emprunter.getEmpDate() != null) {
                ps.setDate(1, Date.valueOf(emprunter.getEmpDate()));
            } else {
                ps.setNull(1, DATE); // Gestion de la date null
            }

            ps.setDate(1, Date.valueOf(emprunter.getEmpDate()));
            ps.setInt(2, emprunter.getCliId());
            ps.setInt(3, emprunter.getLibId());
            ps.setInt(4, emprunter.getLivId());

            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
    public int delete(int id) throws SQLException {
        String sql = "DELETE FROM EMPRUNT WHERE EMP_ID = ?";

        try(
                Connection connection = DatabaseConnection.getInstanceDB();
                PreparedStatement ps = connection.prepareStatement(sql))
        {
            ps.setInt(1, id);
            return ps.executeUpdate();
        }

    }
}
