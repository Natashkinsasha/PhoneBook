package main.Repository;


import main.DAO.DAOException;

import java.util.List;

public interface Repository <T,K> {
    T create(T entity) throws DAOException;

    void update(T entity) throws DAOException;

    void delete(T entity) throws DAOException;

    T getById(K id) throws DAOException;

    List<T> getAll() throws DAOException;
}
