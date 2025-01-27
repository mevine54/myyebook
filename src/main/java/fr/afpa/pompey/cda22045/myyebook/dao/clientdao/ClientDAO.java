package fr.afpa.pompey.cda22045.myyebook.dao.clientdao;

import fr.afpa.pompey.cda22045.myyebook.dao.DAO;
import fr.afpa.pompey.cda22045.myyebook.model.Client;

import java.sql.SQLException;


public interface ClientDAO extends DAO<Client> {

    Client getParCompteId(Integer cptId) throws SQLException;
}