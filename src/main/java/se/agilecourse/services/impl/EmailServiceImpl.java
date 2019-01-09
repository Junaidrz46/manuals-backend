package se.agilecourse.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sendgrid.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import se.agilecourse.config.FileStorageProperties;
import se.agilecourse.exceptions.GeneratRunTimeException;
import se.agilecourse.services.EmailService;
import org.apache.commons.validator.routines.EmailValidator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Service
public class EmailServiceImpl implements EmailService {

    Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    private FileStorageProperties fileStProp;

    /*@Autowired
    public JavaMailSender emailSender;*/



    @Override
    public String sendEmail(List<String> emailAddresses, String subject, String body) {
        Email FROM = new Email(fileStProp.getSenderAddress());
        FROM.setName("Manual Admin");

        EmailValidator emailvalidator = EmailValidator.getInstance();
        Mail mail=null;
        Personalization personalization=null;
        int countMessages =0;
        for (String emailAddress: emailAddresses) {
            if(emailvalidator.isValid(emailAddress)){
                logger.info("Email Address : "+emailAddress+" : Subject : "+subject+" : Body : "+ body );
                mail = new Mail(FROM, subject, new Email(emailAddress), new Content("text/html", body));
                SendGrid sendGrid = new SendGrid(fileStProp.getEmailApikey());
                Request request = new Request();
                request.setMethod(Method.POST);
                request.setEndpoint("mail/send");
                try {
                    request.setBody(mail.build());
                    Response response = sendGrid.api(request);
                } catch (IOException e) {
                    throw new GeneratRunTimeException(e.getMessage());
                }
                countMessages++;
            }else{
                throw new GeneratRunTimeException("Email Addres not Valid  : "+emailAddress);
            }
        }



        ObjectMapper objectMapper = new ObjectMapper();
        String jsonValue ="";
        try{
            jsonValue=objectMapper.writeValueAsString(emailAddresses);
        }catch (Exception e){
            logger.error(e.getMessage());
        }

        return "{\""+countMessages+ " Email(s)\": \"Sent successfully to these email addresses : "+ jsonValue +"\"}";
    }

    @Override
    public String sendEmail(String emailAddres, String subject, String body) {
        Email FROM = new Email(fileStProp.getSenderAddress());
        EmailValidator emailvalidator = EmailValidator.getInstance();
        Mail mail=null;
        if(emailvalidator.isValid(emailAddres)){
            logger.info("Email Address : "+emailAddres+" : Subject : "+subject+" : Body : "+ body );
             //mail = new Mail(FROM, subject, new Email(emailAddres), new Content("text/plain", body));
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(emailAddres);
            message.setSubject(subject);
            message.setText(body);
//            emailSender.send(message);
        }else{
            throw new GeneratRunTimeException("Email Addres not Valid  : "+emailAddres);
        }

        SendGrid sendGrid = new SendGrid(fileStProp.getEmailApikey());
        Request request = new Request();
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        try {
            request.setBody(mail.build());
            //Response response = sendGrid.api(request);
        } catch (IOException e) {
            throw new GeneratRunTimeException(e.getMessage());
        }

        return "{\" Email send to "+emailAddres+"\" : \"Sent successfully\"}";
    }

   /* @Override
    public String sendGmailService(List<String> emailAddresses, String subject, String body) {
        Email FROM = new Email(fileStProp.getSenderAddress());

        EmailValidator emailvalidator = EmailValidator.getInstance();
        Mail mail=null;
        Personalization personalization=null;
        int countMessages =0;
        for (String emailAddress: emailAddresses) {
            if(emailvalidator.isValid(emailAddress)){
                logger.info("Email Address : "+emailAddress+" : Subject : "+subject+" : Body : "+ body );
                mail = new Mail(FROM, subject, new Email(emailAddress), new Content("text/plain", body));
                countMessages++;
            }else{
                throw new GeneratRunTimeException("Email Addres not Valid  : "+emailAddress);
            }
        }

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonValue ="";
        try{
            jsonValue=objectMapper.writeValueAsString(emailAddresses);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return "{\""+countMessages+ " Email(s)\": \"Sent successfully to these email addresses : "+ jsonValue +"\"}";
    }*/

}
