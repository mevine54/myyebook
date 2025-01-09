package fr.afpa.pompey.cda22045.myyebook.DAO.auteurDAO;

import fr.afpa.pompey.cda22045.myyebook.ConnectionBDD.DatabaseConnection;
import fr.afpa.pompey.cda22045.myyebook.model.Auteur;
import fr.afpa.pompey.cda22045.myyebook.model.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuteurDAOImp implements AuteurDAO {
    /**
     * Get t.
     *
     * @param id the id
     * @return the t
     * @throws SQLException the sql exception
     */
    @Override
    public Auteur get(int id) throws SQLException {
        Auteur auteur = new Auteur();
        String sql = "select * from AUTEUR where id = ?";

        try(
                Connection connection = DatabaseConnection.getInstanceDB();
                PreparedStatement ps = connection.prepareStatement(sql))
        {
            ps.setInt(1, auteur.getAutId());
            ps.setString(2, auteur.getAutName());
            ps.setBytes(3, auteur.getAutPhoto());

            ResultSet rs = ps.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return auteur;
    }

    /**
     * Gets all.
     *
     * @return the all
     * @throws SQLException the sql exception
     */
    @Override
    public List<Auteur> getAll() throws SQLException {
        List<Auteur> auteurs = new ArrayList<>();
        String sql = "select * from AUTEUR";

        try (   Connection connection = DatabaseConnection.getInstanceDB();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                auteurs.add(new Auteur(
                        rs.getInt("aut_id"),
                        rs.getString("aut_nom"),
                        rs.getBytes("auteur_img")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return auteurs;


    }

    /**
     * Insert int.
     *
     * @param auteur the t
     * @return the int
     * @throws SQLException the sql exception
     */
    @Override
    public int insert(Auteur auteur) throws SQLException {
        String sql = "INSERT INTO AUTEUR (aut_id ,aut_nom, aut_img) VALUES (?, ?)";
        int newID = 0;

        try (Connection connection = DatabaseConnection.getInstanceDB();
             PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, auteur.getAutId());
            statement.setString(2, auteur.getAutName());
            statement.setBytes(3, auteur.getAutPhoto());

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
     * @param auteur the t
     * @return the int
     * @throws SQLException the sql exception
     */
    @Override
    public int update(Auteur auteur) throws SQLException {
        String sql = "UPDATE AUTEUR SET aut_nom = ?, aut_img = ? WHERE aut_id = ?";

        try(Connection connection = DatabaseConnection.getInstanceDB();
            PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setString(1, auteur.getAutName());
            statement.setString(2, auteur.getAutName());
            statement.setBytes(3, auteur.getAutPhoto());

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
        String sql = "DELETE FROM AUTEUR WHERE aut_id = ?";
        try(
                Connection connection = DatabaseConnection.getInstanceDB();
                PreparedStatement ps = connection.prepareStatement(sql)
        ){
            ps.setInt(1, id);
            ps.executeUpdate();
        }
        return id;
    }
}
