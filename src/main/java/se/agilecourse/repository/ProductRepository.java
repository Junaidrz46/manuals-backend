package se.agilecourse.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import se.agilecourse.model.Product;

import java.util.List;
import java.util.Optional;


public interface ProductRepository extends MongoRepository<Product, String>,CustomizedRepository  {
    Optional<Product> findById(String id);
    @Query(value = "{}",fields = "{'name' : 1,'productNumber' : 1}")
    List<Product> findByProductNumberIsLike(String productNo);
    @Query(value = "{}",fields = "{'name' : 1, 'companyId' : 1}")
    List<Product> findByCompanyId(String companyId);
    @Query(value = "{}",fields = "{'name' : 1}")
    List<Product> findByNameIsLike(String productName);
    @Query(value = "{}",fields = "{'name' : 1}")
    List<Product> findAll();

}
