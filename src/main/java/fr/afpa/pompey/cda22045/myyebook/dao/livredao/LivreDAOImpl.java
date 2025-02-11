package fr.afpa.pompey.cda22045.myyebook.dao.livredao;


import fr.afpa.pompey.cda22045.myyebook.connectionbdd.DatabaseConnection;
import fr.afpa.pompey.cda22045.myyebook.model.Auteur;
import fr.afpa.pompey.cda22045.myyebook.model.Categorie;
import fr.afpa.pompey.cda22045.myyebook.model.Livre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivreDAOImpl implements LivreDAO {
    private static final Logger log = LoggerFactory.getLogger(LivreDAOImpl.class);

    @Override
    public Livre get(Integer id) throws SQLException {
        Livre livre = null;
        String sql = "SELECT * FROM livre l\n" +
                "INNER JOIN auteur a ON a.aut_id = l.aut_id\n" +
                "INNER JOIN categorie c ON c.cat_id = l.cat_id\n" +
                "WHERE liv_id = ?";

        try (Connection connection = DatabaseConnection.getInstanceDB();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                // Récupère Auteur et Categorie by their IDs
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
                livre = new Livre(
                        resultSet.getInt("liv_id"),
                        resultSet.getString("liv_titre"),
                        resultSet.getString("liv_resume"),
                        resultSet.getString("liv_photo"),
                        resultSet.getBoolean("liv_en_avant"),
                        auteur,
                        categorie,
                        resultSet.getInt("quantite")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return livre;
    }

    @Override
    public List<Livre> getAll() throws SQLException {
        List<Livre> livres = new ArrayList<>();
        String sql = "SELECT * FROM livre l\n" +
                "INNER JOIN auteur a ON a.aut_id = l.aut_id\n" +
                "INNER JOIN categorie c ON c.cat_id = l.cat_id";

        try (Connection connection = DatabaseConnection.getInstanceDB();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {

                // Récupère Auteur et Categorie by their IDs
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
                        resultSet.getBoolean("liv_en_avant"),
                        auteur, categorie, resultSet.getInt("quantite")
                );

                livre.setAuteur(auteur);
                livre.setCategorie(categorie);
                log.info(String.valueOf(livre));
                livres.add(livre);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return livres;
    }

    @Override
    public Integer insert(Livre livre) throws SQLException {
        String sql = "INSERT INTO livre (liv_titre, liv_resume, liv_photo, liv_en_avant, aut_id, cat_id,quantite) VALUES (?, ?, ?, ?, ?,?,?)";
        Integer id = null;
        try (Connection connection = DatabaseConnection.getInstanceDB();
             PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, livre.getTitre());
            ps.setString(2, livre.getResume());
            ps.setString(3, livre.getImage());
            ps.setBoolean(4, livre.isEstEnAvant());
            ps.setInt(5, livre.getAuteur().getAuteurId());
            ps.setInt(6, livre.getCategorie().getId());
            ps.setInt(7, livre.getQuantite());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = generatedKeys.getInt(1);
            }
            return id;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer update(Livre livre) throws SQLException {
        String sql = "UPDATE livre SET liv_titre = ?, liv_resume = ?, liv_photo = ?,liv_en_avant = ?, aut_id = ?, cat_id = ?, quantite= ? WHERE liv_id = ?";

        try (Connection connection = DatabaseConnection.getInstanceDB();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, livre.getTitre());
            ps.setString(2, livre.getResume());
            ps.setString(3, livre.getImage());
            ps.setBoolean(4, livre.isEstEnAvant());
            ps.setInt(5, livre.getAuteur().getAuteurId());
            ps.setInt(6, livre.getCategorie().getId());
            ps.setInt(7, livre.getQuantite());
            ps.setInt(8, livre.getId());
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer delete(int id) throws SQLException {
        String sql = "DELETE FROM livre WHERE liv_id = ?";

        try (Connection connection = DatabaseConnection.getInstanceDB();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Livre> getParAuteur(int autId) throws SQLException {
        List<Livre> livres = new ArrayList<>();
        String sql = "SELECT l.* FROM livre l WHERE l.aut_id = ?";

        try (Connection connection = DatabaseConnection.getInstanceDB();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, autId);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {



                // Récupère Auteur et Categorie by their IDs
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
                        resultSet.getBoolean("liv_en_avant"),
                        auteur,categorie,
                        resultSet.getInt("quantite")
                );

                livre.setAuteur(auteur);
                livre.setCategorie(categorie);
                livres.add(livre);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return livres;
    }

    @Override
    public List<Livre> getParCategorie(int catId) throws SQLException {
        List<Livre> livres = new ArrayList<>();
        String sql = "SELECT * FROM livre l\n" +
                "INNER JOIN auteur a ON a.aut_id = l.aut_id\n" +
                "INNER JOIN categorie c ON c.cat_id = l.cat_id\n" +
                "WHERE l.cat_id = ?";

        try (Connection connection = DatabaseConnection.getInstanceDB();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, catId);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                // Récupère Auteur et Categorie by their IDs
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
                        resultSet.getBoolean("liv_en_avant"),
                        auteur,
                        categorie,
                        resultSet.getInt("quantite")
                );
                livres.add(livre);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return livres;
    }

    @Override
    public List<Livre> chercherTitreOuAuteur(String text) throws SQLException {
        List<Livre> livres = new ArrayList<>();
        String sql = "SELECT * FROM livre l\n" +
                "INNER JOIN auteur a ON a.aut_id = l.aut_id\n" +
                "INNER JOIN categorie c ON c.cat_id = l.cat_id\n" +
                "WHERE LOWER(liv_titre) LIKE ? OR LOWER(aut_nom) LIKE ? OR LOWER(aut_prenom) LIKE ?";


        try (Connection connection = DatabaseConnection.getInstanceDB();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            String aCherche = "%" + text + "%";
            ps.setString(1, aCherche.toLowerCase());
            ps.setString(2, aCherche.toLowerCase());
            ps.setString(3, aCherche.toLowerCase());
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Livre livre = new Livre();
                livre.setId(resultSet.getInt("liv_id"));
                livre.setTitre(resultSet.getString("liv_titre"));
                livre.setResume(resultSet.getString("liv_resume"));
                livre.setImage(resultSet.getString("liv_photo"));
                // Récupère Auteur et Categorie by their IDs
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
                livre.setAuteur(auteur);
                livre.setCategorie(categorie);
                livres.add(livre);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return livres;
    }

    @Override
    public Integer getNbLivres() throws SQLException {
        String sql = "SELECT COUNT(*) AS nb_livres FROM livre";

        try (Connection connection = DatabaseConnection.getInstanceDB();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet resultSet = ps.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt("nb_livres");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Livre> getEnAvant() throws SQLException {
        List<Livre> livres = new ArrayList<>();
        String sql = "SELECT * FROM livre l\n" +
                "INNER JOIN auteur a ON a.aut_id = l.aut_id\n" +
                "INNER JOIN categorie c ON c.cat_id = l.cat_id WHERE liv_en_avant = TRUE";

        try (Connection connection = DatabaseConnection.getInstanceDB();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Livre livre = new Livre();
                livre.setId(resultSet.getInt("liv_id"));
                livre.setTitre(resultSet.getString("liv_titre"));
                livre.setResume(resultSet.getString("liv_resume"));
                livre.setImage(resultSet.getString("liv_photo"));
                livre.setEstEnAvant(resultSet.getBoolean("liv_en_avant"));
                // Récupère Auteur et Categorie by their IDs
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
                livre.setAuteur(auteur);
                livre.setCategorie(categorie);
                livres.add(livre);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return livres;
    }


    // Assuming you have methods to get Auteur and Categorie by their IDs
//    private Auteur getAuteurById(int autId) throws SQLException {
//        Auteur auteur = null;
//        String sql = "SELECT * FROM auteur WHERE aut_id = ?";
//
//        try (Connection connection = DatabaseConnection.getInstanceDB();
//             PreparedStatement ps = connection.prepareStatement(sql)) {
//            ps.setInt(1, autId);
//            ResultSet resultSet = ps.executeQuery();
//            if (resultSet.next()) {
//                auteur.setAuteurId(resultSet.getInt("aut_id"));
//                auteur.setNom(resultSet.getString("aut_nom"));
//                auteur.setPrenom(resultSet.getString("aut_prenom"));
//                auteur.setPhoto(resultSet.getString("aut_photo"));
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return auteur;
//    }

//    private Categorie getCategorieById(int catId) throws SQLException {
//        Categorie categorie = null;
//        String sql = "SELECT * FROM categorie WHERE cat_id = ?";
//
//        try (Connection connection = DatabaseConnection.getInstanceDB();
//             PreparedStatement ps = connection.prepareStatement(sql)) {
//            ps.setInt(1, catId);
//            ResultSet resultSet = ps.executeQuery();
//            if (resultSet.next()) {
//                categorie.setId(resultSet.getInt("cat_id"));
//                categorie.setNom(resultSet.getString("cat_nom"));
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return categorie;
//    }
}
