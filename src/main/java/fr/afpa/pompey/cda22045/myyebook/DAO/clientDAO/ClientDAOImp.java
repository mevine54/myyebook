package fr.afpa.pompey.cda22045.myyebook.DAO.clientDAO;

import fr.afpa.pompey.cda22045.myyebook.ConnectionBDD.DatabaseConnection;
import fr.afpa.pompey.cda22045.myyebook.model.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Client dao imp.
 */
public class ClientDAOImp implements ClientDAO {
    @Override
    public Client get(int id) throws SQLException {
        Client client = new Client();
        String sql = "INSERT INTO Client (cli_id, cli_nom, cli_prenom, cli_email) VALUES (?, ?, ?, ?)";

        try(Connection connection = DatabaseConnection.getInstanceDB();
            PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setInt(1, client.getCliId());
            statement.setString(2, client.getCliNom());
            statement.setString(3, client.getCliPrenom());
            statement.setString(4, client.getCliEmail());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return client;
    }

    @Override
    public List getAll() throws SQLException {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM Client";

        try (   Connection connection = DatabaseConnection.getInstanceDB();
                PreparedStatement statement = connection.prepareStatement(sql);
                ) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                clients.add(new Client(
                        resultSet.getInt("cli_id"),
                        resultSet.getString("cli_nom"),
                        resultSet.getString("cli_prenom"),
                        resultSet.getString("cli_email")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return clients;
    }


    @Override
    public int insert(Object o) throws SQLException {
        Client client = (Client) o;
        String sql = "INSERT INTO Client (cli_id, cli_nom, cli_prenom, cli_email) VALUES (?, ?, ?, ?)";
        int newID = 0;

        try (Connection connection = DatabaseConnection.getInstanceDB();
             PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, client.getCliId());
            statement.setString(2, client.getCliNom());
            statement.setString(3, client.getCliPrenom());
            statement.setString(4, client.getCliEmail());

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

    @Override
    public int update(Object o) throws SQLException {
        Client client = (Client) o;
        String sql = "UPDATE Client SET cli_nom = ?, cli_prenom = ?, cli_email = ? WHERE cli_id = ?";

        try(Connection connection = DatabaseConnection.getInstanceDB();
            PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setString(1, client.getCliNom());
            statement.setString(2, client.getCliPrenom());
            statement.setString(3, client.getCliEmail());
            statement.setInt(4, client.getCliId());

            statement.executeUpdate();
            int res = statement.executeUpdate();

            statement.close();
            connection.close();
            return res;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public int delete(int cliId) throws SQLException {
        String sql = "DELETE FROM Client WHERE cli_id = ?";

        try(Connection connection = DatabaseConnection.getInstanceDB();
            PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setInt(1, cliId);
            statement.executeUpdate();

        }

        return cliId;
    }
}
