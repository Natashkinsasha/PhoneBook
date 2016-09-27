package main.Servic;


import java.util.Properties;

public interface EmailServise {
    void sendEmail(String from, String password, String whom, String theme, String text) throws ServiceException;
}

