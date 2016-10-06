package main.java.Repository;


import java.util.List;

public interface Repository <T,K> {
    T create(T dto) throws RepositoryException;

    void update(T dto) throws  RepositoryException;

    void delete(K id) throws RepositoryException;

    T get(K id) throws RepositoryException;

    List<T> toList() throws RepositoryException;

    int size() throws RepositoryException;

}
