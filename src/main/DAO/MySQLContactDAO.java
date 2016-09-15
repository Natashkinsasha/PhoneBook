package main.DAO;


import main.DTO.ContactDTO;
import main.Entity.ContactEntity;

import javax.sql.DataSource;
import java.util.List;

public class MySQLContactDAO implements ContactDAO {

    private DataSource dataSource;
    private static final String updateQuere = "UPDATE contact SET firstname=?, secondname=?, patronymic=?, birthday=?, male=?,  nationality=?, relationshipstatus=?, webSite=?, email=?, country=?, city=?, street=?, index=? where id=?";
    private static final String deleteQuere = "DELETE * FROM contact WHERE id=?";
    private static final String getAllQuere = "SELECT * FROM contact";
    private static final String getByIDQuere = "SELECT * FROM contact WHERE id=?";
    private static final String insertQuere = "INSERT INTO contact (firstname, secondname, patronymic, birthday, male,  nationality, relationshipstatus, webSite, email, country, city, street, index) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public MySQLContactDAO(DataSource dataSource) {
        this.dataSource=dataSource;
    }

    private DataSource getDataSource() {
        return dataSource;
    }

    public ContactEntity create(ContactEntity entity) throws DAOException {
        return null;
    }

    public void update(ContactEntity entity) throws DAOException {

    }

    public void delete(ContactEntity entity) throws DAOException {

    }

    public ContactEntity getById(Integer id) throws DAOException {
        return null;
    }

    @Override
    public List<ContactEntity> getAll() throws DAOException {
        return null;
    }
}
