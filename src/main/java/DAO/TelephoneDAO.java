package main.java.DAO;


import main.java.Entity.TelephoneEntity;

import java.util.List;

public interface TelephoneDAO extends DAO<TelephoneEntity, Integer>{
    List<TelephoneEntity> getByContactId(int id) throws DAOException;
}
