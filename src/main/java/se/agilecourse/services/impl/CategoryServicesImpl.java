package se.agilecourse.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.agilecourse.model.Category;
import se.agilecourse.repository.CategoryRepository;
import se.agilecourse.services.CategoryServices;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServicesImpl implements CategoryServices {

    @Autowired
    CategoryRepository repository;

    @Override
    public Optional<Category> findById(String Id) {
        return repository.findById(Id);
    }

    @Override
    public List<Category> findAllCategories() {
        return repository.findAll();
    }

    @Override
    public Category saveCategory(Category category) {
        return repository.save(category);
    }

}
