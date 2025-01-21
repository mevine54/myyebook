package fr.afpa.pompey.cda22045.myyebook.dao.auteurdao;

import fr.afpa.pompey.cda22045.myyebook.dao.DAO;
import fr.afpa.pompey.cda22045.myyebook.model.Auteur;

import java.sql.SQLException;
import java.util.List;

public interface AuteurDAO extends DAO<Auteur> {
    List<Auteur> getParNom(String nom) throws SQLException;
    Integer getNbAuteurs() throws SQLException;
}