




import by.itechart.phonebook.DTO.ContactDTO;
import by.itechart.phonebook.Email.Sender;
import by.itechart.phonebook.Servis.ServiceException;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

public class SendEmailFormControlerJUnit extends Assert {
    @Test
    public void generateTest(){
        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setFirstName("Alex").setSecondName("Natashkin");
        VelocityEngine ve = new VelocityEngine();
        ve.init();
        Template t = ve.getTemplate("template.vm");
        VelocityContext context = new VelocityContext();
        context.put("firstname", contactDTO.getFirstName());
        context.put("secondname", contactDTO.getSecondNameString());
        StringWriter writer = new StringWriter();
        t.merge( context, writer );
        System.out.println(writer.toString());
    }

    @Test
    public void sendEmailTest() throws ServiceException {
        Sender tlsSender = new Sender("natashkinsasha@gmail.com", "Natashkinsasha6426384");
        tlsSender.send("Test", "Test", "natashkinsasha@gmail.com", "natashkinsasha@gmail.com");
    }
}
