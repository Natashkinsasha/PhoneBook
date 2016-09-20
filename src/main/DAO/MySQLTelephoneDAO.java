package main.DAO;


import main.DTO.TelephoneDTO;
import main.Entity.TelephoneEntity;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLTelephoneDAO implements TelephoneDAO {

    private static final String updateQuere = "UPDATE telephone SET number=?, type=?, comment=?, contact_id=? where id=?";
    private static final String deleteQuere = "DELETE * FROM telephone WHERE id=?";
    private static final String getAllQuere = "SELECT * FROM telephone";
    private static final String getByIDQuere = "SELECT * FROM telephone WHERE id=?";
    private static final String insertQuere = "INSERT INTO telephone (number, type, comment, contact_id) VALUES (?, ?, ?, ?);";
    private static final String getByContactIDQuere = "SELECT * FROM telephone WHERE contact_id=?";
    private static final String getNumberQuere = "SELECT COUNT(*) FROM telephone";
    private Connection connection;
    public MySQLTelephoneDAO(Connection connection) {
        this.connection=connection;
    }
    private Connection getConnection(){
        return connection;
    }

    //TO DO Удастоверица, что выбран лучший способ получения последней вставленой записи
    public TelephoneEntity create(TelephoneEntity entity) throws DAOException {
        List<TelephoneEntity> telephoneEntities = new ArrayList<>();
        try (PreparedStatement createPreparedStatement = getConnection().prepareStatement(insertQuere)) {
            createPreparedStatement.setString(1, entity.getNumber());
            createPreparedStatement.setString(2, entity.getType());
            createPreparedStatement.setString(3, entity.getComments());
            createPreparedStatement.setInt(4, entity.getContactId());
            boolean result = createPreparedStatement.execute();
            //TO DO добавить проверку на добавление
            ResultSet resultSet =  createPreparedStatement.executeQuery("SELECT * FROM telephone WHERE id = last_insert_id()");
            telephoneEntities = parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return telephoneEntities.get(0);
    }

    public void update(TelephoneEntity entity) throws DAOException {
        try (PreparedStatement updatePreparedStatement = getConnection().prepareStatement(updateQuere)) {
            updatePreparedStatement.setString(1, entity.getNumber());
            updatePreparedStatement.setString(2, entity.getType());
            updatePreparedStatement.setString(3, entity.getComments());
            updatePreparedStatement.setInt(4, entity.getContactId());
            updatePreparedStatement.setInt(5, entity.getId());
            int result = updatePreparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }

    }

    public void delete(Integer id) throws DAOException {
        try (PreparedStatement updatePreparedStatement = getConnection().prepareStatement(deleteQuere)) {
            updatePreparedStatement.setInt(1, id);
            boolean resultSet = updatePreparedStatement.execute();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public TelephoneEntity getById(Integer id) throws DAOException {
        List<TelephoneEntity> telephoneEntities;
        try (PreparedStatement getByIdPreparedStatement = getConnection().prepareStatement(getByIDQuere)) {
            getByIdPreparedStatement.setInt(1, id);
            ResultSet resultSet = getByIdPreparedStatement.executeQuery();
            telephoneEntities = parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return telephoneEntities.get(0);
    }

    @Override
    public List<TelephoneEntity> getAll() throws DAOException {
        List<TelephoneEntity> telephoneEntities;
        try (PreparedStatement getAllPreparedStatement = getConnection().prepareStatement(getAllQuere)) {
            ResultSet resultSet = getAllPreparedStatement.executeQuery();
            telephoneEntities = parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return telephoneEntities;
    }

    private List<TelephoneEntity> parseResultSet(ResultSet resultSet) throws DAOException {
        List<TelephoneEntity> telephoneEntities = new ArrayList<>();
        try {
            while (resultSet.next()) {
                TelephoneEntity telephoneEntity = new TelephoneEntity();
                telephoneEntity.setId(resultSet.getInt("id"));
                telephoneEntity.setNumber(resultSet.getString("number"));
                telephoneEntity.setType(resultSet.getString("type"));
                telephoneEntity.setComments(resultSet.getString("comment"));
                telephoneEntity.setContactId(resultSet.getInt("contact_id"));
                telephoneEntities.add(telephoneEntity);
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return telephoneEntities;
    }

    @Override
    public List<TelephoneEntity> getByContactId(int id) throws DAOException{
        List<TelephoneEntity> telephoneEntities;
        try (PreparedStatement getByIdPreparedStatement = getConnection().prepareStatement(getByContactIDQuere)) {
            getByIdPreparedStatement.setInt(1, id);
            ResultSet resultSet = getByIdPreparedStatement.executeQuery();
            telephoneEntities = parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return telephoneEntities;
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
}
