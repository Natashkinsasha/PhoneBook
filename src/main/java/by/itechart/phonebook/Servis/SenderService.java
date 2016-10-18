package by.itechart.phonebook.Servis;


public interface SenderService {
    void send(String subject, String text, String fromEmail, String toEmail);
}
