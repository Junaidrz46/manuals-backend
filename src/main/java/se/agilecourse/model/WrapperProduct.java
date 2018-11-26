package se.agilecourse.model;

import org.springframework.data.mongodb.core.mapping.DBRef;
import se.agilecourse.annotation.CascadeSave;

import java.util.List;

public class WrapperProduct {

   String categoryId;
   Product product;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}