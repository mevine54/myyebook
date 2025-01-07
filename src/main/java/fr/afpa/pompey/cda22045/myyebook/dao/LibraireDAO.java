package fr.afpa.pompey.cda22045.myyebook.dao;

import fr.afpa.pompey.cda22045.myyebook.model.Libraire;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibraireDAO {
    private Connection connection;

    public LibraireDAO(Connection connection) {
        this.connection = connection;
    }

    public void save(Libraire libraire) throws SQLException {
        String sql = "INSERT INTO Libraire (lib_id, lib_nom, lib_prenom) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, libraire.getLibId());
            statement.setString(2, libraire.getLibNom());
            statement.setString(3, libraire.getLibPrenom());
            statement.executeUpdate();
        }
    }

    public Libraire findById(int libId) throws SQLException {
        String sql = "SELECT * FROM Libraire WHERE lib_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, libId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Libraire(
                        resultSet.getInt("lib_id"),
                        resultSet.getString("lib_nom"),
                        resultSet.getString("lib_prenom")
                );
            }
        }
        return null;
    }

    public List<Libraire> findAll() throws SQLException {
        List<Libraire> libraires = new ArrayList<>();
        String sql = "SELECT * FROM Libraire";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                libraires.add(new Libraire(
                        resultSet.getInt("lib_id"),
                        resultSet.getString("lib_nom"),
                        resultSet.getString("lib_prenom")
                ));
            }
        }
        return libraires;
    }

    public void update(Libraire libraire) throws SQLException {
        String sql = "UPDATE Libraire SET lib_nom = ?, lib_prenom = ? WHERE lib_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, libraire.getLibNom());
            statement.setString(2, libraire.getLibPrenom());
            statement.setInt(3, libraire.getLibId());
            statement.executeUpdate();
        }
    }

    public void delete(int libId) throws SQLException {
        String sql = "DELETE FROM Libraire WHERE lib_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, libId);
            statement.executeUpdate();
        }
    }
}
