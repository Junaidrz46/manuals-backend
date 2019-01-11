package se.agilecourse.services;

import java.util.List;


public interface EmailService {

    public String sendEmail(List<String> emailAddresses,String subject,String body);
    public String sendEmailtoOptInUsers(String subject,String body);
    public String sendEmail(String emailAddres,String subject,String body);
    //public String sendGmailService(List<String> emailAddresses,String subject,String body);


}
