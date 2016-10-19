package by.itechart.phonebook.DAO;



import by.itechart.phonebook.Entity.ContactEntity;
import by.itechart.phonebook.SQLGenerator.StringQuere;
import by.itechart.phonebook.SQLGenerator.StringQuereBuilder;

import java.sql.*;
import java.util.*;

public class MySQLContactDAO implements ContactDAO {


    private static final String updateQuere = "UPDATE contact SET firstname=?, secondname=?, patronymic=?, birthday=?, male=?,  nationality=?, relationshipstatus=?, webSite=?, email=?, country=?, city=?, street=?, ind=?, company=?, photo_path=? where id=?";
    private static final String deleteQuere = "DELETE FROM contact WHERE id=?";
    private static final String getAllQuere = "SELECT * FROM contact";
    private static final String getByIDQuere = "SELECT * FROM contact WHERE id=?";
    private static final String insertQuere = "INSERT INTO contact (firstname, secondname, patronymic, birthday, male,  nationality, relationshipstatus, webSite, email, country, city, street, ind, company, photo_path) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    //TO DO может есть более оптимальный запрос на количесво элементов
    private static final String getNumberQuere = "SELECT COUNT(*) FROM contact";
    private static final String getbirthdays = "SELECT * FROM contact where DAYOFYEAR(contact.birthday)=DAYOFYEAR(CURDATE())";
    private static final String getSortLimit = "SELECT * FROM contact ORDER BY ? DESC LIMIT ?, ?";


    private Connection connection;

    public MySQLContactDAO(Connection connection) {
        this.connection = connection;
    }

    private Connection getConnection() {
        return connection;
    }



    public ContactEntity create(ContactEntity entity) throws DAOException {
        List<ContactEntity> contactEntities = new ArrayList<>();
        try (PreparedStatement createPreparedStatement = getConnection().prepareStatement(insertQuere)) {
            createPreparedStatement.setString(1, entity.getFirstName());
            createPreparedStatement.setString(2, entity.getSecondName());
            createPreparedStatement.setString(3, entity.getPatronymic());
            createPreparedStatement.setDate(4, entity.getBirthday());
           // createPreparedStatement.setBoolean(5, entity.getMale());
            createPreparedStatement.setObject(5,entity.getMale());
            createPreparedStatement.setString(6, entity.getNationality());
            createPreparedStatement.setString(7, entity.getRelationshipStatus());
            createPreparedStatement.setString(8, entity.getWebSite());
            createPreparedStatement.setString(9, entity.getEmail());
            createPreparedStatement.setString(10, entity.getCountry());
            createPreparedStatement.setString(11, entity.getCity());
            createPreparedStatement.setString(12, entity.getStreet());
            createPreparedStatement.setString(13, entity.getIndex());
            createPreparedStatement.setString(14, entity.getCompany());
            createPreparedStatement.setString(15, entity.getPhotoPath());
            boolean result = createPreparedStatement.execute();
            //TO DO добавить проверку на добавление
            ResultSet resultSet = createPreparedStatement.executeQuery("SELECT * FROM contact WHERE id = last_insert_id()");
            contactEntities = parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return contactEntities.get(0);
    }

    public void update(ContactEntity entity) throws DAOException {
        try (PreparedStatement updatePreparedStatement = getConnection().prepareStatement(updateQuere)) {
            updatePreparedStatement.setString(1, entity.getFirstName());
            updatePreparedStatement.setString(2, entity.getSecondName());
            updatePreparedStatement.setString(3, entity.getPatronymic());
            updatePreparedStatement.setDate(4, entity.getBirthday());
            updatePreparedStatement.setObject(5,entity.getMale());
            updatePreparedStatement.setString(6, entity.getNationality());
            updatePreparedStatement.setString(7, entity.getRelationshipStatus());
            updatePreparedStatement.setString(8, entity.getWebSite());
            updatePreparedStatement.setString(9, entity.getEmail());
            updatePreparedStatement.setString(10, entity.getCountry());
            updatePreparedStatement.setString(11, entity.getCity());
            updatePreparedStatement.setString(12, entity.getStreet());
            updatePreparedStatement.setString(13, entity.getIndex());
            updatePreparedStatement.setString(14, entity.getCompany());
            updatePreparedStatement.setString(15, entity.getPhotoPath());
            updatePreparedStatement.setInt(16, entity.getId());
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

    public ContactEntity getById(Integer id) throws DAOException {
        List<ContactEntity> contactEntities;
        try (PreparedStatement getByIdPreparedStatement = getConnection().prepareStatement(getByIDQuere)) {
            getByIdPreparedStatement.setInt(1, id);
            ResultSet resultSet = getByIdPreparedStatement.executeQuery();
            contactEntities = parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        if (contactEntities.size() != 0) {
            return contactEntities.get(0);
        }
        return null;
    }

    @Override
    public List<ContactEntity> getAll() throws DAOException {
        List<ContactEntity> contactEntities;
        try (PreparedStatement getAllPreparedStatement = getConnection().prepareStatement(getAllQuere)) {
            ResultSet resultSet = getAllPreparedStatement.executeQuery();
            contactEntities = parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return contactEntities;
    }

    @Override
    public int getNumber() throws DAOException {
        int number;
        try (PreparedStatement getNumberStatement = getConnection().prepareStatement(getNumberQuere)) {
            ResultSet resultSet = getNumberStatement.executeQuery();
            resultSet.next();
            number = resultSet.getInt(1);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return number;
    }

    private List<ContactEntity> parseResultSet(ResultSet resultSet) throws DAOException {
        List<ContactEntity> contactEntities = new ArrayList<>();
        try {
            while (resultSet.next()) {
                ContactEntity contactEntity = new ContactEntity();
                contactEntity.setId(resultSet.getInt("id"));
                contactEntity.setBirthday(resultSet.getDate("birthday"));
                contactEntity.setCity(resultSet.getString("city"));
                contactEntity.setCompany(resultSet.getString("company"));
                contactEntity.setEmail(resultSet.getString("email"));
                contactEntity.setCountry(resultSet.getString("country"));
                contactEntity.setFirstName(resultSet.getString("firstname"));
                contactEntity.setSecondName(resultSet.getString("secondname"));
                contactEntity.setIndex(resultSet.getString("ind"));
                contactEntity.setMale(resultSet.getObject("male")==null?null: (Boolean) resultSet.getObject("male"));
                contactEntity.setStreet(resultSet.getString("street"));
                contactEntity.setNationality(resultSet.getString("nationality"));
                contactEntity.setPatronymic(resultSet.getString("patronymic"));
                contactEntity.setRelationshipStatus(resultSet.getString("relationshipstatus"));
                contactEntity.setWebSite(resultSet.getString("website"));
                contactEntity.setCompany(resultSet.getString("company"));
                contactEntity.setPhotoPath(resultSet.getString("photo_path"));
                contactEntities.add(contactEntity);
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return contactEntities;
    }

    @Override
    public List<ContactEntity> getSerchSortLimit(ContactEntity contactEntity, int offset, int count, Map<String, Boolean> sortFields) throws DAOException {
        //TO Do добавить день рождение и пол
        //Разобраться что бы работал поиск с пустыми полями
        StringQuere stringQuere = StringQuereBuilder.getStringQuere().select("*").from("contact");
        if (contactEntity != null) {
            stringQuere.where().like("firstname", contactEntity.getFirstName()).
                    like("secondname", contactEntity.getSecondName()).like("patronymic", contactEntity.getPatronymic()).
                    like("nationality", contactEntity.getNationality()).like("relationshipstatus", contactEntity.getRelationshipStatus()).like("country", contactEntity.getCountry()).
                    like("city", contactEntity.getCity()).like("street", contactEntity.getStreet()).like("ind", contactEntity.getIndex()).like("company", contactEntity.getCompany());
        }
        if (sortFields != null) {
            Set<String> fields = sortFields.keySet();
            for (String field : fields) {
                Boolean isDesc = sortFields.get(field);
                stringQuere.orderBy(field, isDesc);
            }
        }
        stringQuere.limit(offset, count);
        List<ContactEntity> contactEntities;
        try (PreparedStatement getAllPreparedStatement = getConnection().prepareStatement(stringQuere.toString())) {
            ResultSet resultSet = getAllPreparedStatement.executeQuery();
            contactEntities = parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return contactEntities;
    }

    @Override
    public int getNumberSerch(ContactEntity contactEntity) throws DAOException {
        //TO Do добавить день рождение и пол
        StringQuere stringQuere = StringQuereBuilder.getStringQuere().select("COUNT(*)").from("contact");
        if (contactEntity != null) {
            stringQuere.where().like("firstname", contactEntity.getFirstName()).
                    like("secondname", contactEntity.getSecondName()).like("patronymic", contactEntity.getPatronymic()).
                    like("nationality", contactEntity.getNationality()).like("relationshipstatus", contactEntity.getRelationshipStatus()).like("country", contactEntity.getCountry()).
                    like("city", contactEntity.getCity()).like("street", contactEntity.getStreet()).like("ind", contactEntity.getIndex()).like("company", contactEntity.getCompany());
        }
        int number;
        try (PreparedStatement getNumberSerchStatement = getConnection().prepareStatement(stringQuere.toString())) {
            ResultSet resultSet = getNumberSerchStatement.executeQuery();
            resultSet.next();
            number = resultSet.getInt(1);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return number;
    }

    @Override
    public List<ContactEntity> getBirthdays() throws DAOException {
        List<ContactEntity> contactEntities;
        try (PreparedStatement getBirthdays = getConnection().prepareStatement(getbirthdays)) {
            ResultSet resultSet = getBirthdays.executeQuery();
            contactEntities = parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return contactEntities;
    }


}
