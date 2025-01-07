package fr.afpa.pompey.cda22045.myyebook.dao;

import fr.afpa.pompey.cda22045.myyebook.model.Livre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivreDAO {
    private Connection connection;

    public LivreDAO(Connection connection) {
        this.connection = connection;
    }

    public void save(Livre livre) throws SQLException {
        String sql = "INSERT INTO Livre (liv_id, liv_titre, liv_resume, liv_image, liv_quantite) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, livre.getLivId());
            statement.setString(2, livre.getLivTitre());
            statement.setString(3, livre.getLivResume());
            statement.setBytes(4, livre.getLivImage());
            statement.setInt(5, livre.getLivQuantite());
            statement.executeUpdate();
        }
    }

    public Livre findById(int livId) throws SQLException {
        String sql = "SELECT * FROM Livre WHERE liv_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, livId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Livre(
                        resultSet.getInt("liv_id"),
                        resultSet.getString("liv_titre"),
                        resultSet.getString("liv_resume"),
                        resultSet.getBytes("liv_image"),
                        resultSet.getInt("liv_quantite")
                );
            }
        }
        return null;
    }

    public List<Livre> findAll() throws SQLException {
        List<Livre> livres = new ArrayList<>();
        String sql = "SELECT * FROM Livre";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                livres.add(new Livre(
                        resultSet.getInt("liv_id"),
                        resultSet.getString("liv_titre"),
                        resultSet.getString("liv_resume"),
                        resultSet.getBytes("liv_image"),
                        resultSet.getInt("liv_quantite")
                ));
            }
        }
        return livres;
    }

    public void update(Livre livre) throws SQLException {
        String sql = "UPDATE Livre SET liv_titre = ?, liv_resume = ?, liv_image = ?, liv_quantite = ? WHERE liv_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, livre.getLivTitre());
            statement.setString(2, livre.getLivResume());
            statement.setBytes(3, livre.getLivImage());
            statement.setInt(4, livre.getLivQuantite());
            statement.setInt(5, livre.getLivId());
            statement.executeUpdate();
        }
    }

    public void delete(int livId) throws SQLException {
        String sql = "DELETE FROM Livre WHERE liv_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, livId);
            statement.executeUpdate();
        }
    }
}

