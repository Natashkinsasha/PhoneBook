package by.itechart.phonebook.Servis;


public interface EmailServise {
    void sendEmail(String from, String password, String whom, String theme, String text) throws ServiceException;
}

