package fr.afpa.pompey.cda22045.myyebook.dao.librairedao;

import fr.afpa.pompey.cda22045.myyebook.connectionbdd.DatabaseConnection;
import fr.afpa.pompey.cda22045.myyebook.model.Compte;
import fr.afpa.pompey.cda22045.myyebook.model.Libraire;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibraireDAOImp implements LibraireDAO {

    @Override
    public Libraire get(Integer libId) throws SQLException {
        Libraire libraire = null;
        Compte compte = null;
        String sql = "SELECT * FROM compte c INNER JOIN libraire l on c.cpt_id = l.cpt_id WHERE lib_id = ? ;";

        try (
                Connection connection = DatabaseConnection.getInstanceDB();
                PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, libId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                compte = new Compte(
                        rs.getInt("cpt_id"),
                        rs.getString("cpt_login"),
                        rs.getString("cpt_mdp")
                );

                libraire = new Libraire(
                        rs.getInt("lib_id"),
                        compte.getCompteId(),
                        compte.getLogin(),
                        compte.getPassword(),
                        rs.getBoolean("lib_est_approuve"),
                        rs.getString("lib_nom"),
                        rs.getString("lib_prenom")
                );
            }

        }

        return libraire;
    }

    @Override
    public List<Libraire> getAll() throws SQLException {
        List<Libraire> libraires = new ArrayList<>();
        String sql = "SELECT * FROM compte c INNER JOIN libraire l on c.cpt_id = l.cpt_id";
        try (
                Connection connection = DatabaseConnection.getInstanceDB();
                PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Compte compte = new Compte(
                        rs.getInt("cpt_id"),
                        rs.getString("cpt_login"),
                        rs.getString("cpt_mdp")
                );

                Libraire libraire = new Libraire(
                        rs.getInt("lib_id"),
                        compte.getCompteId(),
                        compte.getLogin(),
                        compte.getPassword(),
                        rs.getBoolean("lib_est_approuve"),
                        rs.getString("lib_nom"),
                        rs.getString("lib_prenom")
                );
                libraires.add(libraire);
            }
        }
        return libraires;
    }

    @Override
    public Integer insert(Libraire libraire) throws SQLException {
        String sql = "INSERT INTO Compte (cpt_login, cpt_mdp,cpt_role) VALUES ( ?, ?,?)";
        Integer compteId = 0;
        try {

            Connection connection = DatabaseConnection.getInstanceDB();
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, libraire.getLogin());
            ps.setString(2, libraire.getPassword());
            ps.setString(3, libraire.getRole());
            ps.executeUpdate();
            ResultSet generatedKeysCompte = ps.getGeneratedKeys();
            if (generatedKeysCompte.next()) {
                sql = "INSERT INTO libraire ( lib_nom, lib_prenom,cpt_id) VALUES ( ?, ?,?)";
                compteId = generatedKeysCompte.getInt(1);
                System.out.println("COMPTE : -----  " + compteId);
                ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, libraire.getNom());
                ps.setString(2, libraire.getPrenom());
                ps.setInt(3, compteId );
                ps.executeUpdate();
                ResultSet generatedKeysLibraire = ps.getGeneratedKeys();
                if (generatedKeysLibraire.next()) {
                    int libraireId = generatedKeysLibraire.getInt(1);
                    libraire.setLibId(libraireId);
                    connection.commit();
                    return libraireId;
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
    public Integer update(Libraire libraire) throws SQLException {
        String sql = "UPDATE Compte SET cpt_login = ?, cpt_mdp = ?, cpt_role = ? WHERE cpt_id = ?";
        try {
            Connection connection = DatabaseConnection.getInstanceDB();
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, libraire.getLogin());
            ps.setString(2, libraire.getPassword());
            ps.setString(3, libraire.getRole());
            ps.setInt(4, libraire.getCompteId());
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                sql = "UPDATE libraire SET lib_nom = ?, lib_prenom = ? WHERE lib_id = ?";
                ps = connection.prepareStatement(sql);
                ps.setString(1, libraire.getNom());
                ps.setString(2, libraire.getPrenom());
                ps.setInt(3, libraire.getLibId());
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
        String sqlGetCompteId = "SELECT c.cpt_id FROM Compte c INNER JOIN libraire l ON c.cpt_id = l.cpt_id WHERE l.lib_id = ?";
        String sqlDeleteLibraire = "DELETE FROM libraire WHERE lib_id = ?";
        String sqlDeleteCompte = "DELETE FROM Compte WHERE cpt_id = ?";
        Connection connection = null;
        PreparedStatement psGetCompteId = null;
        PreparedStatement psDeleteLibraire = null;
        PreparedStatement psDeleteCompte = null;
        ResultSet rs = null;

        try {
            connection = DatabaseConnection.getInstanceDB();
            connection.setAutoCommit(false);

            // Get the compte ID associated with the libraire using INNER JOIN
            psGetCompteId = connection.prepareStatement(sqlGetCompteId);
            psGetCompteId.setInt(1, id);
            rs = psGetCompteId.executeQuery();
            if (rs.next()) {
                int compteId = rs.getInt("cpt_id");

                // Delete the libraire
                psDeleteLibraire = connection.prepareStatement(sqlDeleteLibraire);
                psDeleteLibraire.setInt(1, id);
                psDeleteLibraire.executeUpdate();

                // Delete the compte
                psDeleteCompte = connection.prepareStatement(sqlDeleteCompte);
                psDeleteCompte.setInt(1, compteId);
                psDeleteCompte.executeUpdate();

                connection.commit();
                return id;
            } else {
                throw new SQLException("No compte found for libraireId: " + id );
            }
        } catch (SQLException e) {
            if (connection != null) {
                connection.rollback();
            }
            throw new RuntimeException(e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (psGetCompteId != null) {
                psGetCompteId.close();
            }
            if (psDeleteLibraire != null) {
                psDeleteLibraire.close();
            }
            if (psDeleteCompte != null) {
                psDeleteCompte.close();
            }
            if (connection != null) {
                connection.setAutoCommit(true);
                connection.close();
            }
        }
    }
}