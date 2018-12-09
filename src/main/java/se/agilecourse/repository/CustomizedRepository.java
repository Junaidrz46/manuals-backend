package se.agilecourse.repository;

import org.springframework.data.mongodb.repository.Query;
import se.agilecourse.model.Material;
import se.agilecourse.model.Product;

import java.util.List;

public interface CustomizedRepository {
    
    List<Product> findProductsByCategoryId(String categoryId);
    List<Material> findMaterialsByProductId(String productId);

}
