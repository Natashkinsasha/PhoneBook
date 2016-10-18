package by.itechart.phonebook.DAO;


import by.itechart.phonebook.DTO.AttachmentDTO;
import by.itechart.phonebook.Entity.AttachmentEntity;
import by.itechart.phonebook.Entity.TelephoneEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLAttachmentDAO implements AttachmentDAO{

    private static final String updateQuere = "UPDATE attachment SET path=?, creation_date=?, name=?, comment=? where id=?";
    private static final String deleteQuere = "DELETE FROM attachment WHERE id=?";
    private static final String getAllQuere = "SELECT * FROM attachment";
    private static final String getByIDQuere = "SELECT * FROM attachment WHERE id=?";
    private static final String insertQuere = "INSERT INTO attachment (path, creation_date, name, comment, contact_id) VALUES (?, ?, ?, ?, ?)";
    private static final String getNumberQuere = "SELECT COUNT(*) FROM attachments";
    private static final String getByContactIDQuere = "SELECT * FROM attachment WHERE contact_id=?";
    private static final String deleteExcludedQuere = "DELETE * FROM attachment where attachment.contact_id=? and attachment.id not in (?)";

    private Connection connection;
    public MySQLAttachmentDAO(Connection connection) {
        this.connection=connection;
    }

    private Connection getConnection() {
        return connection;
    }

    @Override
    public AttachmentEntity create(AttachmentEntity entity) throws DAOException {
        List<AttachmentEntity> attachmentEntities = new ArrayList<>();
        try (PreparedStatement createPreparedStatement = getConnection().prepareStatement(insertQuere)) {
            createPreparedStatement.setString(1, entity.getPath());
            createPreparedStatement.setDate(2, entity.getCreationDate());
            createPreparedStatement.setString(3, entity.getName());
            createPreparedStatement.setString(4, entity.getComment());
            createPreparedStatement.setInt(5, entity.getContactId());
            createPreparedStatement.execute();
            ResultSet resultSet =  createPreparedStatement.executeQuery("SELECT * FROM attachment WHERE id = last_insert_id()");
            attachmentEntities = parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return attachmentEntities.get(0);
    }

    private List<AttachmentEntity> parseResultSet(ResultSet resultSet) throws DAOException {
        List<AttachmentEntity> attachmentEntities = new ArrayList<>();
        try {
            while (resultSet.next()) {
                AttachmentEntity attachmentEntity = new AttachmentEntity();
                attachmentEntity.setId(resultSet.getInt("id"));
                attachmentEntity.setComment(resultSet.getString("comment"));
                attachmentEntity.setCreationDate(resultSet.getDate("creation_date"));
                attachmentEntity.setName(resultSet.getString("name"));
                attachmentEntity.setPath(resultSet.getString("path"));
                attachmentEntities.add(attachmentEntity);
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return attachmentEntities;
    }

    @Override
    public void update(AttachmentEntity entity) throws DAOException {
        try (PreparedStatement updatePreparedStatement = getConnection().prepareStatement(updateQuere)) {
            updatePreparedStatement.setString(1, entity.getPath());
            updatePreparedStatement.setDate(2, entity.getCreationDate());
            updatePreparedStatement.setString(3, entity.getName());
            updatePreparedStatement.setString(4, entity.getComment());
            updatePreparedStatement.setInt(5, entity.getId());
            updatePreparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void delete(Integer id) throws DAOException {
        try (PreparedStatement deletePreparedStatement = getConnection().prepareStatement(deleteQuere)) {
            deletePreparedStatement.setInt(1, id);
            deletePreparedStatement.execute();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public AttachmentEntity getById(Integer id) throws DAOException {
        List<AttachmentEntity> attachmentEntities;
        try (PreparedStatement getByIdPreparedStatement = getConnection().prepareStatement(getByIDQuere)) {
            getByIdPreparedStatement.setInt(1, id);
            ResultSet resultSet = getByIdPreparedStatement.executeQuery();
            attachmentEntities = parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return attachmentEntities.get(0);
    }

    @Override
    public List<AttachmentEntity> getAll() throws DAOException {
        List<AttachmentEntity> attachmentEntities;
        try (PreparedStatement getAllPreparedStatement = getConnection().prepareStatement(getAllQuere)) {
            ResultSet resultSet = getAllPreparedStatement.executeQuery();
            attachmentEntities = parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return attachmentEntities;
    }

    @Override
    public int getNumber() throws DAOException {
        int number;
        try (PreparedStatement getNumberStatement = getConnection().prepareStatement(getNumberQuere)) {
            ResultSet resultSet = getNumberStatement.executeQuery();
            number = resultSet.getInt(1);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return number;
    }

    @Override
    public List<AttachmentEntity> getByContactId(int id) throws DAOException{
        List<AttachmentEntity> attachmentEntities;
        try (PreparedStatement getByIdPreparedStatement = getConnection().prepareStatement(getByContactIDQuere)) {
            getByIdPreparedStatement.setInt(1, id);
            ResultSet resultSet = getByIdPreparedStatement.executeQuery();
            attachmentEntities = parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return attachmentEntities;
    }

    @Override
    public void update(List<AttachmentEntity> attachmentEntities) throws DAOException {
        try (PreparedStatement statement = getConnection().prepareStatement(deleteExcludedQuere)) {
            statement.setInt(1,attachmentEntities.get(0).getContactId());
            Integer[] ids = attachmentEntities.stream().map(elemnt -> elemnt.getId()).toArray(Integer[]::new);
            Array idsArray = getConnection().createArrayOf("Integer", ids);
            statement.setArray(2, idsArray);
            statement.execute();
            AttachmentEntity[] attachmentsForUpdate = attachmentEntities.stream().filter((s) -> s.getId() > 0).toArray(AttachmentEntity[]::new);
            for (AttachmentEntity attachment: attachmentsForUpdate){
                update(attachment);
            }
            AttachmentEntity[] attachmentsForAdd = attachmentEntities.stream().filter((s) -> s.getId() < 0).toArray(AttachmentEntity[]::new);
            for (AttachmentEntity attachment: attachmentsForAdd){
                create(attachment);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }

    }
}
