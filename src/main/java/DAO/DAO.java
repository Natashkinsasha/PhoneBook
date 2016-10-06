package main.java.DAO;


import java.util.List;

public interface DAO<T,K> {
    T create(T entity) throws DAOException;
    void update(T entity) throws DAOException;
    void delete(K id) throws DAOException;
    T getById(K id) throws DAOException;
    List<T> getAll() throws DAOException;
    int getNumber() throws DAOException;
}
