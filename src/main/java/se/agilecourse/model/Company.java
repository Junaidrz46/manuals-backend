package se.agilecourse.model;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import se.agilecourse.annotation.CascadeSave;

import java.util.List;

@Document(collection="companies")
public class Company {

    String id;
    String name;
    String description;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
