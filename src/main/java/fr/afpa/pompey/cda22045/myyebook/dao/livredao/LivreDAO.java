package fr.afpa.pompey.cda22045.myyebook.dao.livredao;

import fr.afpa.pompey.cda22045.myyebook.dao.DAO;
import fr.afpa.pompey.cda22045.myyebook.model.Livre;

import java.sql.SQLException;
import java.util.List;

public interface LivreDAO extends DAO<Livre> {
    List<Livre> getParAuteur(int autId) throws SQLException;
    List<Livre> getParCategorie(int catId) throws SQLException;
    Integer getNbLivres() throws SQLException;
}


