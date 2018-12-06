package se.agilecourse.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import se.agilecourse.model.Company;

import java.util.List;

public interface CompanyRepository extends MongoRepository<Company, String>, CustomizedRepository {
    List<Company> findByName(String companyName);
}
