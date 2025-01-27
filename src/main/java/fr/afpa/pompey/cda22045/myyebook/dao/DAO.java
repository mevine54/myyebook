package fr.afpa.pompey.cda22045.myyebook.dao;

import fr.afpa.pompey.cda22045.myyebook.connectionbdd.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * The interface Dao.
 *
 * @param <T> the type parameter
 */
public interface DAO<T> {


    static Connection connection = DatabaseConnection.getInstanceDB();
    /**
     * Get t.
     *
     * @param id the id
     * @return the t
     * @throws SQLException the sql exception
     */
    default T get(Integer id) throws SQLException {
        return null;
    }

    /**
     * Gets all.
     *
     * @return the all
     * @throws SQLException the sql exception
     */
    List<T> getAll() throws SQLException;


    /**
     * Insert int.
     *
     * @param t the t
     * @return the int
     * @throws SQLException the sql exception
     */
    Integer insert(T t) throws SQLException;

    /**
     * Update int.
     *
     * @param t the t
     * @return the int
     * @throws SQLException the sql exception
     */
    Integer update(T t) throws SQLException;

    /**
     * Delete int.
     *
     * @param id the
     * @return the int
     * @throws SQLException the sql exception
     */
    Integer delete(int id) throws SQLException;
}