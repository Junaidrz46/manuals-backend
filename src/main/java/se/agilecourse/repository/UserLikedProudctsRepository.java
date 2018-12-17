package se.agilecourse.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import se.agilecourse.model.Product;
import se.agilecourse.model.UserlikedProducts;

import java.util.List;
import java.util.Optional;

public interface UserLikedProudctsRepository extends MongoRepository<UserlikedProducts, String>{

    List<String> findByUserId(String userId);
    Optional<UserlikedProducts> findByUserIdAndAndProductId(String userId,String productId);
}
