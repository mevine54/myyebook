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
                        compte,
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
            if (rs.next()) {
                Compte compte = new Compte(
                        rs.getInt("cpt_id"),
                        rs.getString("cpt_login"),
                        rs.getString("cpt_mdp")
                );

                Libraire libraire = new Libraire(
                        rs.getInt("lib_id"),
                        compte,
                        rs.getString("lib_nom"),
                        rs.getString("lib_prenom")
                );
                libraires.add(libraire);
            }
        }
        return libraires;
    }

    @Override
    public int insert(Libraire libraire) throws SQLException {
        String sql = "INSERT INTO Compte (cpt_login, cpt_mdp) VALUES ( ?, ?)";
        Integer compteId = 0;
        try {

            Connection connection = DatabaseConnection.getInstanceDB();
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, libraire.getLogin());
            ps.setString(2, libraire.getPassword());
            ps.executeUpdate();
            ResultSet generatedKeysCompte = ps.getGeneratedKeys();
            if (generatedKeysCompte.next()) {
                sql = "INSERT INTO libraire (lib_id, lib_nom, lib_prenom,cpt_id) VALUES ( ?, ?, ?,?)";
                compteId = generatedKeysCompte.getInt(1);
                System.out.println("COMPTE : -----  " + compteId);
                ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, libraire.getLibId());
                ps.setString(2, libraire.getNom());
                ps.setString(3, libraire.getPrenom());
                ps.setInt(4, libraire.getCompteId());
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
    public int update(Libraire libraire) throws SQLException {
        String sql = "UPDATE Compte SET cpt_login = ?, cpt_mdp = ? WHERE cpt_id = ?";
        try {
            Connection connection = DatabaseConnection.getInstanceDB();
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, libraire.getLogin());
            ps.setString(2, libraire.getPassword());
            ps.setInt(3, libraire.getCompteId());
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
    public int delete(Integer id) throws SQLException {
        return 0;
    }
}