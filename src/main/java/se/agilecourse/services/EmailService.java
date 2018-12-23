package se.agilecourse.services;

import java.util.List;


public interface EmailService {

    public String sendEmail(List<String> emailAddresses,String subject,String body);


}
