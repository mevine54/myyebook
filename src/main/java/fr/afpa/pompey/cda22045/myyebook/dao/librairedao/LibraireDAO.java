package fr.afpa.pompey.cda22045.myyebook.dao.librairedao;

import fr.afpa.pompey.cda22045.myyebook.dao.DAO;
import fr.afpa.pompey.cda22045.myyebook.model.Libraire;

import java.sql.SQLException;

public interface LibraireDAO extends DAO<Libraire> {

    Libraire getParCompteId(Integer cptId) throws SQLException;
}