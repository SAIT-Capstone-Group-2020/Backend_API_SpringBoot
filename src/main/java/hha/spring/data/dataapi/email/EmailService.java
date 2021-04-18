package hha.spring.data.dataapi.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;

import javax.mail.internet.MimeMessage;

/**
 * This class is a component which has email service.
 * This can be auto-detected and registered as Java Bean in configuration stage of Spring boot.
 *
 * @author HHA E-Commerce
 * @version 1.0, April 20, 2021
 */
@Component
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    /**
     * Send html message.
     *
     * @param to       the to
     * @param subject  the subject
     * @param htmlBody the html body
     * @throws MessagingException the messaging exception
     */
    public void sendHtmlMessage(String to, String subject, String htmlBody) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlBody, true);
        emailSender.send(message);
    }
}
