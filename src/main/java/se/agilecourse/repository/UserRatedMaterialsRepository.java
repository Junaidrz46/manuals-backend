package se.agilecourse.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import se.agilecourse.model.Product;
import se.agilecourse.model.UserRatedMaterials;

import java.util.List;
import java.util.Optional;

public interface UserRatedMaterialsRepository extends MongoRepository<UserRatedMaterials, String>{

    List<String> findByUserId(String userId);

}
