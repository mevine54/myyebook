package fr.afpa.pompey.cda22045.myyebook.dao.categoriedao;

import fr.afpa.pompey.cda22045.myyebook.dao.DAO;
import fr.afpa.pompey.cda22045.myyebook.model.Categorie;

import java.sql.SQLException;
import java.util.List;

public interface CategorieDAO extends DAO<Categorie> {
    List<Categorie> getParNom(String nom) throws SQLException;
    Integer getNbCategories() throws SQLException;
}
