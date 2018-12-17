package se.agilecourse.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.agilecourse.model.LoginModel;
import se.agilecourse.model.User;
import se.agilecourse.model.UserRatedMaterials;
import se.agilecourse.model.UserlikedProducts;
import se.agilecourse.services.UserLikedProudctsService;
import se.agilecourse.services.UserRatedMaterialsService;
import se.agilecourse.services.UserServices;
import se.agilecourse.repository.UserRepository;

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
    public List<String> findLikedProductsByUserId(@RequestParam("userId") String userId) {
        return userLikedProudctsService.findProductsByUserId(userId);
    }

    @RequestMapping(value = "/findRatedMaterialByUserId", params = "userId", method = RequestMethod.GET)
    public List<String> findRatedMaterialByUserId(@RequestParam("userId") String userId) {
        return userRatedMaterialsService.findMaterialsByUserId(userId);
    }

    @PostMapping("/saveLikedProductsByUserId")
    User saveProductsListByUserId(@RequestBody UserlikedProducts userlikedProducts) {
        return userLikedProudctsService.saveLikedProductByUserId(userlikedProducts.getProductId(),userlikedProducts.getUserId());
    }

    @PostMapping("/saveRatedMaterialByUserId")
    User saveRatedMaterialByUserId(@RequestBody UserRatedMaterials userRatedMaterials) {
        return userRatedMaterialsService.saveRatedMaterialByUserId(userRatedMaterials.getMaterialId(),userRatedMaterials.getUserId());
    }

    @RequestMapping(value = "/findUsersById" ,method = RequestMethod.GET)
    public User findUserById(@RequestParam("userId") String userId){
        return userServices.getUserById(userId).get();
    }

    // @RequestMapping(value="/findMaterialById",method = RequestMethod.GET)
    // public Material findMaterialById(@RequestParam("materialId") String materialId){
    //    return categoryServices.getMaterialById(materialId).get();
    // }


}
