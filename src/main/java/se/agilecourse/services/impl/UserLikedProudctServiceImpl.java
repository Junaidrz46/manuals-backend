package se.agilecourse.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.agilecourse.exceptions.ConsumerNotFound;
import se.agilecourse.exceptions.GeneratRunTimeException;
import se.agilecourse.exceptions.LikedProductNotFound;
import se.agilecourse.model.Product;
import se.agilecourse.model.User;
import se.agilecourse.model.UserlikedProducts;
import se.agilecourse.repository.ProductRepository;
import se.agilecourse.repository.UserLikedProudctsRepository;
import se.agilecourse.repository.UserRepository;
import se.agilecourse.services.UserLikedProudctsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserLikedProudctServiceImpl implements UserLikedProudctsService{

    Logger logger = LoggerFactory.getLogger(UserLikedProudctServiceImpl.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserLikedProudctsRepository userLikedProudctsRepository;
    @Autowired
    ProductRepository productRepository;

    @Override
    public List<UserlikedProducts> findProductsByUserId(String userId) {
        return userLikedProudctsRepository.findByUserId(userId);
    }

    @Override
    public User saveLikedProductByUserId(String productId, String userId) throws ConsumerNotFound,LikedProductNotFound{
        Optional<User> user = userRepository.findById(userId);
        Optional<UserlikedProducts> userlikedProducts;
        if(!user.isPresent()) {
            throw new ConsumerNotFound("There is no such consumer!");

        }
        String userRole= user.get().getRole();

      /*  if(!userRole.equals("customer"))
            throw new ConsumerNotFound("There is no such consumer!");*/

        Optional<Product> product= productRepository.findById(productId);
        if(!product.isPresent()) {
            throw new LikedProductNotFound("The specific product can not be found!");
        }
        if(!userRole.equalsIgnoreCase("consumer")) {
            logger.info("User Role Not appropriate to like product  :"+userRole);
            throw new ConsumerNotFound("User is not a consumer or customer !");
        }

        userlikedProducts = userLikedProudctsRepository.findByUserIdAndAndProductId(userId,productId);

        if(userlikedProducts.isPresent()){
            throw new GeneratRunTimeException("User Already Liked this Product");
        }

        List<String> productslList = user.get().getLikedProducts();
        if(productslList == null){
            productslList = new ArrayList<>();
        }
        productslList.add(productId);
        user.get().setLikedProducts(productslList);
        userRepository.save(user.get());

        userLikedProudctsRepository.save(new UserlikedProducts(userId,productId));
        return user.get();

    }

    @Override
    public UserlikedProducts unlikeProductByUser(String productId, String userId) {
        Optional<UserlikedProducts> userlikedProducts;

        userlikedProducts = userLikedProudctsRepository.findByUserIdAndAndProductId(userId,productId);
        if(userlikedProducts.isPresent()){
            userLikedProudctsRepository.delete(userlikedProducts.get());

            return userlikedProducts.get();
        }
        throw new GeneratRunTimeException("User Didn't liked this Product");

    }


}
