package se.agilecourse.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import se.agilecourse.model.User;


public interface UserRepository extends MongoRepository<User, String>{

    public User findByUsername(String Username);
    public User findByEmailaddress(String emailaddress);
    public List<User> findByRole(String role);

}
