package se.agilecourse.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.agilecourse.model.LoginModel;
import se.agilecourse.model.User;
import se.agilecourse.repository.UserRepository;
import se.agilecourse.services.UserServices;
import se.agilecourse.util.StringConstants;


@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    UserRepository userRepository;

    @Autowired
    StringConstants stringConstants;

    @Override
    public User findByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmailAddress(String emailAddress) {
        return userRepository.findByEmailaddress(emailAddress);
    }

    @Override
    public List<User> findByRole(String role) {
        return userRepository.findByRole(role);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }


    @Override
    public User saveAdminUser(User user) {
        user.setRole("companyAdmin");
        return userRepository.save(user);
    }

    @Override
    public User saveCompanyRepresentative(User user) {
        user.setRole("companyRepresentative");
        return userRepository.save(user);
    }

    @Override
    public LoginModel loginUser(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username,password);
        LoginModel loginModel = new LoginModel();

        if(user != null){
            loginModel.setUser(user);
            loginModel.setLoginstatus(stringConstants.LOGIN_SUCCESS);
            loginModel.setMessage("User Login");
        }else {
            loginModel.setLoginstatus(stringConstants.LOGIN_FAIL);
            loginModel.setMessage("Username or password didn't match");

        }

        return loginModel ;
    }

    @Override
    public User saveConsumer(User user) {
        return userRepository.save(user);
    }


}
