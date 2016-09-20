package main.DAO;

import main.DTO.ContactDTO;
import main.Entity.ContactEntity;

import java.util.List;
import java.util.Map;

public interface ContactDAO extends DAO<ContactEntity, Integer>{
    List<ContactEntity> getSerchSortLimit(ContactEntity contactEntity, int offset, int count, Map<String, Boolean> sortFields) throws DAOException;
}
