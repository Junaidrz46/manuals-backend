package se.agilecourse.services;

import org.springframework.stereotype.Service;
import se.agilecourse.exceptions.ConsumerNotFound;
import se.agilecourse.exceptions.LikedProductNotFound;
import se.agilecourse.model.Product;
import se.agilecourse.model.User;
import se.agilecourse.model.UserlikedProducts;

import java.util.List;

@Service
public interface UserLikedProudctsService {

    List<UserlikedProducts> findProductsByUserId(String userId);
    User saveLikedProductByUserId(String productId, String userId) throws ConsumerNotFound, LikedProductNotFound;
    UserlikedProducts unlikeProductByUser(String productId,String userId);
    List<String> findUsersEmailsSubscribedAndLikedCompany(String companyId);
}
