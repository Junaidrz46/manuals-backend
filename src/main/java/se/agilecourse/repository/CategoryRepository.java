package se.agilecourse.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import se.agilecourse.model.Category;
import se.agilecourse.model.User;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends MongoRepository<Category, String> , CustomizedRepository{
    Optional<Category> findById(String id);
    @Query(value = "{}",fields = "{'name' : 1}")
    List<Category> findAll();



}
