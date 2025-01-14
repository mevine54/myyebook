package fr.afpa.pompey.cda22045.myyebook.dao.comptedao;

import fr.afpa.pompey.cda22045.myyebook.ConnectionBDD.DatabaseConnection;
import fr.afpa.pompey.cda22045.myyebook.model.Compte;

import java.net.Inet4Address;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CompteDAOImp implements CompteDAO {
    @Override
    public Compte get(Integer id) throws SQLException {
        Compte compte = new Compte();
        String sql = "SELECT * FROM compte WHERE cpt_id = ?";

        try(
                Connection connection = DatabaseConnection.getInstanceDB();
                PreparedStatement ps = connection.prepareStatement(sql))
        {
            ps.setInt(1, id);
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

    @Override
    public List<Compte> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public int insert(Compte compte) throws SQLException {
        return 0;
    }

    @Override
    public int update(Compte compte) throws SQLException {
        return 0;
    }

    @Override
    public int delete(Integer id) throws SQLException {
        return 0;
    }

    @Override
    public Compte getParLogin(String login) throws SQLException {
        Compte compte = new Compte();
        String sql = "SELECT * FROM compte WHERE login = ?";

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
