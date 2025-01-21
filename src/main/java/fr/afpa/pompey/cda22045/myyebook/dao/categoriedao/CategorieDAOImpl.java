package fr.afpa.pompey.cda22045.myyebook.dao.categoriedao;

import fr.afpa.pompey.cda22045.myyebook.ConnectionBDD.DatabaseConnection;
import fr.afpa.pompey.cda22045.myyebook.model.Categorie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategorieDAOImpl implements CategorieDAO {
    @Override
    public Categorie get(Integer id) throws SQLException {
        Categorie categorie = null;
        String sql = "SELECT * FROM Categorie WHERE cat_id = ?";

        try (Connection connection = DatabaseConnection.getInstanceDB();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                categorie = new Categorie(
                        resultSet.getInt("cat_id"),
                        resultSet.getString("cat_nom"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categorie;
    }

    @Override
    public List<Categorie> getAll() throws SQLException {
        List<Categorie> categories = new ArrayList<>();
        String sql = "SELECT * FROM Categorie";

        try (Connection connection = DatabaseConnection.getInstanceDB();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Categorie categorie = new Categorie(
                resultSet.getInt("cat_id"),
                resultSet.getString("cat_nom")
                );
                categories.add(categorie);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categories;
    }

    @Override
    public int insert(Categorie categorie) throws SQLException {
        String sql = "INSERT INTO Categorie (cat_nom) VALUES (?)";
        Integer id = null;
        try (Connection connection = DatabaseConnection.getInstanceDB();
             PreparedStatement ps = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, categorie.getNom());
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
    public int update(Categorie categorie) throws SQLException {
        String sql = "UPDATE Categorie SET cat_nom = ? WHERE cat_id = ?";

        try (Connection connection = DatabaseConnection.getInstanceDB();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, categorie.getNom());
            ps.setInt(2, categorie.getId());
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int delete(Integer id) throws SQLException {
        String sql = "DELETE FROM Categorie WHERE cat_id = ?";
        try (Connection connection = DatabaseConnection.getInstanceDB();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Categorie> getParNom(String nom) throws SQLException {
        List<Categorie> categories = new ArrayList<>();
        String sql = "SELECT * FROM Categorie WHERE cat_nom LIKE ?";

        try (Connection connection = DatabaseConnection.getInstanceDB();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, "%" + nom + "%");
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Categorie categorie = null;
                categorie.setId(resultSet.getInt("cat_id"));
                categorie.setNom(resultSet.getString("cat_nom"));
                categories.add(categorie);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categories;
    }

    @Override
    public Integer getNbCategories() throws SQLException {
        String sql = "SELECT COUNT(*) AS nb_categories FROM Categorie";

        try (Connection connection = DatabaseConnection.getInstanceDB();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet resultSet = ps.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt("nb_categories");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
