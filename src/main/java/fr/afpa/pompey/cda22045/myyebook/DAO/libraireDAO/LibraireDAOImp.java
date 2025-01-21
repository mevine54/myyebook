package fr.afpa.pompey.cda22045.myyebook.DAO.libraireDAO;

import fr.afpa.pompey.cda22045.myyebook.ConnectionBDD.DatabaseConnection;
import fr.afpa.pompey.cda22045.myyebook.model.Libraire;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibraireDAOImp implements LibraireDAO {

    /**
     * Get t.
     *
     * @param libId the id
     * @return the t
     * @throws SQLException the sql exception
     */
    @Override
    public Libraire get(int libId) throws SQLException {
        Libraire libraire = new Libraire();
        String sql = "SELECT * FROM Libraire WHERE lib_id = ?";

        try(
                Connection connection = DatabaseConnection.getInstanceDB();
                PreparedStatement ps = connection.prepareStatement(sql))
        {
            ps.setInt(1, libId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Libraire(
                        rs.getInt("lib_id"),
                        rs.getString("lib_nom"),
                        rs.getString("lib_prenom")
                );
            }

        }

        return libraire;
    }

    /**
     * Gets all.
     *
     * @return the all
     * @throws SQLException the sql exception
     */
    @Override
    public List<Libraire> getAll() throws SQLException {
        List<Libraire> libraires = new ArrayList<>();
        String sql = "SELECT * FROM Libraire";
        try(
                Connection connection = DatabaseConnection.getInstanceDB();
                PreparedStatement ps = connection.prepareStatement(sql)
                ) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                libraires.add(new Libraire(
                        rs.getInt("lib_id"),
                        rs.getString("lib_nom"),
                        rs.getString("lib_prenom")
                ));
            }
        }
        return libraires;
    }


    /**
     * Insert int.
     *
     * @param libraire the t
     * @return the int
     * @throws SQLException the sql exception
     */
    @Override
    public int insert(Libraire libraire) throws SQLException {
        String sql = "INSERT INTO Libraire (lib_id, lib_nom, lib_prenom) VALUES (?, ?, ?)";
        int newID = 0;

        try(
                Connection connection = DatabaseConnection.getInstanceDB();
                PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS))
        {
            ps.setInt(1, libraire.getLibId());
            ps.setString(2, libraire.getLibNom());
            ps.setString(3, libraire.getLibPrenom());

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
     * @param libraire the t
     * @return the int
     * @throws SQLException the sql exception
     */
    @Override
    public int update(Libraire libraire) throws SQLException {
        String sql = "UPDATE Libraire SET lib_nom = ?, lib_prenom = ? WHERE lib_id = ?";

        try (
                Connection connection = DatabaseConnection.getInstanceDB();
                PreparedStatement ps = connection.prepareStatement(sql)
                ){
            ps.setString(1, libraire.getLibNom());
            ps.setString(2, libraire.getLibPrenom());
            ps.setInt(3, libraire.getLibId());

            ps.executeUpdate();
            int res = ps.executeUpdate();

            return res;
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
        String sql = "DELETE FROM Libraire WHERE lib_id = ?";

        try(
                Connection connection = DatabaseConnection.getInstanceDB();
                PreparedStatement ps = connection.prepareStatement(sql))
        {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
        return id;
    }


}


