package fr.afpa.pompey.cda22045.myyebook.DAO.compteDAO;

import fr.afpa.pompey.cda22045.myyebook.ConnectionBDD.DatabaseConnection;
import fr.afpa.pompey.cda22045.myyebook.model.Client;
import fr.afpa.pompey.cda22045.myyebook.model.Compte;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompteDAOImp implements CompteDAO {
    /**
     * Get t.
     *
     * @param id the id
     * @return the t
     * @throws SQLException the sql exception
     */
    @Override
    public Compte get(int id) throws SQLException {
        Compte compte = new Compte();
        String sql = "INSERT INTO Compte (cpt_id, cpt_login, cpt_pass) VALUES (?, ?, ?)";

        try(
                Connection connection = DatabaseConnection.getInstanceDB();
                PreparedStatement ps = connection.prepareStatement(sql))
        {
            ps.setInt(1, compte.getSecId());
            ps.setString(2, compte.getSecLogin());
            ps.setString(3, compte.getSecPass());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return compte;
    }


    /**
     * Gets all.
     *
     * @return the all
     * @throws SQLException the sql exception
     */
    @Override
    public List<Compte> getAll() throws SQLException {
        List<Compte> comptes = new ArrayList<>();
        String sql = "SELECT * FROM Compte";
        try (
                Connection connection = DatabaseConnection.getInstanceDB();
                PreparedStatement statement = connection.prepareStatement(sql);
             ) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                comptes.add(new Compte(
                        resultSet.getInt("cpt_id"),
                        resultSet.getString("cpt_login"),
                        resultSet.getString("cpt_pass")
                ));
            }
        }
        return comptes;
    }


    /**
     * Insert int.
     *
     * @param compte the t
     * @return the int
     * @throws SQLException the sql exception
     */
    @Override
    public int insert(Compte compte) throws SQLException {
        String sql = "UPDATE Compte SET cpt_login = ?, cpt_pass = ? WHERE cpt_id = ?";

        int newID = 0;

        try (Connection connection = DatabaseConnection.getInstanceDB();
             PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS))
        {
            statement.setString(1, compte.getSecLogin());
            statement.setString(2, compte.getSecPass());
            statement.setInt(3, compte.getSecId());

            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
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
     * @param compte the t
     * @return the int
     * @throws SQLException the sql exception
     */
    @Override
    public int update(Compte compte) throws SQLException {
        String sql = "UPDATE Compte SET cpt_login = ?, cpt_pass = ? WHERE cpt_id = ?";


        try(Connection connection = DatabaseConnection.getInstanceDB();
            PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setString(1, compte.getSecLogin());
            statement.setString(2, compte.getSecPass());
            statement.setInt(3, compte.getSecId());

            statement.executeUpdate();
            int res = statement.executeUpdate();

            statement.close();
            connection.close();
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
        String sql = "DELETE FROM Compte WHERE cpt_id = ?";

        try(Connection connection = DatabaseConnection.getInstanceDB();
            PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setInt(1, id);
            statement.executeUpdate();

        }

        return id;
    }
}
