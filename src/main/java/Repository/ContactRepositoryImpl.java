package main.java.Repository;


import main.java.DAO.*;
import main.java.DTO.ContactDTO;
import main.java.DTO.TelephoneDTO;
import main.java.Entity.ContactEntity;
import main.java.Entity.TelephoneEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ContactRepositoryImpl implements ContactRepository {

    private DAOFactory getDAOFactory() {
        return DAOFactory.getDAOFactory(TypeDAOFactory.MySQL);
    }

    @Override
    public ContactDTO create(ContactDTO contactDTO) throws RepositoryException {
        DAOFactory daoFactory = getDAOFactory();
        Connection connection = null;
        ContactDTO newContactDTO = null;
        try {
            connection = daoFactory.getConnection();
            ContactDAO mySQLContactDAO = daoFactory.getContactDAO(connection);
            TelephoneDAO mySQLtelephoneDAO = daoFactory.getTelephoneDAO(connection);
            connection.setAutoCommit(false);
            newContactDTO = new ContactDTO(mySQLContactDAO.create(new ContactEntity(contactDTO)));
            for (TelephoneDTO telephoneDTO : contactDTO.getTelephonesDTO()) {
                newContactDTO.getTelephonesDTO().add(new TelephoneDTO(mySQLtelephoneDAO.create(new TelephoneEntity(telephoneDTO, newContactDTO.getId()))));
            }
            connection.commit();
        } catch (SQLException | DAOException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    throw new RepositoryException(e1);
                }
            }
            throw new RepositoryException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RepositoryException(e);
                }
            }
        }
        return newContactDTO;
    }

    @Override
    public void update(ContactDTO dto) throws RepositoryException {
        DAOFactory daoFactory = getDAOFactory();
        Connection connection = null;
        try {
            connection = daoFactory.getConnection();
            ContactDAO mySQLContactDAO = daoFactory.getContactDAO(connection);
            TelephoneDAO mySQLtelephoneDAO = daoFactory.getTelephoneDAO(connection);
            ContactEntity contactEntity = new ContactEntity(dto);
            connection.setAutoCommit(false);
            mySQLContactDAO.update(contactEntity);
            List<TelephoneEntity> telephoneEntityList = mySQLtelephoneDAO.getByContactId(contactEntity.getId());
            for (TelephoneEntity telephoneEntity : telephoneEntityList) {
                mySQLtelephoneDAO.delete(telephoneEntity.getId());
            }
            for (TelephoneDTO telephoneDTO : dto.getTelephonesDTO()) {
                TelephoneEntity telephoneEntity = new TelephoneEntity(telephoneDTO, dto.getId());
                mySQLtelephoneDAO.create(telephoneEntity);

            }

            connection.commit();
        } catch (SQLException | DAOException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    throw new RepositoryException(e1);
                }
            }
            throw new RepositoryException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RepositoryException(e);
                }
            }
        }
    }

    @Override
    public void delete(Integer id) throws RepositoryException {
        DAOFactory daoFactory = getDAOFactory();
        Connection connection = null;
        try {
            connection = daoFactory.getConnection();
            ContactDAO mySQLContactDAO = daoFactory.getContactDAO(connection);
            TelephoneDAO mySQLtelephoneDAO = daoFactory.getTelephoneDAO(connection);
            connection.setAutoCommit(false);
            mySQLContactDAO.delete(id);
            /*
            for (TelephoneDTO telephoneDTO : dto.getTelephonesDTO()) {
                TelephoneEntity telephoneEntity = new TelephoneEntity(telephoneDTO, dto.getId());
                mySQLtelephoneDAO.delete(telephoneEntity.getId());
            }
            */
            connection.commit();
        } catch (SQLException | DAOException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    throw new RepositoryException(e1);
                }
            }
            throw new RepositoryException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RepositoryException(e);
                }
            }
        }
    }

    @Override
    public ContactDTO get(Integer id) throws RepositoryException {
        DAOFactory daoFactory = getDAOFactory();
        Connection connection = null;
        ContactDTO newContactDTO;
        try {
            connection = daoFactory.getConnection();
            ContactDAO mySQLContactDAO = daoFactory.getContactDAO(connection);
            TelephoneDAO mySQLtelephoneDAO = daoFactory.getTelephoneDAO(connection);
            connection.setAutoCommit(false);
            newContactDTO = new ContactDTO(mySQLContactDAO.getById(id));
            List<TelephoneEntity> telephoneEntities = mySQLtelephoneDAO.getByContactId(id);
            for (TelephoneEntity telephoneEntity : telephoneEntities) {
                newContactDTO.getTelephonesDTO().add(new TelephoneDTO(telephoneEntity));
            }
            connection.commit();
        } catch (SQLException | DAOException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    throw new RepositoryException(e1);
                }
            }
            throw new RepositoryException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RepositoryException(e);
                }
            }
        }
        return newContactDTO;
    }

    @Override
    public List<ContactDTO> toList() throws RepositoryException {
        DAOFactory daoFactory = getDAOFactory();
        Connection connection = null;
        List<ContactDTO> contactDTOs = new ArrayList<>();

        try {
            ContactDAO mySQLContactDAO = daoFactory.getContactDAO(connection);
            TelephoneDAO mySQLtelephoneDAO = daoFactory.getTelephoneDAO(connection);
            connection.setAutoCommit(false);
            for (ContactEntity contactEntity : mySQLContactDAO.getAll()) {
                ContactDTO newContactDTO = new ContactDTO(contactEntity);
                List<TelephoneEntity> telephoneEntities = mySQLtelephoneDAO.getByContactId(contactEntity.getId());
                for (TelephoneEntity telephoneEntity : telephoneEntities) {
                    newContactDTO.getTelephonesDTO().add(new TelephoneDTO(telephoneEntity));
                }
                contactDTOs.add(newContactDTO);
            }
            connection.commit();
        } catch (SQLException | DAOException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    throw new RepositoryException(e1);
                }
            }
            throw new RepositoryException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RepositoryException(e);
                }
            }
        }
        return contactDTOs;
    }

    @Override
    public int size() throws RepositoryException {
        DAOFactory daoFactory = getDAOFactory();
        try (Connection connection = daoFactory.getConnection()) {
            ContactDAO mySQLContactDAO = daoFactory.getContactDAO(connection);
            return mySQLContactDAO.getNumber();
        } catch (SQLException | DAOException e) {
            throw new RepositoryException(e);
        }
    }


    @Override
    public List<ContactDTO> getSerchSortLimit(ContactDTO contactDTO, int offset, int number, Map<String, Boolean> sortFields) throws RepositoryException {
        DAOFactory daoFactory = getDAOFactory();
        Connection connection = null;
        List<ContactDTO> contactDTOs = new ArrayList<>();
        ContactEntity sortContactEntity = null;
        if (contactDTO != null) {
            sortContactEntity = new ContactEntity(contactDTO);
        }
        try {
            connection = daoFactory.getConnection();
            ContactDAO mySQLContactDAO = daoFactory.getContactDAO(connection);
            TelephoneDAO mySQLtelephoneDAO = daoFactory.getTelephoneDAO(connection);

            for (ContactEntity contactEntity : mySQLContactDAO.getSerchSortLimit(sortContactEntity, offset, number, sortFields)) {
                ContactDTO newContactDTO = new ContactDTO(contactEntity);
                List<TelephoneEntity> telephoneEntities = mySQLtelephoneDAO.getByContactId(contactEntity.getId());
                for (TelephoneEntity telephoneEntity : telephoneEntities) {
                    newContactDTO.getTelephonesDTO().add(new TelephoneDTO(telephoneEntity));
                }
                contactDTOs.add(newContactDTO);
            }

        } catch (DAOException e) {
            throw new RepositoryException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RepositoryException(e);
                }
            }
        }
        return contactDTOs;
    }

    @Override
    public int sizeWithSerch(ContactDTO contactDTO) throws RepositoryException {
        DAOFactory daoFactory = getDAOFactory();
        ContactEntity sortContactEntity = null;
        if (contactDTO != null) {
            sortContactEntity = new ContactEntity(contactDTO);
        }
        try (Connection connection = daoFactory.getConnection()) {
            ContactDAO mySQLContactDAO = daoFactory.getContactDAO(connection);
            return mySQLContactDAO.getNumberSerch(sortContactEntity);
        } catch (SQLException | DAOException e) {
            throw new RepositoryException(e);
        }
    }


}
