package se.agilecourse.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.agilecourse.exceptions.ConsumerNotFound;
import se.agilecourse.exceptions.MaterialNotFoundException;
import se.agilecourse.model.Material;
import se.agilecourse.model.User;
import se.agilecourse.model.UserRatedMaterials;
import se.agilecourse.repository.MaterialRepository;
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

    @Autowired
    MaterialRepository materialRepository;

    @Override
    public List<UserRatedMaterials> findMaterialsByUserId(String userId) {
        return userRatedMaterialsRepository.findByUserId(userId);
    }

    @Override
    public User saveRatedMaterialByUserId(String materialId, String userId,Integer materialRate) {
        Optional<User> user = userRepository.findById(userId);
        if(!user.isPresent())
            throw new ConsumerNotFound("There is no such consumer!");
        String userRole=user.get().getRole();
        if(!"customer".equals(userRole)){
            throw new ConsumerNotFound("There is no such consumer!");
        }
        Optional<Material> material= materialRepository.findById(materialId);
        if(!material.isPresent()){
            throw new MaterialNotFoundException("Material does not exist !");
        }

        //save the materialId list into the userRepository
        List<String> materialsList = user.get().getRatedMaterials();
        if(materialsList == null){
            materialsList = new ArrayList<>();
        }
        materialsList.add(materialId);
        user.get().setRatedMaterials(materialsList);
        userRepository.save(user.get());

        //save the materialId, userId materialRate into the userRatedMaterialsRepository
        userRatedMaterialsRepository.save(new UserRatedMaterials(userId,materialId,materialRate));
        return user.get();
    }
    public Integer getAverageRateByMaterialId(String materialId){
        int averageRate=0;


        return averageRate;
    }


}
