package se.agilecourse.services;

import org.springframework.stereotype.Service;
import se.agilecourse.model.LoginModel;
import se.agilecourse.model.User;
import java.util.List;
import java.util.Optional;

@Service
public interface UserServices {

    public User findByUserName(String username);
    public User findByEmailAddress(String emailAddress);
    public List<User> findByRole(String role);
    public List<User> findAllUsers();
    public User saveAdminUser(User user);
    public User saveCompanyRepresentative(User user);
    public LoginModel loginUser(String username,String password);
    public User saveConsumer(User user);

    Optional<User> getUserById(String id);

    User saveAuthorizationByUserId(String userId, String receiveMessage) throws Exception;

}
