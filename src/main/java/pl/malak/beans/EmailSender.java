package pl.malak.beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.mail.BodyPart;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

@Service
public class EmailSender extends JavaMailSenderImpl {

    private static final String DEFAULT_ENCODING = "UTF-8";

    private static final String DEFAULT_MIME = "text/html; charset=UTF-8";

    @Value("${mail.sender.from}")
    private String emailFrom;

    @Value("${mail.sender.username}")
    private String userName;

    @Value("${mail.sender.pass}")
    private String pass;

    @Value("${mail.sender.host}")
    private String host;

    @Value("${mail.sender.port}")
    private Integer port;

    @Value("${mail.sender.protocol}")
    private String protocol;

    @Value("${mail.sender.auth}")
    private String auth;

    @Value("${mail.sender.starttls}")
    private Boolean starttls;

    @PostConstruct
    private void setProperties() {
        setUsername(userName);
        setPassword(pass);
        setHost(host);
        setPort(port);
        setProtocol(protocol);
        getJavaMailProperties().put("mail.debug", false);
        getJavaMailProperties().put("mail.smtp.starttls.enable", starttls);
        getJavaMailProperties().put("mail.smtp.auth", auth);
    }

    public boolean send(String subject, String body, String email, BodyPart... attachments) {
        if (emailFrom != null) {
            try {
                MimeMessage msg = createMimeMessage();
                msg.setSubject(subject, DEFAULT_ENCODING);
                // TODO change after tests
//                msg.addRecipient(RecipientType.TO, new InternetAddress(email));
                msg.addRecipient(RecipientType.TO, new InternetAddress(emailFrom));
                msg.setFrom(new InternetAddress(emailFrom));
                MimeMultipart multiPart = new MimeMultipart();
                BodyPart bodyPart = new MimeBodyPart();
                bodyPart.setContent(body, DEFAULT_MIME);
                multiPart.addBodyPart(bodyPart);
                for (BodyPart att : attachments) {
                    multiPart.addBodyPart(att);
                }
                msg.setContent(multiPart);
                send(msg);
            } catch (Exception ex) {
                return false;
            }
        }
        return true;
    }
}
