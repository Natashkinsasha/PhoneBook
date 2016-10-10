package by.itechart.phonebook.Servic;


public interface EmailServise {
    void sendEmail(String from, String password, String whom, String theme, String text) throws ServiceException;
}

