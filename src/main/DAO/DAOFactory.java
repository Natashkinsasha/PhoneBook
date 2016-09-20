package main.DAO;


import org.apache.commons.dbcp.BasicDataSource;


import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class DAOFactory {
    public abstract ContactDAO getContactDAO(Connection connection) throws DAOException;

    public abstract TelephoneDAO getTelephoneDAO(Connection connection) throws DAOException;

    public abstract Connection getConnection() throws DAOException;

    public static DAOFactory getDAOFactory(TypeDAOFactory typeDAOFactory) {
        switch (typeDAOFactory) {
            case MySQL:
                return new MySQLDAOFactory();
            default:
                return null;
        }
    }
}

//TO DO Уточнить правильно ли, что при каждом get создаеться новый DAO
class MySQLDAOFactory extends DAOFactory {
    MySQLDAOFactory() {
        DataSource dataSource = getDataSource();
    }


    public TelephoneDAO getTelephoneDAO(Connection connection) {
        return new MySQLTelephoneDAO(connection);

    }

    public ContactDAO getContactDAO(Connection connection) {
        return new MySQLContactDAO(connection);

    }

    public Connection getConnection() throws DAOException {
        try {
            return getDataSource().getConnection();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    private static DataSource getDataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUsername("root");
        ds.setPassword("root");
        ds.setUrl("jdbc:mysql://localhost:3306/phonebook?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
        return ds;
    }

}
