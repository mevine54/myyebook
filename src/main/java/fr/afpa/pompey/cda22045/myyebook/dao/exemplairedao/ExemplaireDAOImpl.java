package fr.afpa.pompey.cda22045.myyebook.dao.exemplairedao;

import fr.afpa.pompey.cda22045.myyebook.connectionbdd.DatabaseConnection;
import fr.afpa.pompey.cda22045.myyebook.model.Auteur;
import fr.afpa.pompey.cda22045.myyebook.model.Categorie;
import fr.afpa.pompey.cda22045.myyebook.model.Exemplaire;
import fr.afpa.pompey.cda22045.myyebook.model.Livre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExemplaireDAOImpl implements ExemplaireDAO {

    @Override
    public Exemplaire get(Integer id) throws SQLException {
        Exemplaire exemplaire = null;
        String sql = "SELECT * FROM exemplaire e\n" +
                "INNER JOIN livre l ON l.liv_id = e.liv_id\n" +
                "INNER JOIN auteur a ON a.aut_id = l.aut_id\n" +
                "INNER JOIN categorie c ON c.cat_id = l.cat_id\n" +
                " WHERE exe_id = ?";

        try (Connection connection = DatabaseConnection.getInstanceDB();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                Auteur auteur = new Auteur(
                        resultSet.getInt("aut_id"),
                        resultSet.getString("aut_nom"),
                        resultSet.getString("aut_prenom"),
                        resultSet.getString("aut_photo")
                );
                Categorie categorie = new Categorie(
                        resultSet.getInt("cat_id"),
                        resultSet.getString("cat_nom")
                );
                Livre livre = new Livre(
                        resultSet.getInt("liv_id"),
                        resultSet.getString("liv_titre"),
                        resultSet.getString("liv_resume"),
                        resultSet.getString("liv_photo"),
                        auteur, categorie
                );

                exemplaire = new Exemplaire(
                        resultSet.getInt("exe_id"),
                        livre);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return exemplaire;
    }

    @Override
    public List<Exemplaire> getAll() throws SQLException {
        List<Exemplaire> exemplaires = new ArrayList<Exemplaire>();
        String sql = "SELECT * FROM exemplaire e " +
                "INNER JOIN livre l ON l.liv_id = e.liv_id " +
                "INNER JOIN auteur a ON a.aut_id = l.aut_id\n" +
                "INNER JOIN categorie c ON c.cat_id = l.cat_id;";

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
                Categorie categorie = new Categorie(
                        resultSet.getInt("cat_id"),
                        resultSet.getString("cat_nom")
                );
                Livre livre = new Livre(
                        resultSet.getInt("liv_id"),
                        resultSet.getString("liv_titre"),
                        resultSet.getString("liv_resume"),
                        resultSet.getString("liv_photo"),
                        auteur, categorie
                );

                Exemplaire exemplaire = new Exemplaire(
                        resultSet.getInt("exe_id"),
                        livre);
                exemplaires.add(exemplaire);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return exemplaires;
    }

    @Override
    public int insert(Exemplaire exemplaire) throws SQLException {
        String sql = "INSERT INTO myyebook.exemplaire (liv_id) VALUES ( ?)";
        Integer id = null;
        try (Connection connection = DatabaseConnection.getInstanceDB();
             PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, exemplaire.getLivre().getId());
//            ps.setBoolean(4, livre.getEnAvant());
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
    public int update(Exemplaire exemplaire) throws SQLException {
        String sql = "UPDATE exemplaire SET liv_id = ? WHERE exe_id = ?";

        try (Connection connection = DatabaseConnection.getInstanceDB();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, exemplaire.getLivre().getId());
            ps.setInt(2, exemplaire.getExemplaireId());
            if (ps.executeUpdate() > 0) {
                return exemplaire.getExemplaireId();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public int delete(Integer id) throws SQLException {
        String sql = "DELETE FROM exemplaire WHERE exe_id = ?";
        try (Connection connection = DatabaseConnection.getInstanceDB();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            if (ps.executeUpdate() > 0) {
                return id;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  0;
    }
}
