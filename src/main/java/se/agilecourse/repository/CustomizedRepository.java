package se.agilecourse.repository;

import se.agilecourse.model.Material;
import se.agilecourse.model.Product;

import java.util.List;

public interface CustomizedRepository {
    public List<Product> findProductsByCategoryid(String categoryid);
    public List<Material> findMaterialsByProductId(String productId);
}
