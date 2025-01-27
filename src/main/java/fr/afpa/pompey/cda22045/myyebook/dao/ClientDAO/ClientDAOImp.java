package fr.afpa.pompey.cda22045.myyebook.dao.ClientDAO;

import fr.afpa.pompey.cda22045.myyebook.ConnectionBDD.DatabaseConnection;
import fr.afpa.pompey.cda22045.myyebook.model.Client;
import fr.afpa.pompey.cda22045.myyebook.model.Compte;
import fr.afpa.pompey.cda22045.myyebook.model.Emprunter;
import fr.afpa.pompey.cda22045.myyebook.model.Reservation;

import java.sql.*;
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
        String selectByID = ("select * from client c " +
                "JOIN compte cpt ON c.cpt_id = cpt.cpt_id " +
                "WHERE cli_id=?");

        try(Connection connection = DatabaseConnection.getInstanceDB();
            PreparedStatement ps = connection.prepareStatement(selectByID)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                client.setClientId(rs.getInt("cli_id"));
                client.setNom(rs.getString("cli_nom"));
                client.setPrenom(rs.getString("cli_prenom"));
                client.setEmail(rs.getString("cli_email"));
                client.setAdresse(rs.getString("cli_adresse"));
                client.setVille(rs.getString("cli_ville"));
                client.setCodePostal(rs.getString("cli_code_postale"));

//                Création de l'objet Compte associé au client
                Compte compte = new Compte();
                compte.setCompteId(rs.getInt("cpt_id"));
                compte.setLogin(rs.getString("cpt_login"));
                compte.setPassword(rs.getString("cpt_mdp"));

                client.setCompteId(compte.getCompteId());
                client.setLogin(compte.getLogin());
                client.setPassword(compte.getPassword());


            }
        } catch (SQLException e) {
            throw new SQLException("Impossible de trouver le client",e);
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
        String sql = "select * from client c " +
                "JOIN compte cpt ON c.cpt_id = cpt.cpt_id " +
                "JOIN emprunter e ON c.cli_id = e.cli_id " +
                "JOIN reservation r ON c.cli_id = r.cli_id";

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

//                Compte compte = new Compte();
//                compte.setCompteId(rs.getInt("cpt_id"));
//                compte.setLogin(rs.getString("cpt_login"));
//                compte.setPassword(rs.getString("cpt_mdp"));

                Emprunter emprunter = new Emprunter();
                emprunter.setId(rs.getInt("emp_id"));
                Timestamp empruntDate = rs.getTimestamp("emp_date_emprunt");
                Timestamp retourDate = rs.getTimestamp("emp_date_retour");

                if (empruntDate != null) {
                    emprunter.setDatetimeEmprunt(empruntDate.toLocalDateTime());
                }
                if (retourDate != null) {
                    emprunter.setDatetimeRetour(retourDate.toLocalDateTime());
                }

                Reservation reservation = new Reservation();
                reservation.setResId(rs.getInt("res_id"));
                Timestamp resDate = rs.getTimestamp("res_date");

                if (resDate != null) {
                    reservation.setDatetime(resDate.toLocalDateTime());
                }
            }
        }catch (SQLException e) {
            throw new SQLException("Impossible d'afficher les clients", e);
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
        String insertClient = "INSERT INTO CLIENT(cli_nom, cli_prenom, cli_email, cli_adresse, cli_ville, cli_code_postale, cpt_id) VALUES (?,?,?,?,?,?,?)";
        int newID = 0;

        try (   Connection connection = DatabaseConnection.getInstanceDB();
                PreparedStatement ps = connection.prepareStatement(insertClient, Statement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, client.getNom());
            ps.setString(2, client.getPrenom());
            ps.setString(3, client.getEmail());
            ps.setString(4, client.getAdresse());
            ps.setString(5, client.getVille());
            ps.setString(6, client.getCodePostal());
            ps.setInt(7, client.getCompteId());


            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()) {
                newID = rs.getInt(1);
            }

        } catch (SQLException e) {
            throw new SQLException("Imposible d'inserer le client", e);
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
        String updateClient = "UPDATE CLIENT SET cli_nom = ?, cli_prenom = ?, cli_email = ?, cli_adresse = ?, cli_ville = ?, cli_code_postale = ? WHERE cli_id = ?";

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
            ps.setInt(7, client.getClientId());

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
