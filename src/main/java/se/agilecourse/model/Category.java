package se.agilecourse.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection="categories")
public class Category {

    private String id;
    private String name;
    private List<Product> products;
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
