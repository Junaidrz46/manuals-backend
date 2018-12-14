package se.agilecourse.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "userLikedProducts")
public class UserlikedProducts {
    String id;
    String userId;
    String productId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
