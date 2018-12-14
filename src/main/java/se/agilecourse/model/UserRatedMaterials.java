package se.agilecourse.model;

public class UserRatedMaterials {

    String Id;
    String userId;
    String materialId;

    public UserRatedMaterials(){

    }

    public UserRatedMaterials(String userId, String materialId) {
        this.userId = userId;
        this.materialId = materialId;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }
}
