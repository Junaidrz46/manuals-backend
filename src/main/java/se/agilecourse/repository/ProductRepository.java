package se.agilecourse.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import se.agilecourse.model.Product;

import java.util.List;
import java.util.Optional;


public interface ProductRepository extends MongoRepository<Product, String>,CustomizedRepository  {
    Optional<Product> findById(String id);
    List<Product> findByProductNumberIsLike(String productNo);
    List<Product> findByCompanyId(String companyId);
    List<Product> findByNameIsLike(String productName);

}
