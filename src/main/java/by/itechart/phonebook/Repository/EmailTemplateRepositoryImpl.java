package by.itechart.phonebook.Repository;


import by.itechart.phonebook.DAO.DAOException;
import by.itechart.phonebook.DAO.DAOFactory;
import by.itechart.phonebook.DAO.EmailTemplateDAO;
import by.itechart.phonebook.DAO.TypeDAOFactory;
import by.itechart.phonebook.DTO.EmailTemplateDTO;
import by.itechart.phonebook.Entity.EmailTemplateEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmailTemplateRepositoryImpl implements EmailTemplateRepository {
    @Override
    public EmailTemplateDTO create(EmailTemplateDTO dto) throws RepositoryException {
        return null;
    }

    @Override
    public void update(EmailTemplateDTO dto) throws RepositoryException {

    }

    @Override
    public void delete(Integer id) throws RepositoryException {

    }

    @Override
    public EmailTemplateDTO get(Integer id) throws RepositoryException {
        DAOFactory daoFactory = getDAOFactory();
        Connection connection = null;
        EmailTemplateDTO emailTemplateDTO;
        try {
            connection = daoFactory.getConnection();
            EmailTemplateDAO emailTemplateDAO = daoFactory.getEmailTemplateDAO(connection);
            connection.setAutoCommit(false);
            emailTemplateDTO = new EmailTemplateDTO(emailTemplateDAO.getById(id));
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
        return emailTemplateDTO;
    }

    @Override
    public List<EmailTemplateDTO> toList() throws RepositoryException {
        DAOFactory daoFactory = getDAOFactory();
        Connection connection = null;
        List<EmailTemplateDTO> emailTemplateDTOList = new ArrayList<>();
        try {
            connection = daoFactory.getConnection();
            EmailTemplateDAO emailTemplateDAO = daoFactory.getEmailTemplateDAO(connection);
            connection.setAutoCommit(false);
            for (EmailTemplateEntity emailTemplateEntity: emailTemplateDAO.getAll()){
                emailTemplateDTOList.add(new EmailTemplateDTO(emailTemplateEntity));
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
        return emailTemplateDTOList;
    }

    @Override
    public int size() throws RepositoryException {
        return 0;
    }

    private DAOFactory getDAOFactory() {
        return DAOFactory.getDAOFactory(TypeDAOFactory.MySQL);
    }
}
