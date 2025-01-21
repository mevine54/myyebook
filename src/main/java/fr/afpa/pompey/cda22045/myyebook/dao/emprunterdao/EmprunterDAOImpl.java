package fr.afpa.pompey.cda22045.myyebook.dao.emprunterdao;

import fr.afpa.pompey.cda22045.myyebook.connectionbdd.DatabaseManager;
import fr.afpa.pompey.cda22045.myyebook.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmprunterDAOImpl implements EmprunterDAO {

    @Override
    public int insert(Emprunter emprunter) throws SQLException {
        String sql = "INSERT INTO Emprunter (cli_id, emp_date_emprunt, emp_date_retour, cli_id, lib_id, exe_id,res_id) VALUES (?, ?, ?, ?,?)";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, emprunter.getClient().getClientId());
            ps.setTimestamp(2, Timestamp.valueOf(emprunter.getDatetimeEmprunt()));
            if (emprunter.getDatetimeRetour() == null) {
                ps.setNull(3, Types.TIMESTAMP);
            } else {
                ps.setTimestamp(3, Timestamp.valueOf(emprunter.getDatetimeRetour()));
            }
            ps.setInt(4, emprunter.getClient().getClientId());
            ps.setInt(5, emprunter.getLibraire().getLibId());
            ps.setInt(6, emprunter.getExemplaire().getExemplaireId());
            if (emprunter.getReservation() != null){
                ps.setInt(7, emprunter.getReservation().getResId());

            }else{
                ps.setNull(7, Types.INTEGER);
            }
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(Emprunter emprunter) throws SQLException {
        String sql = "UPDATE Emprunter SET cli_id = ?, exe_id = ?, emp_date_emprunt = ?, emp_date_retour = ?, res_id = ?, lib_id = ?, WHERE emp_id = ? ";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, emprunter.getClient().getClientId());
            ps.setInt(2, emprunter.getExemplaire().getExemplaireId());
            ps.setTimestamp(3, Timestamp.valueOf(emprunter.getDatetimeEmprunt()));
            ps.setTimestamp(4, Timestamp.valueOf(emprunter.getDatetimeRetour()));
            ps.setInt(5, emprunter.getReservation().getResId());
            ps.setInt(6, emprunter.getLibraire().getLibId());
            ps.setInt(7, emprunter.getId());
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int delete(Integer id) throws SQLException {
        String sql = "DELETE FROM Emprunter WHERE emp_id = ?";
        try (Connection connection = DatabaseManager.getConnection();
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
        String sql = "SELECT * FROM Emprunter e \n" +
                "INNER JOIN Client c ON e.cli_id = c.cli_id\n" +
                "INNER JOIN Exemplaire ex ON e.exe_id = ex.exe_id\n" +
                "LEFT JOIN Reservation r ON e.res_id = r.res_id\n" +
                "INNER JOIN Libraire l ON e.lib_id = l.lib_id\n" +
                "INNER JOIN Livre li ON ex.liv_id = li.liv_id\n" +
                "INNER JOIN Auteur a ON li.aut_id = a.aut_id\n" +
                "INNER JOIN Compte cptcli ON cptcli.cpt_id = c.cpt_id\n" +
                "INNER JOIN Compte cptlib ON cptlib.cpt_id = l.cpt_id\n" +
                "INNER JOIN Categorie cat ON cat.cat_id = li.cat_id\n" +
                "WHERE emp_id = ?";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                Client client = new Client(
                        resultSet.getInt("cli_id"),
                        resultSet.getInt("c.cpt_id"),
                        resultSet.getString("cptcli.cpt_login"),
                        resultSet.getString("cptcli.cpt_mdp"),
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
                        auteur,
                        categorie
                );

                Exemplaire exemplaire = new Exemplaire(
                        resultSet.getInt("exe_id"),
                        livre
                );

                Reservation reservation = new Reservation(
                        resultSet.getInt("res_id"),
                        client,
                        livre,
                        resultSet.getTimestamp("res_date").toLocalDateTime()
                );

                Libraire libraire = new Libraire(
                        resultSet.getInt("lib_id"),
                        resultSet.getInt("l.cpt_id"),
                        resultSet.getString("cptlib.cpt_login"),
                        resultSet.getString("cptlib.cpt_mdp"),
                        resultSet.getString("lib_nom"),
                        resultSet.getString("lib_prenom")
                );

                emprunter = new Emprunter(
                        resultSet.getInt("emp_id"),
                        client,
                        libraire,
                        exemplaire,
                        resultSet.getTimestamp("emp_date_emprunt").toLocalDateTime(),
                        resultSet.getTimestamp("emp_date_retour") != null ? resultSet.getTimestamp("emp_date_retour").toLocalDateTime() : null,
                        reservation
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
        String sql = "SELECT * FROM Emprunter e \n" +
                "INNER JOIN Client c ON e.cli_id = c.cli_id\n" +
                "INNER JOIN Exemplaire ex ON e.exe_id = ex.exe_id\n" +
                "LEFT JOIN Reservation r ON e.res_id = r.res_id\n" +
                "INNER JOIN Libraire l ON e.lib_id = l.lib_id\n" +
                "INNER JOIN Livre li ON ex.liv_id = li.liv_id\n" +
                "INNER JOIN Auteur a ON li.aut_id = a.aut_id\n" +
                "INNER JOIN Compte cptcli ON cptcli.cpt_id = c.cpt_id\n" +
                "INNER JOIN Compte cptlib ON cptlib.cpt_id = l.cpt_id\n" +
                "INNER JOIN Categorie cat ON cat.cat_id = li.cat_id";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Client client = new Client(
                        resultSet.getInt("cli_id"),
                        resultSet.getInt("c.cpt_id"),
                        resultSet.getString("cptcli.cpt_login"),
                        resultSet.getString("cptcli.cpt_mdp"),
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
                        auteur,
                        categorie
                );

                Exemplaire exemplaire = new Exemplaire(
                        resultSet.getInt("exe_id"),
                        livre
                );

                Reservation reservation = new Reservation(
                        resultSet.getInt("res_id"),
                        client,
                        livre,
                        resultSet.getTimestamp("res_date").toLocalDateTime()
                );

                Libraire libraire = new Libraire(
                        resultSet.getInt("lib_id"),
                        resultSet.getInt("l.cpt_id"),
                        resultSet.getString("cptlib.cpt_login"),
                        resultSet.getString("cptlib.cpt_mdp"),
                        resultSet.getString("lib_nom"),
                        resultSet.getString("lib_prenom")
                );

                Emprunter emprunter = new Emprunter(
                        resultSet.getInt("emp_id"),
                        client,
                        libraire,
                        exemplaire,
                        resultSet.getTimestamp("emp_date_emprunt").toLocalDateTime(),
                        resultSet.getTimestamp("emp_date_retour") != null ? resultSet.getTimestamp("emp_date_retour").toLocalDateTime() : null,
                        reservation
                );

                emprunterList.add(emprunter);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return emprunterList;
    }


}
