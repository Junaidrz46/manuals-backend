package se.agilecourse.services;

import org.springframework.stereotype.Service;
import se.agilecourse.model.Material;
import se.agilecourse.model.User;
import se.agilecourse.model.UserRatedMaterials;

import java.util.List;

@Service
public interface UserRatedMaterialsService {
    List<UserRatedMaterials> findMaterialsByUserId(String userId);
    User saveRatedMaterialByUserId(String productId, String userId,Integer materialRate);
}
