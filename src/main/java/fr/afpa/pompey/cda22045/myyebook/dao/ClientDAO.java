package fr.afpa.pompey.cda22045.myyebook.dao;

import fr.afpa.pompey.cda22045.myyebook.model.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class ClientDAO {

    public void save(Client client) throws SQLException {
        String sql = "INSERT INTO Client (cli_id, cli_nom, cli_prenom, cli_email) VALUES (?, ?, ?, ?)";
        try (   Connection connection = DatabaseConnection.getInstanceDB();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, client.getCliId());
            statement.setString(2, client.getCliNom());
            statement.setString(3, client.getCliPrenom());
            statement.setString(4, client.getCliEmail());
            statement.executeUpdate();
        }
    }

    public Client findById(int cliId) throws SQLException {
        String sql = "SELECT * FROM Client WHERE cli_id = ?";
        try (   Connection connection = DatabaseConnection.getInstanceDB();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, cliId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Client(
                        resultSet.getInt("cli_id"),
                        resultSet.getString("cli_nom"),
                        resultSet.getString("cli_prenom"),
                        resultSet.getString("cli_email")
                );
            }
        }
        return null;
    }

    public List<Client> findAll() throws SQLException {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM Client";
        try (   Connection connection = DatabaseConnection.getInstanceDB();
                PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                clients.add(new Client(
                        resultSet.getInt("cli_id"),
                        resultSet.getString("cli_nom"),
                        resultSet.getString("cli_prenom"),
                        resultSet.getString("cli_email")
                ));
            }
        }
        return clients;
    }

    public void update(Client client) throws SQLException {
        String sql = "UPDATE Client SET cli_nom = ?, cli_prenom = ?, cli_email = ? WHERE cli_id = ?";
        try (   Connection connection = DatabaseConnection.getInstanceDB();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, client.getCliNom());
            statement.setString(2, client.getCliPrenom());
            statement.setString(3, client.getCliEmail());
            statement.setInt(4, client.getCliId());
            statement.executeUpdate();
        }
    }

    public void delete(int cliId) throws SQLException {
        String sql = "DELETE FROM Client WHERE cli_id = ?";
        try (   Connection connection = DatabaseConnection.getInstanceDB();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, cliId);
            statement.executeUpdate();
        }
    }
}
