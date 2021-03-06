package by.itechart.phonebook.DAO;


import org.apache.commons.dbcp.BasicDataSource;


import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public abstract class DAOFactory {

    private static volatile DAOFactory mySQLDAOFactory;

    public abstract ContactDAO getContactDAO(Connection connection) throws DAOException;

    public abstract TelephoneDAO getTelephoneDAO(Connection connection) throws DAOException;

    public abstract AttachmentDAO getAttachmentDAO(Connection connection) throws DAOException;


    public abstract Connection getConnection() throws DAOException;

    private static DAOFactory getMySQLDAOFactory(){
        DAOFactory  localInstance = mySQLDAOFactory;
        if (localInstance == null) {
            synchronized (DAOFactory .class) {
                localInstance = mySQLDAOFactory;
                if (localInstance == null) {
                    mySQLDAOFactory = localInstance = new MySQLDAOFactory();
                }
            }
        }
        return localInstance;
    }

    public static DAOFactory getDAOFactory(TypeDAOFactory typeDAOFactory) {
        switch (typeDAOFactory) {
            case MySQL:
                return getMySQLDAOFactory();
            default:
                return null;
        }
    }
}


class MySQLDAOFactory extends DAOFactory {
    private DataSource dataSource;
    MySQLDAOFactory() {
        this.dataSource = getDataSource();
    }


    public TelephoneDAO getTelephoneDAO(Connection connection) {
        return new MySQLTelephoneDAO(connection);

    }

    @Override
    public AttachmentDAO getAttachmentDAO(Connection connection) throws DAOException {
        return new MySQLAttachmentDAO(connection);
    }



    public ContactDAO getContactDAO(Connection connection) {
        return new MySQLContactDAO(connection);

    }

    public Connection getConnection() throws DAOException {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    private static DataSource getDataSource() {
        BasicDataSource ds = new BasicDataSource();
        Properties prop = new Properties();
        InputStream input = null;
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try {
            input = classLoader.getResourceAsStream("db.properties");
            prop.load(input);
            ds.setDriverClassName(prop.getProperty("driverclassname"));
            ds.setUsername(prop.getProperty("username"));
            ds.setPassword(prop.getProperty("password"));
            ds.setUrl(prop.getProperty("url"));
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //ds.setDriverClassName("com.mysql.jdbc.Driver");
        //ds.setUsername("alex");
        //ds.setPassword("123456789");
        //ds.setUrl("jdbc:mysql://localhost:3306/phonebooknatashkin?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");

        return ds;
    }

}
