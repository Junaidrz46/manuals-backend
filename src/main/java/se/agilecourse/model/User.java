package se.agilecourse.model;

import org.springframework.data.mongodb.core.mapping.Document;

/*import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;*/

@Document(collection="users")
public class User {

    public User(){
    }

    public User(String firstname, String lastname, String username, String password, String emailaddress, String companyname) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.emailaddress = emailaddress;
        this.companyname = companyname;
    }

    public User(String firstname, String lastname, String username, String password, String emailaddress, String companyname, String role) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.emailaddress = emailaddress;
        this.companyname = companyname;
        this.role = role;
    }

    private String id;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String emailaddress;
    private String companyname;
    private String role;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public String toString() {

        if(id != null && !id.equalsIgnoreCase("")){
            return String.format(
                    "User[id='%s', firstname='%s', lastname='%s', username='%s', password='%s , emailaddress='%s' , role='%s']",
                    id, firstname, lastname, username, password, emailaddress, role);
        }else {
            return String.format(
                    "User[ firstname='%s', lastname='%s', username='%s', password='%s , emailaddress='%s' , role='%s']",
                    firstname, lastname, username, password, emailaddress, role);
        }
    }
}
