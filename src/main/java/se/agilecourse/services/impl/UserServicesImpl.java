package se.agilecourse.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.agilecourse.exceptions.CompanyIdNotFoundException;
import se.agilecourse.exceptions.GeneratRunTimeException;
import se.agilecourse.model.LoginModel;
import se.agilecourse.model.Product;
import se.agilecourse.model.User;
import se.agilecourse.repository.UserRepository;
import se.agilecourse.services.UserServices;
import se.agilecourse.util.StringConstants;


@Service
public class UserServicesImpl implements UserServices {

    private final Logger logger = LoggerFactory.getLogger(UserServicesImpl.class);

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
    public User saveAdminUser(User user) {//
        if(user.getCompanyId() == null || user.getCompanyId().equalsIgnoreCase("")){
            throw new CompanyIdNotFoundException("Company Id not found while creating Admin");
        }
        user.setRole(stringConstants.ROLE_ADMIN);
        return userRepository.save(user);
    }

    @Override
    public User saveCompanyRepresentative(User user) {
        if(user.getCompanyId() == null || user.getCompanyId().equalsIgnoreCase("")){
            throw new CompanyIdNotFoundException("Company Id not found while creating Representative");
        }
        user.setRole(stringConstants.ROLE_REPRESANTATIVE);
        return userRepository.save(user);
    }

    @Override
    public User saveServiceProvider(User user) {
        if(user.getCompanyId()==null || user.getCompanyId().equals(""))
            throw new GeneratRunTimeException("the company Id can not be null when you create a service provider");
        user.setRole(stringConstants.ROLE_SERVICE_PROVIDER);
        return userRepository.save(user);
    }

    @Override
    public User saveConsumer(User user) {
        user.setRole(stringConstants.ROLE_CONSUMER);
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
    public Optional<User> getUserById(String userId) {
        return userRepository.findById(userId);
    }

    @Override
    public User saveAuthorizationByUserId(String userId, String receiveMessage) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            if (receiveMessage.equals("0")) {
                user.get().setReceiveMessage("0");
            }
            else if (receiveMessage.equals("1")) {
                user.get().setReceiveMessage("1");
            }
            userRepository.save(user.get());
            return user.get();
        }else{
            throw new GeneratRunTimeException("UserID IS NOT VALID ");
        }

    }

    @Override
    public Optional<User> updateAuthorizedStatusForSP(String userId, String status) {
        Optional<User> user = userRepository.findById(userId);
        if(!user.isPresent())
            throw new GeneratRunTimeException("user id is invalid!");
        String userRole=user.get().getRole();
        if(!stringConstants.ROLE_SERVICE_PROVIDER.equals(userRole))
            throw new GeneratRunTimeException("the user is not a service provider!");
        user.get().setAuthorizedSP(status);//0---unAuthorized, 1 Authorized
        userRepository.save(user.get());
        return user;
    }

    @Override
    public List<String> findEmailIdOfSubscribedUsers() {
        List<User> userList =  userRepository.findByReceiveMessage("1");
        List<String> emailAddresses = new ArrayList<String>();
        if(userList != null && userList.size() > 0){
            for (User user: userList) {
                if(user.getEmailaddress() != null && !user.getEmailaddress().equalsIgnoreCase("")) {
                    emailAddresses.add(user.getEmailaddress());
                }else{
                    logger.info("Null or empty email address values of user : "+user.getId());
                }
            }
        }

        logger.info("Returning List Size : "+emailAddresses.size());
        return emailAddresses;
    }
    @Override
    public List<User> findSPByCompanyId(String role,String companyId) {
        if(!stringConstants.ROLE_SERVICE_PROVIDER.equals(role))
            throw new GeneratRunTimeException("The role is not a service provider");
        List<User> users=userRepository.findUsersByRoleAndCompanyId(role,companyId);
        return users;
    }

    @Override
    public User deleteUserById(String userId) {

        Optional<User> user = userRepository.findById(userId);

        if(user.isPresent()) {
            userRepository.delete(user.get());
            return user.get();
        }else{
            throw new GeneratRunTimeException("User With Given Id didn't exists.");
        }

    }


}
