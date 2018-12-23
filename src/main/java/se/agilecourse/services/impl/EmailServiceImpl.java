package se.agilecourse.services.impl;

import com.sendgrid.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.agilecourse.config.FileStorageProperties;
import se.agilecourse.services.EmailService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {

    Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    private final Email FROM = new Email("jr223ac@student.lnu.se");

    @Autowired
    private FileStorageProperties fileStProp;


    @Override
    public String sendEmail(List<String> emailAddresses, String subject, String body) {

        Mail mail = new Mail(FROM, subject, FROM, new Content("text/plain", body));
        Personalization personalization = new Personalization();
        emailAddresses.forEach(consumer -> personalization.addBcc(new Email(consumer)));
        personalization.addTo(FROM);
        mail.addPersonalization(personalization);
        //SendGrid sendGrid = new SendGrid(fileStProp.getEmailApikey());
        SendGrid sendGrid = new SendGrid("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");
        Request request = new Request();
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        try {
            request.setBody(mail.build());
            Response response = sendGrid.api(request);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        return "{\"message\": \"Sent successfully\"}";
    }


    public static void main(String[] args) {
        EmailServiceImpl impl = new EmailServiceImpl();
        List<String> emailAddresses = new ArrayList<String>();
        emailAddresses.add("junaidrz@gmail.com");
        emailAddresses.add("junaidraz@gmail.com");


        System.out.println(impl.sendEmail(emailAddresses,"My Subject","Test Content"));
    }
}
