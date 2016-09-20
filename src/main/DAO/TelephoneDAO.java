package main.DAO;


import main.DTO.TelephoneDTO;
import main.Entity.TelephoneEntity;

import java.util.List;

public interface TelephoneDAO extends DAO<TelephoneEntity, Integer>{
    List<TelephoneEntity> getByContactId(int id) throws DAOException;
}
