package fr.afpa.pompey.cda22045.myyebook.dao;

import fr.afpa.pompey.cda22045.myyebook.dao.DatabaseConnection;

import java.sql.Connection;
import java.util.List;

public abstract class DAO<T> {

    protected Connection conn = DatabaseConnection.getInstanceDB();

    public abstract T create(T obj);
    public abstract boolean delete(long id);
    public abstract boolean update(T obj);
    public abstract T getById(int id);
    public abstract List<T> getAll();




}
