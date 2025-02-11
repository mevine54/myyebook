package fr.afpa.pompey.cda22045.myyebook.dao.emprunterdao;

import fr.afpa.pompey.cda22045.myyebook.connectionbdd.DatabaseConnection;
import fr.afpa.pompey.cda22045.myyebook.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmprunterDAOImpl implements EmprunterDAO {

    @Override
    public Integer insert(Emprunter emprunter) throws SQLException {
        String sql = "INSERT INTO Emprunter (emp_res_date,emp_date_emprunt, emp_date_retour, cli_id, lib_id, liv_id) VALUES (?, ?, ?,?, ?,?)";

        try (Connection connection = DatabaseConnection.getInstanceDB();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setTimestamp(1, Timestamp.valueOf(emprunter.getDatetimeReservation()));
            ps.setTimestamp(2, Timestamp.valueOf(emprunter.getDatetimeEmprunt()));
            if (emprunter.getDatetimeRetour() == null) {
                ps.setNull(3, Types.TIMESTAMP);
            } else {
                ps.setTimestamp(3, Timestamp.valueOf(emprunter.getDatetimeRetour()));
            }
            ps.setInt(4, emprunter.getClient().getClientId());
            ps.setInt(5, emprunter.getLibraire().getLibId());
            ps.setInt(6, emprunter.getLivre().getId());


            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer update(Emprunter emprunter) throws SQLException {
        String sql = "UPDATE Emprunter SET cli_id = ?, liv_id = ?, emp_date_emprunt = ?, emp_date_retour = ?, emp_date_emprunt = ?, lib_id = ? WHERE emp_id = ? ";

        try (Connection connection = DatabaseConnection.getInstanceDB();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, emprunter.getClient().getClientId());
            ps.setInt(2, emprunter.getLivre().getId());
            ps.setTimestamp(3, Timestamp.valueOf(emprunter.getDatetimeEmprunt()));
            ps.setTimestamp(4, Timestamp.valueOf(emprunter.getDatetimeRetour()));
            ps.setTimestamp(5, Timestamp.valueOf(emprunter.getDatetimeRetour()));
            ps.setInt(6, emprunter.getLibraire().getLibId());
            ps.setInt(7, emprunter.getId());
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer delete(int id) throws SQLException {
        String sql = "DELETE FROM Emprunter WHERE emp_id = ?";
        try (Connection connection = DatabaseConnection.getInstanceDB();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Emprunter get(Integer id) throws SQLException {
        Emprunter emprunter = null;
        String sql = "SELECT * FROM Emprunter e  " +
                "INNER JOIN Client c ON e.cli_id = c.cli_id  " +
                "INNER JOIN Libraire l ON e.lib_id = l.lib_id  " +
                "INNER JOIN Livre li ON e.liv_id = li.liv_id  " +
                "INNER JOIN Auteur a ON li.aut_id = a.aut_id  " +
                "INNER JOIN Compte cptcli ON cptcli.cpt_id = c.cpt_id  " +
                "INNER JOIN Compte cptlib ON cptlib.cpt_id = l.cpt_id  " +
                "INNER JOIN Categorie cat ON cat.cat_id = li.cat_id  " +
                "WHERE emp_id = 1";

        try (Connection connection = DatabaseConnection.getInstanceDB();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                Compte compteClient = new Compte(
                        resultSet.getInt("c.cpt_id"),
                        resultSet.getString("cptcli.cpt_login"),
                        resultSet.getString("cptcli.cpt_mdp"),
                        resultSet.getString("cptcli.cpt_role")
                );

                Client client = new Client(
                        compteClient,
                        resultSet.getInt("cli_id"),
                        resultSet.getString("c.cli_nom"),
                        resultSet.getString("c.cli_prenom"),
                        resultSet.getString("c.cli_email"),
                        resultSet.getString("c.cli_adresse"),
                        resultSet.getString("c.cli_ville"),
                        resultSet.getString("c.cli_code_postale")
                );

                Categorie categorie = new Categorie(
                        resultSet.getInt("cat_id"),
                        resultSet.getString("cat_nom")
                );

                Auteur auteur = new Auteur(
                        resultSet.getInt("aut_id"),
                        resultSet.getString("aut_nom"),
                        resultSet.getString("aut_prenom"),
                        resultSet.getString("aut_photo")
                );

                Livre livre = new Livre(
                        resultSet.getInt("liv_id"),
                        resultSet.getString("liv_titre"),
                        resultSet.getString("liv_resume"),
                        resultSet.getString("liv_photo"),
                        resultSet.getBoolean("liv_en_avant"),
                        auteur,
                        categorie,
                        resultSet.getInt("quantite")
                );


                Compte compteLibraire = new Compte(
                        resultSet.getInt("l.cpt_id"),
                        resultSet.getString("cptlib.cpt_login"),
                        resultSet.getString("cptlib.cpt_mdp"),
                        resultSet.getString("cptlib.cpt_role")
                );

                Libraire libraire = new Libraire(
                        compteLibraire,
                        resultSet.getInt("lib_id"),
                        resultSet.getBoolean("lib_est_approuve"),
                        resultSet.getString("lib_nom"),
                        resultSet.getString("lib_prenom")
                );

                emprunter = new Emprunter(
                        resultSet.getInt("emp_id"),
                        client,
                        libraire,
                        livre,
                        resultSet.getTimestamp("emp_res_date").toLocalDateTime(),
                        resultSet.getTimestamp("emp_date_emprunt").toLocalDateTime(),
                        resultSet.getTimestamp("emp_date_retour") != null ? resultSet.getTimestamp("emp_date_retour").toLocalDateTime() : null
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return emprunter;
    }

    @Override
    public List<Emprunter> getAll() throws SQLException {
        List<Emprunter> emprunterList = new ArrayList<>();
        String sql = "SELECT * FROM Emprunter e  " +
                "INNER JOIN Client c ON e.cli_id = c.cli_id  " +
                "INNER JOIN Libraire l ON e.lib_id = l.lib_id  " +
                "INNER JOIN Livre li ON e.liv_id = li.liv_id  " +
                "INNER JOIN Auteur a ON li.aut_id = a.aut_id  " +
                "INNER JOIN Compte cptcli ON cptcli.cpt_id = c.cpt_id  " +
                "INNER JOIN Compte cptlib ON cptlib.cpt_id = l.cpt_id  " +
                "INNER JOIN Categorie cat ON cat.cat_id = li.cat_id  " +
                "WHERE emp_id = 1";

        try (Connection connection = DatabaseConnection.getInstanceDB();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {

                Compte compteClient = new Compte(
                        resultSet.getInt("c.cpt_id"),
                        resultSet.getString("cptcli.cpt_login"),
                        resultSet.getString("cptcli.cpt_mdp"),
                        resultSet.getString("cptcli.cpt_role")
                );

                Client client = new Client(
                        compteClient,
                        resultSet.getInt("cli_id"),
                        resultSet.getString("c.cli_nom"),
                        resultSet.getString("c.cli_prenom"),
                        resultSet.getString("c.cli_email"),
                        resultSet.getString("c.cli_adresse"),
                        resultSet.getString("c.cli_ville"),
                        resultSet.getString("c.cli_code_postale")
                );

                Categorie categorie = new Categorie(
                        resultSet.getInt("cat_id"),
                        resultSet.getString("cat_nom")
                );

                Auteur auteur = new Auteur(
                        resultSet.getInt("aut_id"),
                        resultSet.getString("aut_nom"),
                        resultSet.getString("aut_prenom"),
                        resultSet.getString("aut_photo")
                );

                Livre livre = new Livre(
                        resultSet.getInt("liv_id"),
                        resultSet.getString("liv_titre"),
                        resultSet.getString("liv_resume"),
                        resultSet.getString("liv_photo"),
                        resultSet.getBoolean("liv_en_avant"),
                        auteur,
                        categorie,
                        resultSet.getInt("quantite")
                );

                Compte compteLibraire = new Compte(
                        resultSet.getInt("l.cpt_id"),
                        resultSet.getString("cptlib.cpt_login"),
                        resultSet.getString("cptlib.cpt_mdp"),
                        resultSet.getString("cptlib.cpt_role")
                );

                Libraire libraire = new Libraire(
                        compteLibraire,
                        resultSet.getInt("lib_id"),
                        resultSet.getBoolean("lib_est_approuve"),
                        resultSet.getString("lib_nom"),
                        resultSet.getString("lib_prenom")
                );

                Emprunter emprunter = new Emprunter(
                        resultSet.getInt("emp_id"),
                        client,
                        libraire,
                        livre,
                        resultSet.getTimestamp("emp_res_date").toLocalDateTime(),
                        resultSet.getTimestamp("emp_date_emprunt").toLocalDateTime(),
                        resultSet.getTimestamp("emp_date_retour") != null ? resultSet.getTimestamp("emp_date_retour").toLocalDateTime() : null
                );

                emprunterList.add(emprunter);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return emprunterList;
    }
}
