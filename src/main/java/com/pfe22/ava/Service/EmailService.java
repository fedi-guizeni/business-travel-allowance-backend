package com.pfe22.ava.Service;

import com.sun.mail.smtp.SMTPTransport;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

import static com.pfe22.ava.constant.EmailConstant.*;

@Service
public class EmailService {
    public void SendNewPasswordEmail(String firstname , String password, String email) throws MessagingException {
        Message message = createEmail(firstname , password , email);
        SMTPTransport smtpTransport= (SMTPTransport) getEmailSession().getTransport(SIMPLE_MAIL_TRANSFER_PROTOCOL);
        smtpTransport.connect(GMAIL_SMTP_SERVER, USERNAME ,PASSWORD);
        smtpTransport.sendMessage(message , message.getAllRecipients());
        smtpTransport.close();
    }

    public void SendActivatedEmail(String firstname , String lastname ,String ref,  String email) throws MessagingException {
        Message message = createEmailforAva(firstname , lastname, ref , email);
        SMTPTransport smtpTransport= (SMTPTransport) getEmailSession().getTransport(SIMPLE_MAIL_TRANSFER_PROTOCOL);
        smtpTransport.connect(GMAIL_SMTP_SERVER, USERNAME ,PASSWORD);
        smtpTransport.sendMessage(message , message.getAllRecipients());
        smtpTransport.close();
    }

    private Message createEmail(String firstname , String password, String email) throws MessagingException {
        Message message = new MimeMessage(getEmailSession());
        message.setFrom(new InternetAddress(FROM_EMAIL));
        message.setRecipients(Message.RecipientType.TO , InternetAddress.parse(email , false));
        message.setRecipients(Message.RecipientType.CC,InternetAddress.parse(CC_EMAIL,false));
        message.setSubject(EMAIL_SUBJECT);
        message.setText("salut  " +firstname + ", \n \n le nouveau mot de passe de votre compte est :"+ password + "\n \n ATB ");
        message.setSentDate(new Date());
        message.saveChanges();
        return message;
    }
    private Message createEmailforAva(String firstname , String lastname,  String ref, String email) throws MessagingException {
        Message message = new MimeMessage(getEmailSession());
        message.setFrom(new InternetAddress(FROM_EMAIL));
        message.setRecipients(Message.RecipientType.TO , InternetAddress.parse(email , false));
        message.setRecipients(Message.RecipientType.CC,InternetAddress.parse(CC_EMAIL,false));
        message.setSubject(EMAIL_SUBJECT2);
        message.setText("salut  " +firstname +" "+ lastname +", \n \n votre dossier ava a été approuvé :"+ ref + "\n \n ATB ");
        message.setSentDate(new Date());
        message.saveChanges();
        return message;
    }


    private Session getEmailSession(){

        Properties  properties = System.getProperties();
        properties.put( SMTP_HOST , GMAIL_SMTP_SERVER );
        properties.put( SMTP_AUTH , true );
        properties.put( SMTP_PORT , DEFAULT_PORT );
        properties.put( SMTP_STARTTLS_ENABLE , true );
        properties.put( SMTP_STARTTLS_REQUIRED , true );

        return Session.getInstance(properties,null);
    }
}