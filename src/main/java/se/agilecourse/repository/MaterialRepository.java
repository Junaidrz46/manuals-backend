package se.agilecourse.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import se.agilecourse.model.Material;

import java.util.Optional;

public interface MaterialRepository extends MongoRepository<Material, String>, CustomizedRepository {

    Optional<Material> findById(String Id);


}
