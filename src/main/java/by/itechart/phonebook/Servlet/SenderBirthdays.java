package by.itechart.phonebook.Servlet;

import by.itechart.phonebook.DTO.ContactDTO;
import by.itechart.phonebook.Repository.ContactRepository;
import by.itechart.phonebook.Repository.ContactRepositoryImpl;
import by.itechart.phonebook.Repository.RepositoryException;
import by.itechart.phonebook.Servis.SenderService;
import by.itechart.phonebook.Servis.SenderServiceImpl;
import com.mysql.jdbc.StringUtils;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.mail.MessagingException;
import java.util.List;

public class SenderBirthdays implements Job {
    private Logger logger = Logger.getLogger(SenderBirthdays.class.getName());

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        logger.info("began send birthdays to admin");
        //Перенести
        String message = createMessage();
        if (!StringUtils.isNullOrEmpty(message)) {
            SenderService senderService = new SenderServiceImpl();
            senderService.send("Birthdays", message, "natashkinsasha@gmail.com", "natashkinsasha@gmail.com");
            logger.info("birthdays sent");
        }
    }

    private String createMessage() {
        ContactRepository contactRepository = new ContactRepositoryImpl();
        StringBuffer text = new StringBuffer();
        try {
            List<ContactDTO> birthdaysContacts = contactRepository.getBirthdays();
            for (ContactDTO contactDTO : birthdaysContacts) {
                text.append(contactDTO.toString()).append("/n");
            }
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
        return text.toString();
    }

}
