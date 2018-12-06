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

    public User(String username, String password, String emailaddress){
        this.username = username;
        this.password = password;
        this.emailaddress = emailaddress;

    }

    public User(String username, String password, String emailaddress,String role,String companyId){
        this.username = username;
        this.password = password;
        this.emailaddress = emailaddress;
        this.role = role;
        this.companyId = companyId;
    }


    private String id;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String emailaddress;
    private String companyId;
    private String role;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastName() {
        return lastname;
    }

    public void setLastName(String lastName) {
        this.lastname = lastName;
    }

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

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    @Override
    public String toString() {

        if(id != null && !id.equalsIgnoreCase("")){
            return String.format(
                    "User[id='%s', username='%s', password='%s , emailaddress='%s' , role='%s' , companyId='%s']",
                    id,username, password, emailaddress, role,companyId);
        }else {
            return String.format(
                    "User[ username='%s', password='%s , emailaddress='%s' , role='%s']",
                    username, password, emailaddress, role);
        }
    }
}
