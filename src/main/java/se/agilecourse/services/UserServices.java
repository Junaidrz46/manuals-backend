package se.agilecourse.services;

import org.springframework.stereotype.Service;
import se.agilecourse.model.LoginModel;
import se.agilecourse.model.Product;
import se.agilecourse.model.User;
import java.util.List;
import java.util.Optional;

@Service
public interface UserServices {

     User findByUserName(String username);
     User findByEmailAddress(String emailAddress);
     List<User> findByRole(String role);
     List<User> findAllUsers();
     User saveAdminUser(User user);
     User saveCompanyRepresentative(User user);
     LoginModel loginUser(String username,String password);
     User saveConsumer(User user);
     Optional<User> getUserById(String id);
     User saveAuthorizationByUserId(String userId, String receiveMessage) throws Exception;

}
