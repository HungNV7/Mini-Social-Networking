/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.utilities;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.log4j.Logger;

/**
 *
 * @author ASUS
 */
public class SendingEmail {
    private String userEmail;
    private int code;
    
    private final Logger logger = Logger.getLogger(SendingEmail.class.getName());

    public SendingEmail(String userEmail, int code) {
        this.userEmail = userEmail;
        this.code = code;
    }

    public void sendMail() {
        final String email = "boy.onechamp.sylas07@gmail.com";
        final String password = "08112000HA";

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(email, password);
            }
        });
        
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));
            message.setSubject("VERIFICATION CODE");
            message.setText("Thank for signing up for my website!\n Your verification code: "+ code);
            Transport.send(message);
        } catch (Exception e) {
            logger.error("Error in SendingEmail: " + e);
        }
    }
}
