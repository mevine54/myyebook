package fr.afpa.pompey.cda22045.myyebook.dao.comptedao;

import fr.afpa.pompey.cda22045.myyebook.dao.DAO;
import fr.afpa.pompey.cda22045.myyebook.model.Compte;

import java.sql.SQLException;

public interface CompteDAO extends DAO<Compte> {
    default Compte getParLogin(String login) throws SQLException {
        return null;
    }
    default Integer getNbCompte() throws SQLException {
        return null;
    }
    String getHashedPasswordByLogin(String login);
}