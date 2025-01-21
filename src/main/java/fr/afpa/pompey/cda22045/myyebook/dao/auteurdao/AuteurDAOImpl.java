package fr.afpa.pompey.cda22045.myyebook.dao.auteurdao;

import fr.afpa.pompey.cda22045.myyebook.connectionbdd.DatabaseConnection;
import fr.afpa.pompey.cda22045.myyebook.model.Auteur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuteurDAOImpl implements AuteurDAO {

    @Override
    public Auteur get(Integer id) throws SQLException {
        Auteur auteur = null;
        String sql = "SELECT * FROM Auteur WHERE aut_id = ?";

        try (Connection connection = DatabaseConnection.getInstanceDB();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                auteur = new Auteur(
                        resultSet.getInt("aut_id"),
                        resultSet.getString("aut_nom"),
                        resultSet.getString("aut_prenom"),
                        resultSet.getString("aut_photo")

                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return auteur;
    }

    @Override
    public List<Auteur> getAll() throws SQLException {
        List<Auteur> auteurs = new ArrayList<>();
        String sql = "SELECT * FROM Auteur";

        try (Connection connection = DatabaseConnection.getInstanceDB();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Auteur auteur = new Auteur(
                        resultSet.getInt("aut_id"),
                        resultSet.getString("aut_nom"),
                        resultSet.getString("aut_prenom"),
                        resultSet.getString("aut_photo")

                );
                auteurs.add(auteur);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return auteurs;
    }

    @Override
    public int insert(Auteur auteur) throws SQLException {
        String sql = "INSERT INTO Auteur (aut_nom, aut_prenom,aut_photo) VALUES (?, ?,?)";
        Integer id = null;
        try (Connection connection = DatabaseConnection.getInstanceDB();
             PreparedStatement ps = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, auteur.getNom());
            ps.setString(2, auteur.getPrenom());
            ps.setString(3, auteur.getPhoto());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = generatedKeys.getInt(1);
            }
            return id;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(Auteur auteur) throws SQLException {
        String sql = "UPDATE Auteur SET aut_nom = ?, aut_prenom = ? , aut_photo = ? WHERE aut_id = ?";

        try (Connection connection = DatabaseConnection.getInstanceDB();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, auteur.getNom());
            ps.setString(2, auteur.getPrenom());
            ps.setString(3, auteur.getPhoto());
            ps.setInt(4, auteur.getAuteurId());
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int delete(Integer id) throws SQLException {
        String sql = "DELETE FROM Auteur WHERE aut_id = ?";

        try (Connection connection = DatabaseConnection.getInstanceDB();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Auteur> getParNom(String nom) throws SQLException {
        List<Auteur> auteurs = new ArrayList<>();
        String sql = "SELECT * FROM Auteur WHERE aut_nom LIKE ?";

        try (Connection connection = DatabaseConnection.getInstanceDB();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, "%" + nom + "%");
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Auteur auteur = null;
                auteur.setAuteurId(resultSet.getInt("aut_id"));
                auteur.setNom(resultSet.getString("aut_nom"));
                auteur.setPrenom(resultSet.getString("aut_prenom"));
                auteurs.add(auteur);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return auteurs;
    }

    @Override
    public Integer getNbAuteurs() throws SQLException {
        String sql = "SELECT COUNT(*) AS nb_auteurs FROM Auteur";

        try (Connection connection = DatabaseConnection.getInstanceDB();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet resultSet = ps.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt("nb_auteurs");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}

