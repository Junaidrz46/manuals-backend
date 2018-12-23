package se.agilecourse.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.web.bind.annotation.*;
import se.agilecourse.model.*;
import se.agilecourse.services.EmailService;
import se.agilecourse.services.UserLikedProudctsService;
import se.agilecourse.services.UserRatedMaterialsService;
import se.agilecourse.services.UserServices;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:8888/", maxAge = 3600)
@RestController()
@RequestMapping("/rest/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserServices userServices;

    @Autowired
    UserRatedMaterialsService userRatedMaterialsService;

    @Autowired
    UserLikedProudctsService userLikedProudctsService;

    @Autowired
    EmailService emailService;


    @RequestMapping("/hello")
    public String helloGradle() {
        return "Hello world!";
    }


    @GetMapping("/findAllUsers")
    public List<User> getAll() {
        logger.debug("Get users from all : "+userServices.findAllUsers().size());
        return userServices.findAllUsers();
    }

    @RequestMapping(value = "/findUsersByRole", params = "role", method = RequestMethod.GET)
    public List<User> findUserByRole(@RequestParam("role") String role) {
        //System.out.println("Get users By Roll : "+userServices.findByRole(role).size());
        return userServices.findByRole(role);
    }

    @RequestMapping(value = "/findUserByEmailAddress", params = "emailAddress", method = RequestMethod.GET)
    public User findUserByEmailAddress(@RequestParam("emailAddress") String emailAddress) {
        return userServices.findByEmailAddress(emailAddress);
    }

    @RequestMapping(value = "/findUserByUserName", params = "userName", method = RequestMethod.GET)
    public User findUserByUsername(@RequestParam("userName") String username) {
        return userServices.findByUserName(username);
    }

    //@RequestMapping(value = "/saveAdminUser", params = "emailAddress", method = RequestMethod.POST)
    @PostMapping("/saveAdminUser")
    User saveCompanyAdmin(@RequestBody User user) {
        return userServices.saveAdminUser(user);
    }

    @PostMapping("/saveCompanyRepresentative")
    User saveCompanyRepresntative(@RequestBody User user) {
        return userServices.saveCompanyRepresentative(user);
    }

    @PostMapping("/login")
    LoginModel loginUser(@RequestBody User user){
        return userServices.loginUser(user.getUsername(),user.getPassword());
    }
    @PostMapping("/saveConsumer")
    User saveConsumer(@RequestBody User user) {
        return userServices.saveConsumer(user);
    }

    @RequestMapping(value = "/findLikedProductsByUserId", params = "userId", method = RequestMethod.GET)
    public List<UserlikedProducts> findLikedProductsByUserId(@RequestParam("userId") String userId) {
        return userLikedProudctsService.findProductsByUserId(userId);
    }

    @RequestMapping(value = "/findRatedMaterialByUserId", params = "userId", method = RequestMethod.GET)
    public List<UserRatedMaterials> findRatedMaterialByUserId(@RequestParam("userId") String userId) {
        return userRatedMaterialsService.findMaterialsByUserId(userId);
    }

    @PostMapping("/saveLikedProductsByUserId")
    User saveProductsListByUserId(@RequestBody UserlikedProducts userlikedProducts) {
        return userLikedProudctsService.saveLikedProductByUserId(userlikedProducts.getProductId(),userlikedProducts.getUserId());
    }

    @PostMapping("/unLikedProductsByUserId")
    UserlikedProducts deleteUserLikeProduuctByUserId(@RequestBody UserlikedProducts userlikedProduct){
        return userLikedProudctsService.unlikeProductByUser(userlikedProduct.getProductId(),userlikedProduct.getUserId());

    }

    @PostMapping("/saveRatedMaterialByUserId")
    User saveRatedMaterialByUserId(@RequestBody UserRatedMaterials userRatedMaterials) {
        return userRatedMaterialsService.saveRatedMaterialByUserId(userRatedMaterials.getMaterialId(),userRatedMaterials.getUserId(),userRatedMaterials.getMateriaRate());
    }

    @RequestMapping(value = "/findUsersById" ,method = RequestMethod.GET)
    public User findUserById(@RequestParam("userId") String userId){
        return userServices.getUserById(userId).get();
    }

    @PostMapping("/saveAuthorizationByUserId")
    User saveAuthorizationByUserId(@RequestParam("userId") String userId,
                                   @RequestParam("receiveMessage") String receiveMessage) throws Exception {
        return userServices.saveAuthorizationByUserId(userId, receiveMessage);

    }
    @RequestMapping(value = "/getAverageRateForMaterial" ,method = RequestMethod.GET)
    public String getAverageRateForMaterial(@RequestParam("materialId") String materialId){
        return userRatedMaterialsService.getAverageRateByMaterialId(materialId);
    }

    @PostMapping("/SendEmailtoManyRecipents")
    String saveRatedMaterialByUserId(@RequestBody EmailWrapper emailWrapper) {
        return emailService.sendEmail(emailWrapper.getRecipents(),emailWrapper.getSubject(),emailWrapper.getEmailBody());
    }



}
