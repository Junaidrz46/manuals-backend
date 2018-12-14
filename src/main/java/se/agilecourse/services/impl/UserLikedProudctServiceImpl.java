package se.agilecourse.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.agilecourse.model.Product;
import se.agilecourse.model.User;
import se.agilecourse.model.UserlikedProducts;
import se.agilecourse.repository.UserLikedProudctsRepository;
import se.agilecourse.repository.UserRepository;
import se.agilecourse.services.UserLikedProudctsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserLikedProudctServiceImpl implements UserLikedProudctsService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserLikedProudctsRepository userLikedProudctsRepository;

    @Override
    public List<String> findProductsByUserId(String userId) {
        return userLikedProudctsRepository.findByUserId(userId);
    }

    @Override
    public User saveLikedProductByUserId(String productId, String userId) {
        Optional<User> user = userRepository.findById(userId);
        List<String> productslList = userLikedProudctsRepository.findByUserId(userId);
        if(productslList == null){
            productslList = new ArrayList<>();
        }
        productslList.add(productId);
        user.get().setLikedProducts(productslList);
        userLikedProudctsRepository.save(new UserlikedProducts(userId,productId));
        return user.get();

    }
}
