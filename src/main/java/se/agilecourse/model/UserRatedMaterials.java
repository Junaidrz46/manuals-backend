package se.agilecourse.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="userRateMaterials")
public class UserRatedMaterials {

    String Id;
    String userId;
    String materialId;
    Integer materiaRate;
    public Integer getMateriaRate() {
        return materiaRate;
    }

    public void setMateriaRate(Integer materiaRate) {
        this.materiaRate = materiaRate;
    }



    public UserRatedMaterials(){

    }

    public UserRatedMaterials(String userId, String materialId,Integer materiaRate) {
        this.userId = userId;
        this.materialId = materialId;
        this.materiaRate=materiaRate;
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
