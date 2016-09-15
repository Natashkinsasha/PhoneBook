package test;

import main.DAO.DAOException;
import main.DAO.DAOFactory;
import main.DAO.TelephoneDAO;
import main.DAO.TypeDAOFactory;
import main.DTO.TelephoneDTO;
import main.Entity.TelephoneEntity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.naming.NamingException;
import java.sql.SQLException;

public class DAOFactoryJUnitTest {

    DAOFactory mySQLDAOFactory;
    TelephoneDAO telephoneDAO;

    @Before
    public void setUp() throws Exception {
        mySQLDAOFactory = DAOFactory.getDAOFactory(TypeDAOFactory.MySQL);
        telephoneDAO = mySQLDAOFactory.getTelephoneDAO();

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void create() throws DAOException {
        TelephoneEntity telephoneEntity = new TelephoneEntity();
        telephoneEntity.setNumber("+375296426384").setType("Mob").setComments("Номер Папы").setContactId(1);
        TelephoneEntity telephoneEntity1Return = telephoneDAO.create(telephoneEntity);
        System.out.println(telephoneEntity1Return);
    }

    @Test
    public void update() throws Exception {
        TelephoneEntity telephoneEntity = new TelephoneEntity();
        telephoneEntity.setNumber("+375297346123").setType("Mob").setComments("Номер Папы").setContactId(1);
        TelephoneEntity telephoneEntity1Return = telephoneDAO.create(telephoneEntity);
        System.out.println(telephoneEntity1Return);
        telephoneEntity = new TelephoneEntity();
        telephoneEntity.setNumber("+375296426384").setType("Mob").setComments("Номер Папы").setContactId(1).setId(telephoneEntity1Return.getId());
        telephoneDAO.update(telephoneEntity);
        TelephoneEntity telephoneEntityReturn = telephoneDAO.getById(telephoneEntity.getId());
        System.out.println(telephoneEntityReturn);
    }
}
