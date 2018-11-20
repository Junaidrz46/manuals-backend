package se.agilecourse.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import se.agilecourse.model.Category;
import se.agilecourse.model.User;

public interface CategoryRepository extends MongoRepository<Category, String> {
    public Category findByName(String Username);
}
