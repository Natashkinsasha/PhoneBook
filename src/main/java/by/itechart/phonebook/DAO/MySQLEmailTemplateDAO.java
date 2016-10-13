package by.itechart.phonebook.DAO;

import by.itechart.phonebook.Entity.ContactEntity;
import by.itechart.phonebook.Entity.EmailTemplateEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MySQLEmailTemplateDAO implements EmailTemplateDAO {

    private static final String getAllQuere = "SELECT * FROM emailtemplate";
    private static final String getByIDQuere = "SELECT * FROM emailtemplate WHERE id=?";
    private Connection connection;

    public MySQLEmailTemplateDAO(Connection connection){
        this.connection=connection;
    }

    private Connection getConnection(){
        return connection;
    }


    @Override
    public EmailTemplateEntity create(EmailTemplateEntity entity) throws DAOException {
        return null;
    }

    @Override
    public void update(EmailTemplateEntity entity) throws DAOException {

    }

    @Override
    public void delete(Integer id) throws DAOException {

    }

    @Override
    public EmailTemplateEntity getById(Integer id) throws DAOException {
        List<EmailTemplateEntity> entities;
        try (PreparedStatement getByIdPreparedStatement = getConnection().prepareStatement(getByIDQuere)) {
            getByIdPreparedStatement.setInt(1, id);
            ResultSet resultSet = getByIdPreparedStatement.executeQuery();
            entities = parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        if (entities.size() != 0) {
            return entities.get(0);
        }
        return null;
    }

    @Override
    public List<EmailTemplateEntity> getAll() throws DAOException {
        List<EmailTemplateEntity> entities;
        try (PreparedStatement getAllPreparedStatement = getConnection().prepareStatement(getAllQuere)) {
            ResultSet resultSet = getAllPreparedStatement.executeQuery();
            entities = parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return entities;
    }

    private List<EmailTemplateEntity> parseResultSet(ResultSet resultSet) throws DAOException{
        List<EmailTemplateEntity> emailTemplateEntitys = new ArrayList<>();
        try {
            while (resultSet.next()) {
                EmailTemplateEntity emailTemplateEntity = new EmailTemplateEntity();
                emailTemplateEntity.setId(resultSet.getInt("id"));
                emailTemplateEntity.setPath(resultSet.getString("path"));
                emailTemplateEntity.setEngName(resultSet.getString("eng_name"));
                emailTemplateEntity.setRuName(resultSet.getString("ru_name"));
                emailTemplateEntitys.add(emailTemplateEntity);
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return emailTemplateEntitys ;
    }


    @Override
    public int getNumber() throws DAOException {
        return 0;
    }
}
