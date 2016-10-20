package by.itechart.phonebook.Servis;


import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SenderServiceImpl implements SenderService{
    private static Properties properties;
    private static ResourceBundle resource;

    static {
        properties = new Properties();
        resource = ResourceBundle.getBundle("email");
        properties.put("mail.smtp.host", resource.getString("mail.smtp.host"));
        properties.put("mail.smtp.port", resource.getString("mail.smtp.port"));
        properties.put("mail.smtp.auth", resource.getString("mail.smtp.auth"));
        properties.put("mail.smtp.ssl.trust", resource.getString("mail.smtp.ssl.trust"));
        properties.put("mail.smtp.starttls.enable",resource.getString("mail.smtp.starttls.enable"));
        properties.put("mail.debug",resource.getString("mail.debug"));
    }




    public void send(String subject, String text, String fromEmail, String toEmail){
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(resource.getString("sender.email"), resource.getString("sender.password"));
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(resource.getString("sender.email")));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(text);
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
