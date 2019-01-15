package se.agilecourse.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import se.agilecourse.model.User;


public interface UserRepository extends MongoRepository<User, String>{

    public User findByUsername(String Username);
    public User findByEmailaddress(String emailaddress);
    public User findByUsernameAndPassword(String username,String password);
    public List<User> findByRole(String role);
    public List<User> findByReceiveMessage(String flag);
    Optional<User> findById(String id);
    List<User> findUsersByCompanyId(String companyId);

}
