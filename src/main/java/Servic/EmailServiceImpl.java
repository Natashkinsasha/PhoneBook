package main.java.Servic;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailServiceImpl implements EmailServise {
    static final String ENCODING = "UTF-8";
    static final String smtpPort = "465";
    static final String smtpHost = "smtp.gmail.com";

    @Override
    public void sendEmail(final String login, final String password, String whom, String subject, String content) throws ServiceException {
        Properties props = System.getProperties();
        props.put("mail.smtp.port", smtpPort);
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.auth", "true");
        props.put("mail.mime.charset", ENCODING);
        Session session = Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(login, password);
            }
        });

        Message msg = new MimeMessage(session);

        try {
            msg.setFrom(new InternetAddress(login));
            msg.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(whom, false));
            msg.setSubject(subject);
            msg.setText(content);
            Transport.send(msg);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
