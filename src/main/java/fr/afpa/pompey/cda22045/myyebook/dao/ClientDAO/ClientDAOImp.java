package fr.afpa.pompey.cda22045.myyebook.dao.ClientDAO;

import fr.afpa.pompey.cda22045.myyebook.ConnectionBDD.DatabaseConnection;
import fr.afpa.pompey.cda22045.myyebook.model.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDAOImp implements ClientDAO {
    /**
     * Get t.
     *
     * @param id the id
     * @return the t
     * @throws SQLException the sql exception
     */
    @Override
    public Client get(Integer id) throws SQLException {
        Client client = new Client();
        String selectByID = ("select * from client where cl_id=?");

        try(Connection connection = DatabaseConnection.getInstanceDB();
            PreparedStatement ps = connection.prepareStatement(selectByID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                client.setCompteId(rs.getInt("cpt_id"));
                client.setNom(rs.getString("cpt_login"));
                client.setPrenom(rs.getString("cpt_mdp"));
                client.setClientId(rs.getInt("cli_id"));
                client.setNom(rs.getString("cli_nom"));
                client.setPrenom(rs.getString("cli_prenom"));
                client.setEmail(rs.getString("cli_email"));
                client.setAdresse(rs.getString("cli_adresse"));
                client.setVille(rs.getString("cli_ville"));
                client.setCodePostal(rs.getString("cl_code_postale"));

                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new SQLException("Imosible de trouver le client",e);
        }
        return client;
    }


    /**
     * Gets all.
     *
     * @return the all
     * @throws SQLException the sql exception
     */
    @Override
    public List<Client> getAll() throws SQLException {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM Client cl" +
                "join compte cp on cl.cpt_id=cp.cpt_id ";

        try (   Connection connection = DatabaseConnection.getInstanceDB();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                clients.add(new Client(
                        rs.getInt("cli_id"),
                        rs.getInt("cpt_id"),
                        rs.getString("cpt_login"),
                        rs.getString("cpt_mdp"),
                        rs.getString("cli_nom"),
                        rs.getString("cli_prenom"),
                        rs.getString("cli_email"),
                        rs.getString("cli_adresse"),
                        rs.getString("cli_ville"),
                        rs.getString("cli_code_postale")
                ));
            }
        }catch (SQLException e) {
            throw new SQLException("Imosible d'afficher les clients", e);
        }
        return clients;
    }

    /**
     * Insert int.
     *
     * @param client the t
     * @return the int
     * @throws SQLException the sql exception
     */
    @Override
    public int insert(Client client) throws SQLException {
        String insertClient = "INSERT INTO CLIENT(cli_nom, cli_prenom, cli_email, cli_adresse, cli_ville, cli_code_postale) VALUES (?,?,?,?,?,?)";
        int newID = 0;

        try (   Connection connection = DatabaseConnection.getInstanceDB();
                PreparedStatement ps = connection.prepareStatement(insertClient)
        ) {
            ps.setString(1, client.getNom());
            ps.setString(2, client.getPrenom());
            ps.setString(3, client.getEmail());
            ps.setString(4, client.getAdresse());
            ps.setString(5, client.getVille());
            ps.setString(6, client.getCodePostal());

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()) {
                newID = rs.getInt(1);
            }

        } catch (SQLException e) {
            throw new SQLException("Imosible d'inserer le client", e);
        }



        return newID;
    }

    /**
     * Update int.
     *
     * @param client the t
     * @return the int
     * @throws SQLException the sql exception
     */
    @Override
    public int update(Client client) throws SQLException {
        String updateClient = "UPDATE CLIENT SET cli_nom, cli_prenom, cli_email, cli_adresse, cli_ville, cli_code_postale WHERE cli_id= ?";

        try (
                Connection connection = DatabaseConnection.getInstanceDB();
                PreparedStatement ps = connection.prepareStatement(updateClient))
        {
            ps.setString(1, client.getNom());
            ps.setString(2, client.getPrenom());
            ps.setString(3, client.getEmail());
            ps.setString(4, client.getAdresse());
            ps.setString(5, client.getVille());
            ps.setString(6, client.getCodePostal());

            return  ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Imosible de mettre à jour le client", e);
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
    public int delete(Integer id) throws SQLException {
        //        Voir pour ajouter DELETE ON CASCAD dans le script
        String deleteClient = "DELETE FROM CLIENT WHERE cli_id= ?";
        try (
                Connection connection = DatabaseConnection.getInstanceDB();
                PreparedStatement ps = connection.prepareStatement(deleteClient)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
        return id;
    }
}
