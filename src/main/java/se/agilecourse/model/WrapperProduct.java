package se.agilecourse.model;

import org.springframework.data.mongodb.core.mapping.DBRef;
import se.agilecourse.annotation.CascadeSave;

import java.util.List;

public class WrapperProduct {

    String companyId;
    String categoryId;
    Product product;
   //    String brandId;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

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

    // public String getBrandId() {
    //     return brandId;
    // }

    // public void setBrandId(String brandId) {
    //     this.brandId = brandId;
    // }
}
