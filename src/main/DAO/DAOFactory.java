package main.DAO;




import org.apache.commons.dbcp.BasicDataSource;


import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.SQLException;

public abstract class DAOFactory {
    public abstract ContactDAO getContactDAO() throws DAOException;
    public abstract TelephoneDAO getTelephoneDAO() throws DAOException;
    public static DAOFactory getDAOFactory(TypeDAOFactory typeDAOFactory){
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



    public TelephoneDAO getTelephoneDAO() throws DAOException {
        try {
            return new MySQLTelephoneDAO(getDataSource());
        } catch (NamingException|SQLException e) {
            throw new DAOException(e);
        }
    }
    public ContactDAO getContactDAO() throws DAOException {
        try {
            return new MySQLContactDAO(getDataSource());
        } catch (NamingException |SQLException e) {
            throw new DAOException(e);
        }
    }

    private static DataSource getDataSource() throws SQLException, NamingException {
        //TO DO Переделать, а то для каждого вызова создаеться новый DataSource
        //TO DO Вынести в пропери файл
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUsername("root");
        ds.setPassword("root");
        ds.setUrl("jdbc:mysql://localhost:3306/phonebook?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
        return ds;
    }

}
