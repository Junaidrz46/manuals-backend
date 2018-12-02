package se.agilecourse.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import se.agilecourse.model.Company;

import java.util.Optional;

public interface CompanyRepository extends MongoRepository<Company, String>, CustomizedRepository {
    Optional<Company> findById(String id);
}
