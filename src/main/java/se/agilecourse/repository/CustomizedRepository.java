package se.agilecourse.repository;

import se.agilecourse.model.Company;
import se.agilecourse.model.Material;
import se.agilecourse.model.Product;

import java.util.List;

public interface CustomizedRepository {
    List<Product> findProductsByCategoryid(String categoryid);
    List<Material> findMaterialsByProductId(String productId);

}
