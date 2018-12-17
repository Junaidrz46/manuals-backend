package se.agilecourse.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import se.agilecourse.model.Product;
import se.agilecourse.model.UserlikedProducts;

import java.util.List;

public interface UserLikedProudctsRepository extends MongoRepository<UserlikedProducts, String>{

    List<UserlikedProducts> findByUserId(String userId);
}
