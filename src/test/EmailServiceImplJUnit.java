package test;


import main.Servic.EmailServiceImpl;
import main.Servic.EmailServise;
import main.Servic.ServiceException;
import org.junit.Assert;
import org.junit.Test;

public class EmailServiceImplJUnit extends Assert{
    @Test
    public void sendEmailTest() throws ServiceException {
        EmailServise emailServise = new EmailServiceImpl();
        emailServise.sendEmail("natashkinsasha@gmail.com", "Natashkinsasha6426384", "natashkinsasha@gmail.com", "Test", "Test");
    }
}
