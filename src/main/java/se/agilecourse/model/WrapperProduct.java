package se.agilecourse.model;

import org.springframework.data.mongodb.core.mapping.DBRef;
import se.agilecourse.annotation.CascadeSave;

import java.util.List;

public class WrapperProduct {

   String brandId;
   Product product;

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
