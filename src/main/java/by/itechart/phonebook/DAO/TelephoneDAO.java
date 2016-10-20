package by.itechart.phonebook.DAO;


import by.itechart.phonebook.Entity.ContactEntity;
import by.itechart.phonebook.Entity.TelephoneEntity;

import java.util.List;

public interface TelephoneDAO extends DAO<TelephoneEntity, Integer>{
    List<TelephoneEntity> getByContactId(int id) throws DAOException;
    void update(List<TelephoneEntity> telephoneEntities) throws DAOException;
    void deleteAllByContactId(int id) throws DAOException;
}
