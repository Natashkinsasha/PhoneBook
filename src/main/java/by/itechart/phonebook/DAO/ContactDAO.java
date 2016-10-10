package by.itechart.phonebook.DAO;

import by.itechart.phonebook.Entity.ContactEntity;

import java.util.List;
import java.util.Map;

public interface ContactDAO extends DAO<ContactEntity, Integer>{
    List<ContactEntity> getSerchSortLimit(ContactEntity contactEntity, int offset, int count, Map<String, Boolean> sortFields) throws DAOException;
    int getNumberSerch(ContactEntity contactEntity) throws DAOException;
}
