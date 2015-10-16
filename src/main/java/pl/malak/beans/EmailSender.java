package pl.malak.beans;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.mail.BodyPart;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * @author Michał Szpruta - szprutamich@gmail.com
 */
@Service
public class EmailSender {

    private static final String DEFAULT_ENCODING = "UTF-8";

    private static final String DEFAULT_MIME = "text/html; charset=UTF-8";

    private String emailFrom;

    @Resource
    private JavaMailSender mailSender;

    @PostConstruct
    private void setEmailFrom() {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("malak.properties");
        Properties properties = new Properties();
        try {
            properties.load(is);
            emailFrom = properties.getProperty("mail.sender.from");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean send(String subject, String body, String email, BodyPart... attachments) {
        if (emailFrom != null) {
            try {
                MimeMessage msg = mailSender.createMimeMessage();
                msg.setSubject(subject, DEFAULT_ENCODING);
                msg.addRecipient(RecipientType.TO, new InternetAddress(email));
                msg.setFrom(new InternetAddress(emailFrom));
                MimeMultipart multiPart = new MimeMultipart();
                BodyPart bodyPart = new MimeBodyPart();
                bodyPart.setContent(body, DEFAULT_MIME);
                multiPart.addBodyPart(bodyPart);
                for (BodyPart att : attachments) {
                    multiPart.addBodyPart(att);
                }
                msg.setContent(multiPart);
                mailSender.send(msg);
            } catch (Exception ex) {
                return false;
            }
        }
        return true;
    }
}