package by.itechart.phonebook;


import by.itechart.phonebook.DTO.ContactDTO;
import by.itechart.phonebook.Email.Sender;
import by.itechart.phonebook.Repository.ContactRepository;
import by.itechart.phonebook.Repository.ContactRepositoryImpl;
import by.itechart.phonebook.Repository.RepositoryException;
import org.joda.time.DateTime;

import java.util.List;

public class HBEmailSender extends Thread {

    final static int sendedMillisTime = 8 * 60 * 60 * 1000;

    @Override
    public void run() {
        setDaemon(true);
        DateTime now = new DateTime();
        int nowMillis = now.getMillisOfDay();
        if (nowMillis <= sendedMillisTime) {
            try {
                sleep(sendedMillisTime - nowMillis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            try {
                sleep(sendedMillisTime+(24*60*60*100-sendedMillisTime));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ContactRepository contactRepository = new ContactRepositoryImpl();
        try {
            List<ContactDTO> birthdaysContacts = contactRepository.getBirthdays();
            //проверка на null
            StringBuffer text = new StringBuffer();

            for (ContactDTO contactDTO: birthdaysContacts){
                text.append(contactDTO.toString()).append("/n");
            }
            //вынести в проперти
            //добавить тимплейт
            Sender tlsSender = new Sender("natashkinsasha@gmail.com", "Natashkinsasha6426384");
            tlsSender.send("HB", text.toString(), "natashkinsasha@gmail.com", "natashkinsasha@gmail.com");
        } catch (RepositoryException e) {
            e.printStackTrace();
        }

        try {
            sleep(24*60*60*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
