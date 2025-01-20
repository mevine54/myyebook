package fr.afpa.pompey.cda22045.myyebook.dao.exemplairedao;

import fr.afpa.pompey.cda22045.myyebook.ConnectionBDD.DatabaseConnection;
import fr.afpa.pompey.cda22045.myyebook.model.Auteur;
import fr.afpa.pompey.cda22045.myyebook.model.Categorie;
import fr.afpa.pompey.cda22045.myyebook.model.Exemplaire;
import fr.afpa.pompey.cda22045.myyebook.model.Livre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ExemplaireDAOImpl implements ExemplaireDAO {

    @Override
    public Exemplaire get(Integer id) throws SQLException {
        Exemplaire exemplaire = null;
        String sql = "SELECT * FROM exemplaire e " +
                "INNER JOIN livre l ON l.liv_id = e.liv_id " +
                "INNER JOIN auteur a ON a.aut_id = l.aut_id\n" +
                "INNER JOIN categorie c ON c.cat_id = l.cat_id\n" +
                " WHERE exe_id = ?";

        try (Connection connection = DatabaseConnection.getInstanceDB();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                Auteur auteur = new Auteur(
                        resultSet.getInt("aut_id"),
                        resultSet.getString("aut_nom"),
                        resultSet.getString("aut_prenom"),
                        resultSet.getString("aut_photo")
                );
                Categorie categorie = new Categorie(
                        resultSet.getInt("cat_id"),
                        resultSet.getString("cat_nom")
                );
                Livre livre = new Livre(
                        resultSet.getInt("liv_id"),
                        resultSet.getString("liv_titre"),
                        resultSet.getString("liv_resume"),
                        resultSet.getString("liv_photo"),
                        auteur, categorie
                );

                exemplaire = new Exemplaire(
                        resultSet.getInt("exe_id"),
                        livre);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return exemplaire;
    }

    @Override
    public List<Exemplaire> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public int insert(Exemplaire exemplaire) throws SQLException {
        return 0;
    }

    @Override
    public int update(Exemplaire exemplaire) throws SQLException {
        return 0;
    }

    @Override
    public int delete(Integer id) throws SQLException {
        return 0;
    }
}
