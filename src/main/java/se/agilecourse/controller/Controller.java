package se.agilecourse.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.agilecourse.model.User;
import se.agilecourse.repository.UserRepository;
import se.agilecourse.services.UserServices;

import java.util.List;

@RestController()
@RequestMapping("/rest/users")
public class Controller {

    private static final Logger logger = LoggerFactory.getLogger(Controller.class);

    @Autowired
    UserServices userServices;

    @RequestMapping("/hello")
    public String helloGradle() {
        return "Hello world!";
    }


    @GetMapping("/findAllUsers")
    public List<User> getAll() {
        System.out.println("Get users from all : "+userServices.findAllUsers().size());
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
    User newUser(@RequestBody User user) {
        return userServices.saveAdminUser(user);
    }
}
