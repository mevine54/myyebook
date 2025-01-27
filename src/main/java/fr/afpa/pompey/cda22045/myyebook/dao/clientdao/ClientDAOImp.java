package fr.afpa.pompey.cda22045.myyebook.dao.clientdao;

import fr.afpa.pompey.cda22045.myyebook.connectionbdd.DatabaseConnection;
import fr.afpa.pompey.cda22045.myyebook.model.Client;
import fr.afpa.pompey.cda22045.myyebook.model.Compte;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAOImp implements ClientDAO {

    @Override
    public Client get(Integer cliId) throws SQLException {
        Client client = null;
        Compte compte = null;
        String sql = "SELECT * FROM compte c INNER JOIN client cl on c.cpt_id = cl.cpt_id WHERE cli_id = ? ;";

        try (
                Connection connection = DatabaseConnection.getInstanceDB();
                PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, cliId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                compte = new Compte(
                        rs.getInt("cpt_id"),
                        rs.getString("cpt_login"),
                        rs.getString("cpt_mdp"),
                        rs.getString("cpt_role")
                );

                client = new Client(
                        rs.getInt("cli_id"),
                        compte,
                        rs.getString("cli_nom"),
                        rs.getString("cli_prenom"),
                        rs.getString("cli_email"),
                        rs.getString("cli_adresse"),
                        rs.getString("cli_ville"),
                        rs.getString("cli_code_postale")
                );
            }

        }

        return client;
    }

    @Override
    public List<Client> getAll() throws SQLException {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM compte c INNER JOIN client cl on c.cpt_id = cl.cpt_id";
        try (
                Connection connection = DatabaseConnection.getInstanceDB();
                PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Compte compte = new Compte(
                        rs.getInt("cpt_id"),
                        rs.getString("cpt_login"),
                        rs.getString("cpt_mdp"),
                        rs.getString("cpt_role")
                );

                Client client = new Client(
                        rs.getInt("cli_id"),
                        compte,
                        rs.getString("cli_nom"),
                        rs.getString("cli_prenom"),
                        rs.getString("cli_email"),
                        rs.getString("cli_adresse"),
                        rs.getString("cli_ville"),
                        rs.getString("cli_code_postale")
                );
                clients.add(client);
            }
        }
        return clients;
    }


    @Override
    public Integer insert(Client client) throws SQLException {
        String sql = "INSERT INTO Compte (cpt_login, cpt_mdp, cpt_role) VALUES (?, ?, ?)";
        Integer compteId = 0;
        try {

            Connection connection = DatabaseConnection.getInstanceDB();
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, client.getLogin());
            ps.setString(2, client.getPassword());
            ps.setString(3, client.getRole());
            ps.executeUpdate();
            ResultSet generatedKeysCompte = ps.getGeneratedKeys();
            if (generatedKeysCompte.next()) {
                sql = "INSERT INTO client (cli_nom, cli_prenom, cli_email, cli_adresse, cli_ville, cli_code_postale, cpt_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
                compteId = generatedKeysCompte.getInt(1);
                ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, client.getNom());
                ps.setString(2, client.getPrenom());
                ps.setString(3, client.getEmail());
                ps.setString(4, client.getAdresse());
                ps.setString(5, client.getVille());
                ps.setString(6, client.getCodePostal());
                ps.setInt(7, compteId);
                ps.executeUpdate();
                ResultSet generatedKeysClient = ps.getGeneratedKeys();
                if (generatedKeysClient.next()) {
                    int clientId = generatedKeysClient.getInt(1);
                    client.setClientId(clientId);
                    connection.commit();
                    return clientId;
                }
            }
            connection.rollback();
        } catch (SQLException e) {
            connection.rollback();
            throw new RuntimeException(e);
        } finally {
            connection.setAutoCommit(true);
        }
        return compteId;
    }

    @Override
    public Integer update(Client client) throws SQLException {
        String sql = "UPDATE Compte SET cpt_login = ?, cpt_mdp = ?, cpt_role = ? WHERE cpt_id = ?";
        try {
            Connection connection = DatabaseConnection.getInstanceDB();
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, client.getLogin());
            ps.setString(2, client.getPassword());
            ps.setString(3, client.getRole());
            ps.setInt(4, client.getCompteId());
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                sql = "UPDATE client SET cli_nom = ?, cli_prenom = ?, cli_email = ?, cli_adresse = ?, cli_ville = ?, cli_code_postale = ? WHERE cli_id = ?";
                ps = connection.prepareStatement(sql);
                ps.setString(1, client.getNom());
                ps.setString(2, client.getPrenom());
                ps.setString(3, client.getEmail());
                ps.setString(4, client.getAdresse());
                ps.setString(5, client.getVille());
                ps.setString(6, client.getCodePostal());
                ps.setInt(7, client.getClientId());
                rowsAffected = ps.executeUpdate();

                if (rowsAffected > 0) {
                    connection.commit();
                    return rowsAffected;
                }
            }
            connection.rollback();
        } catch (SQLException e) {
            connection.rollback();
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        } finally {
            connection.setAutoCommit(true);
        }
        return 0;
    }

    @Override
    public Integer delete(int id) throws SQLException {
        String sqlGetCompteId = "SELECT c.cpt_id FROM Compte c INNER JOIN client cl ON c.cpt_id = cl.cpt_id WHERE cl.cli_id = ?";
        String sqlDeleteClient = "DELETE FROM client WHERE cli_id = ?";
        String sqlDeleteCompte = "DELETE FROM Compte WHERE cpt_id = ?";

        try (
                Connection connection = DatabaseConnection.getInstanceDB();
                PreparedStatement psGetCompteId = connection.prepareStatement(sqlGetCompteId);
                PreparedStatement psDeleteClient = connection.prepareStatement(sqlDeleteClient);
                PreparedStatement psDeleteCompte = connection.prepareStatement(sqlDeleteCompte)
        ) {
            connection.setAutoCommit(false);

            // Get the compte ID associated with the client using INNER JOIN
            psGetCompteId.setInt(1, id);
            try (ResultSet rs = psGetCompteId.executeQuery()) {
                if (rs.next()) {
                    int compteId = rs.getInt("cpt_id");

                    // Delete the client
                    psDeleteClient.setInt(1, id);
                    psDeleteClient.executeUpdate();

                    // Delete the compte
                    psDeleteCompte.setInt(1, compteId);
                    psDeleteCompte.executeUpdate();

                    connection.commit();
                    return id;
                } else {
                    throw new SQLException("No compte found for clientId: " + id);
                }
            } catch (SQLException e) {
                connection.rollback();
                throw new RuntimeException(e);
            }
        }    }
}