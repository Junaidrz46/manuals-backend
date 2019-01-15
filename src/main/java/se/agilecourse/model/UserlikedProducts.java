package se.agilecourse.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="userlikedproducts")
public class UserlikedProducts {

    String id;
    String userId;
    String productId;
    String companyId;

    public UserlikedProducts(){
    }

    public UserlikedProducts(String userid, String productId,String companyId) {
        this.userId = userid;
        this.productId = productId;
        this.companyId = companyId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserid(String userId) {
        this.userId = userId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
}
