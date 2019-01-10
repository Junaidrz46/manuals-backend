package se.agilecourse.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import se.agilecourse.annotation.CascadeSave;
import java.util.Date;
import java.util.List;

@Document(collection="products")
public class Product implements Persistable<String> {
    @Id
    private String id;
    private String productNumber;
    private String name;
    private String description;
    private String companyId;
    private String categoryId;
    private String profileImage;
    private boolean persisted;
    private Integer likedCounter;
    @CreatedDate
    private Date createDate;



    public Product(){}
    public Product(String productNumber, String name, String description, String companyId, String categoryId, String profileImage) {
        this.productNumber = productNumber;
        this.name = name;
        this.description = description;
        this.companyId = companyId;
        this.categoryId = categoryId;
        this.profileImage = profileImage;
    }

    @DBRef
    @CascadeSave
    private List<Material> materials;

    public String getCompanyId() {
        return companyId;
    }
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return the likedCounter
     */
    public Integer getLikedCounter() {
        return likedCounter;
    }

    /**
     * @param likedCounter the likedCounter to set
     */
    public void setLikedCounter(Integer likedCounter) {
        this.likedCounter = likedCounter;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
    @Override
    public String getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return !persisted;
    }


    public void setPersisted(boolean persisted) {
        this.persisted = persisted;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Material> getMaterials() {
        return materials;
    }

    public void setMaterials(List<Material> materials) {
        this.materials = materials;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}
