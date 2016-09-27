package test;


import main.DAO.ContactDAO;
import main.DAO.DAOException;
import main.DAO.DAOFactory;
import main.DAO.TypeDAOFactory;
import main.Entity.ContactEntity;
import org.junit.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class ContactDAOJUnit extends Assert{
    private static ContactDAO contactDAO;
    private static Connection connection;

    @BeforeClass
    public static void start() throws Exception {
        DAOFactory daoFactory = DAOFactory.getDAOFactory(TypeDAOFactory.MySQL);
        connection = daoFactory.getConnection();
        contactDAO = daoFactory.getContactDAO(connection);
        //connection.setAutoCommit(false);
    }
    @AfterClass
    public static void finish() throws SQLException {
        //connection.rollback();
        connection.close();
    }



    @Test
    @Ignore
    public void saveTest() throws DAOException {
        ContactEntity alex = new ContactEntity();
        //TO DO Убрать депркейт метод
        alex.setFirstName("Alex").setSecondName("Natashkin").setCompany("ITechArt").setMale(true).setRelationshipStatus("free").setCompany("ITechArt");
        ContactEntity alexDB = contactDAO.create(alex);
        assertEquals(alex,alexDB);
    }

    @Test
    @Ignore
    public void deleteAndGetByIDTest() throws DAOException {
        ContactEntity alex = new ContactEntity();
        //TO DO Убрать депркейт метод
        alex.setFirstName("Alex").setSecondName("Natashkin").setCompany("ITechArt").setMale(true).setRelationshipStatus("free");
        ContactEntity alexDB = contactDAO.create(alex);
        assertEquals(alex,alexDB);
        ContactEntity secondAlexDB = contactDAO.getById(alexDB.getId());
        assertEquals(secondAlexDB,alexDB);
        contactDAO.delete(alexDB.getId());
        ContactEntity thirdAlexDB = contactDAO.getById(alexDB.getId());
        assertNull(thirdAlexDB);
    }

    @Test
    @Ignore
    public void updateTest() throws DAOException {
        ContactEntity alex = new ContactEntity();
        //TO DO Убрать депркейт метод
        alex.setFirstName("Alex").setSecondName("Natashkin").setCompany("ITechArt").setMale(true).setRelationshipStatus("free");
        ContactEntity alexDB = contactDAO.create(alex);
        assertEquals(alex,alexDB);
        alexDB.setFirstName("Sasha");
        contactDAO.update(alexDB);
        ContactEntity secondAlexDB = contactDAO.getById(alexDB.getId());
        assertNotEquals(alex,secondAlexDB);
        assertEquals(secondAlexDB.getFirstName(),"Sasha");
    }

    @Test
    public void getNuberTest() throws Exception {
        int number = contactDAO.getNumber();
    }

    @Test
    @Ignore
    public void updateGetSerchSortLimit() throws DAOException {
        //Тестировать только на пустой базе
        ContactEntity alex = new ContactEntity();
        ContactEntity pasha = new ContactEntity();
        ContactEntity firstSerchPattern = new ContactEntity();
        ContactEntity secondSerchPattern = new ContactEntity();
        //TO DO Убрать депркейт метод
        alex.setFirstName("Sasha").setSecondName("Natashkin").setCompany("ITechArt").setMale(true).setRelationshipStatus("free");
        pasha.setFirstName("Pasha").setSecondName("Rydak").setCompany("Oscar").setMale(true).setRelationshipStatus("free");
        firstSerchPattern.setFirstName("sh");
        secondSerchPattern.setCompany("Oscar");
        contactDAO.create(alex);
        contactDAO.create(pasha);
        List<ContactEntity> contactEntities = contactDAO.getSerchSortLimit(firstSerchPattern,0,20,null);
        //assertEquals(contactEntities.size(),2);
        contactEntities = contactDAO.getSerchSortLimit(secondSerchPattern,0,20,null);
        //assertEquals(contactEntities.size(),1);
        contactEntities = contactDAO.getSerchSortLimit(null,0,20,null);

    }


}
