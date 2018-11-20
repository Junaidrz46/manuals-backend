package se.agilecourse.services;

import se.agilecourse.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryServices {

    public Optional<Category> findbyId(String Id);
    public List<Category> findAllCategories();
    public Category saveCategory(Category category);
}
