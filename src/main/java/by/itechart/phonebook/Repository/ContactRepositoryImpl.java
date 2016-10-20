package by.itechart.phonebook.Repository;


import by.itechart.phonebook.DAO.*;
import by.itechart.phonebook.DTO.AttachmentDTO;
import by.itechart.phonebook.DTO.ContactDTO;
import by.itechart.phonebook.DTO.TelephoneDTO;
import by.itechart.phonebook.Entity.AttachmentEntity;
import by.itechart.phonebook.Entity.ContactEntity;
import by.itechart.phonebook.Entity.TelephoneEntity;
import by.itechart.phonebook.Repository.ContactRepository;


import java.io.File;
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
            AttachmentDAO mySQLAttachmentDAO = daoFactory.getAttachmentDAO(connection);
            TelephoneDAO mySQLtelephoneDAO = daoFactory.getTelephoneDAO(connection);
            connection.setAutoCommit(false);
            newContactDTO = new ContactDTO(mySQLContactDAO.create(new ContactEntity(contactDTO)));
            for (TelephoneDTO telephoneDTO : contactDTO.getTelephonesDTO()) {
                newContactDTO.getTelephonesDTO().add(new TelephoneDTO(mySQLtelephoneDAO.create(new TelephoneEntity(telephoneDTO, newContactDTO.getId()))));
            }
            for (AttachmentDTO attachmentDTO : contactDTO.getAttachmentDTOs()) {
                newContactDTO.getAttachmentDTOs().add(new AttachmentDTO(mySQLAttachmentDAO.create(new AttachmentEntity(attachmentDTO, newContactDTO.getId()))));
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
            AttachmentDAO mySQLAttachmentDAO = daoFactory.getAttachmentDAO(connection);
            ContactEntity contactEntity = new ContactEntity(dto);
            List<TelephoneEntity> telephoneEntities = new ArrayList<>();
            for (TelephoneDTO telephoneDTO: dto.getTelephonesDTO()){
                TelephoneEntity telephoneEntity = new TelephoneEntity(telephoneDTO,dto.getId());
                telephoneEntities.add(telephoneEntity);
            }
            List<AttachmentEntity> attachmentEntities = new ArrayList<>();
            for (AttachmentDTO attachmentDTO:dto.getAttachmentDTOs()){
                AttachmentEntity attachmentEntity = new AttachmentEntity(attachmentDTO,dto.getId());
                attachmentEntities.add(attachmentEntity);
            }
            connection.setAutoCommit(false);
            mySQLContactDAO.update(contactEntity);
            if(telephoneEntities.size()>0) {
                mySQLtelephoneDAO.update(telephoneEntities);
            } else{
                mySQLtelephoneDAO.deleteAllByContactId(contactEntity.getId());
            }
            List<AttachmentEntity> deletedAttachmentList  = new ArrayList<>();
            if (attachmentEntities.size()>0) {
                deletedAttachmentList = mySQLAttachmentDAO.getDeletedAttachment(attachmentEntities);
                mySQLAttachmentDAO.update(attachmentEntities);
            } else {
                deletedAttachmentList = mySQLAttachmentDAO.getByContactId(contactEntity.getId());
                mySQLAttachmentDAO.deleteAllByContactId(contactEntity.getId());
            }
            connection.commit();
            for (AttachmentEntity attachmentEntity:deletedAttachmentList){
                File deletedAttachment = new File(attachmentEntity.getPath());
                deletedAttachment.delete();
            }
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
    public void delete(Integer... id) throws RepositoryException {
        DAOFactory daoFactory = getDAOFactory();
        Connection connection = null;
        try {
            connection = daoFactory.getConnection();
            ContactDAO mySQLContactDAO = daoFactory.getContactDAO(connection);
            TelephoneDAO mySQLtelephoneDAO = daoFactory.getTelephoneDAO(connection);
            connection.setAutoCommit(false);
            for (int i : id) {
                mySQLContactDAO.delete(i);
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
    public ContactDTO get(Integer id) throws RepositoryException {
        DAOFactory daoFactory = getDAOFactory();
        Connection connection = null;
        ContactDTO newContactDTO;
        try {
            connection = daoFactory.getConnection();
            ContactDAO mySQLContactDAO = daoFactory.getContactDAO(connection);
            TelephoneDAO mySQLtelephoneDAO = daoFactory.getTelephoneDAO(connection);
            AttachmentDAO mySQLAttachmentDAO = daoFactory.getAttachmentDAO(connection);
            connection.setAutoCommit(false);
            newContactDTO = new ContactDTO(mySQLContactDAO.getById(id));
            List<TelephoneEntity> telephoneEntities = mySQLtelephoneDAO.getByContactId(id);
            List<AttachmentEntity> attachmentEntities = mySQLAttachmentDAO.getByContactId(id);
            connection.commit();
            for (TelephoneEntity telephoneEntity : telephoneEntities) {
                newContactDTO.getTelephonesDTO().add(new TelephoneDTO(telephoneEntity));
            }
            for (AttachmentEntity attachmentEntity : attachmentEntities) {
                newContactDTO.getAttachmentDTOs().add(new AttachmentDTO(attachmentEntity));
            }

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
            connection = daoFactory.getConnection();
            ContactDAO mySQLContactDAO = daoFactory.getContactDAO(connection);
            TelephoneDAO mySQLtelephoneDAO = daoFactory.getTelephoneDAO(connection);
            AttachmentDAO mySQLAttachmentDAO = daoFactory.getAttachmentDAO(connection);
            connection.setAutoCommit(false);
            for (ContactEntity contactEntity : mySQLContactDAO.getAll()) {
                ContactDTO newContactDTO = new ContactDTO(contactEntity);
                List<TelephoneEntity> telephoneEntities = mySQLtelephoneDAO.getByContactId(contactEntity.getId());
                List<AttachmentEntity> attachmentEntities = mySQLAttachmentDAO.getByContactId(contactEntity.getId());
                for (TelephoneEntity telephoneEntity : telephoneEntities) {
                    newContactDTO.getTelephonesDTO().add(new TelephoneDTO(telephoneEntity));
                }
                for (AttachmentEntity attachmentEntity : attachmentEntities) {
                    newContactDTO.getAttachmentDTOs().add(new AttachmentDTO(attachmentEntity));
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
            AttachmentDAO mySQLAttachmentDAO = daoFactory.getAttachmentDAO(connection);
            connection.setAutoCommit(false);
            for (ContactEntity contactEntity : mySQLContactDAO.getSerchSortLimit(sortContactEntity, offset, number, sortFields)) {
                ContactDTO newContactDTO = new ContactDTO(contactEntity);
                List<TelephoneEntity> telephoneEntities = mySQLtelephoneDAO.getByContactId(contactEntity.getId());
                for (TelephoneEntity telephoneEntity : telephoneEntities) {
                    newContactDTO.getTelephonesDTO().add(new TelephoneDTO(telephoneEntity));
                }
                List<AttachmentEntity> attachmentEntities = mySQLAttachmentDAO.getByContactId(contactEntity.getId());
                for (AttachmentEntity attachmentEntity : attachmentEntities) {
                    newContactDTO.getAttachmentDTOs().add(new AttachmentDTO(attachmentEntity));
                }
                contactDTOs.add(newContactDTO);
            }
            connection.commit();

        } catch (SQLException|DAOException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    throw new RepositoryException(e1);
                }
            }
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

    @Override
    public List<ContactDTO> getBirthdays() throws RepositoryException {
        DAOFactory daoFactory = getDAOFactory();
        List<ContactDTO> contactDTOs = new ArrayList<>();
        try (Connection connection = daoFactory.getConnection()) {
            ContactDAO mySQLContactDAO = daoFactory.getContactDAO(connection);
            List<ContactEntity> birthdaysEntity = mySQLContactDAO.getBirthdays();
            for (ContactEntity contactEntity : birthdaysEntity) {
                contactDTOs.add(new ContactDTO(contactEntity));
            }
            return contactDTOs;
        } catch (SQLException | DAOException e) {
            throw new RepositoryException(e);
        }
    }


}
