package se.agilecourse.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.agilecourse.model.Material;
import se.agilecourse.model.User;
import se.agilecourse.model.UserRatedMaterials;
import se.agilecourse.repository.UserRatedMaterialsRepository;
import se.agilecourse.repository.UserRepository;
import se.agilecourse.services.UserRatedMaterialsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserRatedMaterialsServiceImpl implements UserRatedMaterialsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRatedMaterialsRepository userRatedMaterialsRepository;

    @Override
    public List<String> findMaterialsByUserId(String userId) {
        return userRatedMaterialsRepository.findByUserId(userId);
    }

    @Override
    public User saveRatedMaterialByUserId(String materialId, String userId) {
        Optional<User> user = userRepository.findById(userId);
        List<String> materialsList = userRatedMaterialsRepository.findByUserId(userId);
        if(materialsList == null){
            materialsList = new ArrayList<>();
        }
        materialsList.add(materialId);
        user.get().setRatedMaterials(materialsList);
        userRatedMaterialsRepository.save(new UserRatedMaterials(userId,materialId));
        return user.get();
    }


}
