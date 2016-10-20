package by.itechart.phonebook.DAO;


import by.itechart.phonebook.DAO.TelephoneDAO;
import by.itechart.phonebook.Entity.ContactEntity;
import by.itechart.phonebook.Entity.TelephoneEntity;
import org.apache.commons.lang.ArrayUtils;


import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MySQLTelephoneDAO implements TelephoneDAO {

    private static final String updateQuere = "UPDATE telephone SET number=?, type=?, comment=?, country_code=?, operator_code=?, contact_id=? where id=?";
    private static final String deleteQuere = "DELETE  FROM telephone WHERE id=?";
    private static final String getAllQuere = "SELECT * FROM telephone";
    private static final String getByIDQuere = "SELECT * FROM telephone WHERE id=?";
    private static final String insertQuere = "INSERT INTO telephone (number, type, comment, country_code, operator_code, contact_id) VALUES (?, ?, ?, ?, ?, ?);";
    private static final String getByContactIDQuere = "SELECT * FROM telephone WHERE contact_id=?";
    private static final String getNumberQuere = "SELECT COUNT(*) FROM telephone";
    private static final String deleteExcludedQuere = "DELETE FROM telephone where telephone.contact_id=? and telephone.id not in (arrive)";
    private Connection connection;

    public MySQLTelephoneDAO(Connection connection) {
        this.connection = connection;
    }

    private Connection getConnection() {
        return connection;
    }


    @Override
    public void update(List<TelephoneEntity> telephoneEntities) throws DAOException {
        Integer[] ids = telephoneEntities.stream().map(elemnt -> elemnt.getId()).toArray(Integer[]::new);
        StringBuffer arrive= new StringBuffer();
        for (int i = 1; i<=ids.length; i++){
            arrive.append("?");
            if(i!=ids.length){
                arrive.append(",");
            }
        }
        String newDeleteExcludedQuere=deleteExcludedQuere.replaceAll("arrive",arrive.toString());
        try (PreparedStatement statement = getConnection().prepareStatement(newDeleteExcludedQuere)) {
            statement.setInt(1, telephoneEntities.get(0).getContactId());
            for (int i = 0; i<ids.length; i++){
                statement.setInt(i+2,ids[i]);
            }
            statement.execute();
            TelephoneEntity[] telephonesForUpdate = telephoneEntities.stream().filter((s) -> s.getId() > 0).toArray(TelephoneEntity[]::new);
            for (TelephoneEntity telephone : telephonesForUpdate) {
                update(telephone);
            }
            TelephoneEntity[] telephonesForAdd = telephoneEntities.stream().filter((s) -> s.getId() < 0).toArray(TelephoneEntity[]::new);
            for (TelephoneEntity telephone : telephonesForAdd) {
                create(telephone);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    //TO DO Удастоверица, что выбран лучший способ получения последней вставленой записи
    public TelephoneEntity create(TelephoneEntity entity) throws DAOException {
        List<TelephoneEntity> telephoneEntities = new ArrayList<>();
        try (PreparedStatement createPreparedStatement = getConnection().prepareStatement(insertQuere)) {
            createPreparedStatement.setString(1, entity.getNumber());
            createPreparedStatement.setString(2, entity.getType());
            createPreparedStatement.setString(3, entity.getComments());
            createPreparedStatement.setString(4, entity.getCountryCode());
            createPreparedStatement.setString(5, entity.getOperatorCode());
            createPreparedStatement.setInt(6, entity.getContactId());
            createPreparedStatement.execute();
            //TO DO добавить проверку на добавление
            ResultSet resultSet = createPreparedStatement.executeQuery("SELECT * FROM telephone WHERE id = last_insert_id()");
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
            updatePreparedStatement.setString(4, entity.getCountryCode());
            updatePreparedStatement.setString(5, entity.getOperatorCode());
            updatePreparedStatement.setInt(6, entity.getContactId());
            updatePreparedStatement.setInt(7, entity.getId());
            updatePreparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }

    }

    public void delete(Integer id) throws DAOException {
        try (PreparedStatement deletePreparedStatement = getConnection().prepareStatement(deleteQuere)) {
            deletePreparedStatement.setInt(1, id);
            deletePreparedStatement.execute();
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
                telephoneEntity.setOperatorCode(resultSet.getString("operator_code"));
                telephoneEntity.setCountryCode(resultSet.getString("country_code"));
                telephoneEntities.add(telephoneEntity);
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return telephoneEntities;
    }

    @Override
    public List<TelephoneEntity> getByContactId(int id) throws DAOException {
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
