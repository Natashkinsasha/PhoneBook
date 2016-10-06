package test;


import main.java.Servic.EmailServiceImpl;
import main.java.Servic.EmailServise;
import main.java.Servic.ServiceException;
import org.junit.Assert;
import org.junit.Test;

public class EmailServiceImplJUnit extends Assert{
    @Test
    public void sendEmailTest() throws ServiceException {
        EmailServise emailServise = new EmailServiceImpl();
    }
}
