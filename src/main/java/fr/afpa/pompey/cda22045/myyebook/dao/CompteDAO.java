package fr.afpa.pompey.cda22045.myyebook.dao;

import fr.afpa.pompey.cda22045.myyebook.model.Compte;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompteDAO {
    private Connection connection;

    public CompteDAO(Connection connection) {
        this.connection = connection;
    }

    public void save(Compte compte) throws SQLException {
        String sql = "INSERT INTO Compte (sec_id, sec_login, sec_pass) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, compte.getSecId());
            statement.setString(2, compte.getSecLogin());
            statement.setString(3, compte.getSecPass());
            statement.executeUpdate();
        }
    }

    public Compte findById(int secId) throws SQLException {
        String sql = "SELECT * FROM Compte WHERE sec_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, secId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Compte(
                        resultSet.getInt("sec_id"),
                        resultSet.getString("sec_login"),
                        resultSet.getString("sec_pass")
                );
            }
        }
        return null;
    }

    public List<Compte> findAll() throws SQLException {
        List<Compte> comptes = new ArrayList<>();
        String sql = "SELECT * FROM Compte";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                comptes.add(new Compte(
                        resultSet.getInt("sec_id"),
                        resultSet.getString("sec_login"),
                        resultSet.getString("sec_pass")
                ));
            }
        }
        return comptes;
    }

    public void update(Compte compte) throws SQLException {
        String sql = "UPDATE Compte SET sec_login = ?, sec_pass = ? WHERE sec_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, compte.getSecLogin());
            statement.setString(2, compte.getSecPass());
            statement.setInt(3, compte.getSecId());
            statement.executeUpdate();
        }
    }

    public void delete(int secId) throws SQLException {
        String sql = "DELETE FROM Compte WHERE sec_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, secId);
            statement.executeUpdate();
        }
    }
}
