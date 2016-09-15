package main.Repository;


import main.DAO.DAOException;
import main.DTO.ContactDTO;

import java.util.List;

public class ContactRepositoryImpl implements ContactRepository{

    @Override
    public ContactDTO create(ContactDTO entity) throws DAOException {
        return null;
    }

    @Override
    public void update(ContactDTO entity) throws DAOException {

    }

    @Override
    public void delete(ContactDTO entity) throws DAOException {

    }

    @Override
    public ContactDTO getById(Integer id) throws DAOException {
        return null;
    }

    @Override
    public List<ContactDTO> getAll() throws DAOException {
        return null;
    }
}
