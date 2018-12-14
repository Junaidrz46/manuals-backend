package se.agilecourse.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import se.agilecourse.model.Product;

import java.util.List;
import java.util.Optional;


public interface ProductRepository extends MongoRepository<Product, String>,CustomizedRepository  {
    Optional<Product> findById(String id);

    @Query(value = "{'productNumber' : {$regex: ?0 } }",fields = "{'name' : 1,'productNumber' : 1}")
    List<Product> findByProductNumber(String productNo);

    @Query(value = "{'companyId' : ?0 }",fields = "{'name' : 1, 'companyId' : 1}")
    List<Product> findByCompanyId(String companyId);

    @Query(value = "{'name' : {$regex: ?0 }}",fields = "{'name' : 1}")
    List<Product> findByName(String productName);

    //@Query(value = "{}",fields = "{'name' : 1}")
    //List<Product> findAll();

    @Query(value = "{$or :[{'productNumber' : {$regex: ?0 }},{'companyId' : ?0  },{'name' : {$regex: ?0 }}]}"
    ,fields = "{'name' : 1,'productNumber' : 1,'companyId' : 1}")
    List<Product> findAll(String condition);

}
