package se.agilecourse.services;

import se.agilecourse.model.Category;
import se.agilecourse.model.Product;

import java.util.List;
import java.util.Optional;

public interface CategoryServices {

    Optional<Category> findById(String id);
    List<Category> findAllCategories();
    Category saveCategory(Category category);
    List<Product> getProductsByCid(String cid);

}
