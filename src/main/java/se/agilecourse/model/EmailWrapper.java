package se.agilecourse.model;

import java.util.List;

public class EmailWrapper {

    List<String> recipents;
    String Subject;
    String EmailBody;

    public List<String> getRecipents() {
        return recipents;
    }

    public void setRecipents(List<String> recipents) {
        this.recipents = recipents;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getEmailBody() {
        return EmailBody;
    }

    public void setEmailBody(String emailBody) {
        EmailBody = emailBody;
    }
}
