package fr.afpa.pompey.cda22045.myyebook.dao.comptedao;

import fr.afpa.pompey.cda22045.myyebook.ConnectionBDD.DatabaseConnection;
import fr.afpa.pompey.cda22045.myyebook.model.Compte;

import java.net.Inet4Address;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompteDAOImp implements CompteDAO {
    @Override
    public Compte get(Integer id) throws SQLException {
        Compte compte = null;
        String sql = "SELECT * FROM compte WHERE cpt_id = ?";

        try(
                PreparedStatement ps = connection.prepareStatement(sql))
        {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                compte = new Compte(
                        resultSet.getInt("cpt_id"),
                        resultSet.getString("cpt_login"),
                        resultSet.getString("cpt_mdp")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return compte;
    }

    @Override
    public List<Compte> getAll() throws SQLException {
        List<Compte> comptes = new ArrayList<>();
        String sql = "SELECT * FROM compte";

        try(
                PreparedStatement ps = connection.prepareStatement(sql))
        {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Compte compte = new Compte();
                compte.setCompteId(resultSet.getInt("cpt_id"));
                compte.setLogin(resultSet.getString("cpt_login"));
                compte.setPassword(resultSet.getString("cpt_mdp"));
                comptes.add(compte);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return comptes;
    }

    @Override
    public int insert(Compte compte) throws SQLException {
        String sql = "INSERT INTO Compte (cpt_login, cpt_mdp) VALUES ( ?, ?)";
        int compteId = 0;
        try(
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
        {
            ps.setString(1, compte.getLogin());
            ps.setString(2, compte.getPassword());
            ps.executeUpdate();
            ResultSet generatedKeysCompte = ps.getGeneratedKeys();
            if (generatedKeysCompte.next()) {
                return generatedKeysCompte.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return compteId;
    }

    @Override
    public int update(Compte compte) throws SQLException {
        String sql = "UPDATE Compte SET cpt_login = ?, cpt_mdp =  ? WHERE cpt_id = ?;";
        int compteId = 0;
        try(
                PreparedStatement ps = connection.prepareStatement(sql))
        {
            ps.setString(1, compte.getLogin());
            ps.setString(2, compte.getPassword());
            ps.setInt(3, compte.getCompteId() );
            int i = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return compteId;
    }

    @Override
    public int delete(Integer id) throws SQLException {
        String sql = "DELETE FROM compte WHERE cpt_id = ?";

        try(
                PreparedStatement ps = connection.prepareStatement(sql))
        {
            ps.setInt(1, id);
            int i = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;

    }

    @Override
    public Compte getParLogin(String login) throws SQLException {
        Compte compte = new Compte();
        String sql = "SELECT * FROM compte WHERE cpt_login = ?";

        try(
                Connection connection = DatabaseConnection.getInstanceDB();
                PreparedStatement ps = connection.prepareStatement(sql))
        {
            ps.setString(1, login);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                compte.setCompteId(resultSet.getInt("cpt_id"));
                compte.setLogin(resultSet.getString("cpt_login"));
                compte.setPassword(resultSet.getString("cpt_mdp"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return compte;
    }
}