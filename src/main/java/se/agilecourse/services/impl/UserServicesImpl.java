package se.agilecourse.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.agilecourse.exceptions.CompanyIdNotFoundException;
import se.agilecourse.model.LoginModel;
import se.agilecourse.model.Product;
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
        if(user.getCompanyId() == null || user.getCompanyId().equalsIgnoreCase("")){
            throw new CompanyIdNotFoundException("Company Id not found while creating Admin");
        }
        user.setRole("companyAdmin");
        return userRepository.save(user);
    }

    @Override
    public User saveCompanyRepresentative(User user) {
        if(user.getCompanyId() == null || user.getCompanyId().equalsIgnoreCase("")){
            throw new CompanyIdNotFoundException("Company Id not found while creating Representative");
        }
        user.setRole("companyRepresentative");
        return userRepository.save(user);
    }

    @Override
    public User saveConsumer(User user) {
        user.setRole("customer");
        return userRepository.save(user);
    }

    @Override
    public User saveProductsByUserId(String productId, String userId) {
        Optional<User> user = userRepository.findById(userId);
        List<String> productslList = user.get().getLikedProductsId();
        if(productslList == null){
            productslList = new ArrayList<String>();
        }
        productslList.add(productId);
        user.get().getLikedProductsId();
        userRepository.save(user.get());
        return user.get();

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




}
