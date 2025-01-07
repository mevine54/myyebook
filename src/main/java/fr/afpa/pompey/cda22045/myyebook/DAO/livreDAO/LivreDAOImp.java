package fr.afpa.pompey.cda22045.myyebook.DAO.livreDAO;

import fr.afpa.pompey.cda22045.myyebook.ConnectionBDD.DatabaseConnection;
import fr.afpa.pompey.cda22045.myyebook.model.Livre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Livre dao imp.
 */
public class LivreDAOImp implements LivreDAO {
    @Override
    public Livre get(int livId) throws SQLException {
        Livre livre = new Livre();
        String sql = "SELECT * FROM Livre WHERE liv_id = ?";
        try (   Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, livId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                livre.setLivId(resultSet.getInt("liv_id"));
                livre.setLivTitre(resultSet.getString("liv_titre"));
                livre.setLivResume(resultSet.getString("liv_resume"));
                livre.setLivQuantite(resultSet.getInt("liv_quantite"));
                livre.setLivImage(resultSet.getBytes("liv_image"));
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }

        return livre;
    }

    @Override
    public List<Livre> getAll() throws SQLException {
        List<Livre> livres = new ArrayList<>();
        String sql = "SELECT * FROM Livre";
        try (   Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql))
        {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                livres.add(new Livre(
                        resultSet.getInt("liv_id"),
                        resultSet.getString("liv_titre"),
                        resultSet.getString("liv_resume"),
                        resultSet.getBytes("liv_image"),
                        resultSet.getInt("liv_quantite")
                ));
            }

        } catch (SQLException e) {
            throw new SQLException("Erreur lors de la récupération des livres.", e);
        }

        return livres;
    }

    @Override
    public int insert(Livre livre) throws SQLException {
        String sql = "INSERT INTO Livre (liv_id, liv_titre, liv_resume, liv_image, liv_quantite) VALUES (?, ?, ?, ?, ?)";
        int newId = 0;

        try (   Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, livre.getLivId());
            statement.setString(2, livre.getLivTitre());
            statement.setString(3, livre.getLivResume());
            statement.setBytes(4, livre.getLivImage());
            statement.setInt(5, livre.getLivQuantite());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                newId = rs.getInt(1);
            }
        }catch (SQLException e) {
            throw new SQLException(e);
        }

        return newId;
    }

    @Override
    public int update(Livre livre) throws SQLException {
        String sql = "UPDATE Livre SET liv_titre = ?, liv_resume = ?, liv_image = ?, liv_quantite = ? WHERE liv_id = ?";

        try (   Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setString(1, livre.getLivTitre());
            statement.setString(2, livre.getLivResume());
            statement.setBytes(3, livre.getLivImage());
            statement.setInt(4, livre.getLivQuantite());
            statement.setInt(5, livre.getLivId());


            return statement.executeUpdate();

        } catch (SQLException e) {
            throw new SQLException("Erreur lors de la mise à jour du livre", e);
        }

    }

    @Override
    public int delete(int i) throws SQLException {
        String sql = "DELETE FROM Livre WHERE liv_id = ?";

        try (   Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setInt(1, i);

            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Impossible de supprimer le livre" ,e);
        }

    }
}
