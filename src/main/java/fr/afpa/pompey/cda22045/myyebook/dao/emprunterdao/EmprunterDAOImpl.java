package fr.afpa.pompey.cda22045.myyebook.dao.emprunterdao;

import fr.afpa.pompey.cda22045.myyebook.ConnectionBDD.DatabaseConnection;
import fr.afpa.pompey.cda22045.myyebook.model.*;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmprunterDAOImpl implements EmprunterDAO{

//    @Override
//    public List<Emprunter> getParExemplaire(int exe_id) throws SQLException {
//        List<Emprunter> emprunterList = new ArrayList<>();
//        String sql = "SELECT * " +
//                    "FROM Emprunter " +
//                    "INNER JOIN Client ON Emprunter.cli_id = Client.cli_id " +
//                    "INNER JOIN Exemplaire ON Emprunter.exe_id = Exemplaire.exe_id " +
//                    "WHERE exe_id = ?";
//
//        try (Connection connection = DatabaseConnection.getInstanceDB();
//             PreparedStatement ps = connection.prepareStatement(sql)) {
//            ps.setInt(1, exe_id);
//            ResultSet resultSet = ps.executeQuery();
//            while (resultSet.next()) {
//                Emprunter emprunter = new Emprunter();
//                emprunter.setId(resultSet.getInt("emp_id"));
//                emprunter.setDatetimeEmprunt(resultSet.getTime("emp_date_emprunt")); //TODO : A REVOIR
//                emprunter.setDatetimeRetour(resultSet.getDate("emp_date_retour")); //TODO : A REVOIR
//                emprunterList.add(emprunter);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return List.of();
//    }
//
//    @Override
//    public List<Emprunter> getParClient(int cli_id) throws SQLException {
//        return List.of();
//    }
//
//    @Override
//    public Integer getNbEmprunt() throws SQLException {
//        return 0;
//    }

    @Override
    public Emprunter get(Integer id) throws SQLException {
        String sql = "SELECT * FROM Emprunter e\n" +
                "INNER JOIN Client c ON e.cli_id = c.cli_id\n" +
                "INNER JOIN Exemplaire ex ON e.exe_id = ex.exe_id\n" +
                "INNER JOIN Reservation r ON e.res_id = r.res_id\n" +
                "INNER JOIN Libraire l ON e.lib_id = l.lib_id\n" +
                "WHERE emp_id = ?";

        try (Connection connection = DatabaseConnection.getInstanceDB();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()) {
                Emprunter emprunter = new Emprunter();
                emprunter.setId(resultSet.getInt("emp_id"));
                emprunter.setDatetimeEmprunt(resultSet.getDate("emp_date_emprunt")); //TODO : A REVOIR
                emprunter.setDatetimeRetour(resultSet.getDate("emp_date_retour")); //TODO : A REVOIR

                Reservation reservation = getReservationById(resultSet.getInt("res_id"));
                Client client = getClientById(resultSet.getInt("cli_id"));
                Libraire libraire = getLibraireById(resultSet.getInt("lib_id"));
                Exemplaire exemplaire = getExemplaireById(resultSet.getInt("exe_id"));

                return emprunter;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Emprunter> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public int insert(Emprunter emprunter) throws SQLException {
        String sql = "INSERT INTO Emprunter (cli_id, exe_id, emp_date_emprunt, emp_date_retour) VALUES (?, ?, ?, ?)";

        try(Connection connection = DatabaseConnection.getInstanceDB();
            PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, emprunter.getClient().getClientId());
            ps.setInt(2, emprunter.getExemplaire().getExemplaireId());
            ps.setDate(3, emprunter.getDatetimeEmprunt()); //TODO : A REVOIR
            ps.setDate(4, emprunter.getDatetimeRetour()); //TODO : A REVOIR
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public int update(Emprunter emprunter) throws SQLException {
        String sql = "UPDATE Emprunter SET cli_id = ?, exe_id = ?, emp_date_emprunt = ?, emp_date_retour = ?, res_id = ? WHERE emp_id = ?";

        try (Connection connection = DatabaseConnection.getInstanceDB();
        PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, emprunter.getClient().getClientId());
            ps.setInt(2, emprunter.getExemplaire().getExemplaireId());
            ps.setDate(3, emprunter.getDatetimeEmprunt()); //TODO : A REVOIR
            ps.setDate(4, emprunter.getDatetimeRetour()); //TODO : A REVOIR
            ps.setInt(5, emprunter.getReservation().getResId());
            ps.setInt(6, emprunter.getId());
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    @Override
    public int delete(Integer id) throws SQLException {
        String sql = "DELETE FROM Emprunter WHERE emp_id = ?";
        try (Connection connection = DatabaseConnection.getInstanceDB();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Reservation getReservationById(int res_id) {
        Reservation reservation = null;
        String sql = "SELECT * FROM Reservation WHERE res_id = ?";
        try (Connection connection = DatabaseConnection.getInstanceDB();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, res_id);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()) {
                reservation.setResId(resultSet.getInt("res_id"));
                reservation.setDatetime(resultSet.getDate("res_date")); //TODO : A REVOIR
                reservation.getLivre().setId(resultSet.getInt("liv_id"));
                reservation.getClient().setClientId(resultSet.getInt("cli_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reservation;
    }

    private Client getClientById(int cli_id){
        Client client = null;
        String sql = "SELECT * FROM Client WHERE cli_id = ?";
        try (Connection connection = DatabaseConnection.getInstanceDB();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, cli_id);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()) {
                client.setClientId(resultSet.getInt("cli_id"));
                client.setNom(resultSet.getString("cli_nom"));
                client.setPrenom(resultSet.getString("cli_prenom"));
                client.setAdresse(resultSet.getString("cli_adresse"));
                client.setEmail(resultSet.getString("cli_email"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return client;
    }

    private Libraire getLibraireById(int lib_id) {
        Libraire libraire = null;
        String sql = "SELECT * FROM Libraire WHERE lib_id = ?";
        try (Connection connection = DatabaseConnection.getInstanceDB();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, lib_id);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()) {
                libraire.setLibId(resultSet.getInt("lib_id"));
                libraire.setNom(resultSet.getString("lib_nom"));
                libraire.setPrenom(resultSet.getString("lib_prenom"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return libraire;
    }

    private Exemplaire getExemplaireById(int exe_id) {
        Exemplaire exemplaire = null;
        String sql = "SELECT * FROM Exemplaire WHERE exe_id = ?";
        try (Connection connection = DatabaseConnection.getInstanceDB();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, exe_id);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()) {
                exemplaire.setExemplaireId(resultSet.getInt("exe_id"));
                exemplaire.getLivre().setId(resultSet.getInt("liv_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return exemplaire;
    }
}
