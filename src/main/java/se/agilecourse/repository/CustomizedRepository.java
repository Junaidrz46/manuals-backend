package se.agilecourse.repository;

import se.agilecourse.model.AverageRatedMaterial;
import se.agilecourse.model.Material;
import se.agilecourse.model.Product;

import java.util.List;

public interface CustomizedRepository {
    String getAverageRateForMaterial(String materialId);
    List<Product> findProductsByCategoryId(String categoryId);
    List<Material> findMaterialsByProductId(String productId);
    List<Product> getMostRecentlyAddedProducts();

    // List<String> getCounterWithProductsByBrand(String CompanyId);
}
