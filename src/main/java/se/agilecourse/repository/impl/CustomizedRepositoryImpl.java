package se.agilecourse.repository.impl;


import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import se.agilecourse.model.*;
import se.agilecourse.repository.CustomizedRepository;
import se.agilecourse.repository.UserRepository;
import se.agilecourse.services.CategoryServices;
import se.agilecourse.services.UserLikedProudctsService;
import se.agilecourse.services.UserServices;
import se.agilecourse.services.impl.CategoryServicesImpl;
import se.agilecourse.services.impl.UserLikedProudctServiceImpl;
import se.agilecourse.services.impl.UserServicesImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;

public class CustomizedRepositoryImpl implements CustomizedRepository {
    private final Logger logger = LoggerFactory.getLogger(CustomizedRepositoryImpl.class);
    @Autowired
    MongoTemplate mongoTemplate;

    UserServices userServices;
    UserLikedProudctsService userLikedProudctsService;
    CategoryServices categoryServices;
    // UserRepository userRepository;

    @Override
    public List<Product> findProductsByCategoryId(String categoryid) {
        Query query = new Query(Criteria.where("id").is(new ObjectId(categoryid)));
        Category category = mongoTemplate.findOne(query,Category.class,"categories");
        List<Product> list=category.getProducts();
        return list;
    }

    public List<Material> findMaterialsByProductId(String productId){
        Query query = new Query(Criteria.where("id").is(new ObjectId(productId)));
        Product product = mongoTemplate.findOne(query,Product.class,"products");
        List<Material> list = product.getMaterials();
        return list;
    }
    public String getAverageRateForMaterial(String materialId) {
        GroupOperation getAverageRate = group("materialId")
                .avg("materiaRate").as("averageRate");

        Aggregation aggregation = Aggregation.newAggregation(getAverageRate);
        AggregationResults<AverageRatedMaterial> result = mongoTemplate.aggregate(
                aggregation, "userRateMaterials", AverageRatedMaterial.class);
        List<AverageRatedMaterial> list=result.getMappedResults();
        String averageRate =null;
        for(AverageRatedMaterial averageRatedMaterial:list){
            if(materialId.equals(averageRatedMaterial.getId())){
                averageRate=averageRatedMaterial.getAverageRate().toString();
                return averageRate;
            }
        }

        return "0";

    }


    // @Override
    // public List<String> getCounterWithProductsByBrand(String companyId) {
    //     Query query = new Query (Criteria.where("id").is(new ObjectId(companyId)));
    //     Company company = mongoTemplate.findOne(query, Company.class,"companies");
    //     // List userList = userRepository.findAll();
    //     List<User> userList =  userServices.findAllUsers();
    //     for (int i = 0; i < userList.size(); i++) {
    //         User user = userList.get(i);
    //         List<UserlikedProducts> productList = userLikedProudctsService.findProductsByUserId(user.getId());
    //         for(int j = 0; j < productList.size(); j++){
    //             UserlikedProducts userlikedproduct = productList.get(j);
    //             Optional<Product> product = categoryServices.getProductById(userlikedproduct.getProductId());
    //         if (product.get().getCompanyId().equals(companyId)){
    //             List<String> result = new ArrayList<String>();
    //             List<String> productsLiked = new ArrayList<String>();
    //             productsLiked.add(product.get().getId()); // All products' Id are liked of the specific company
    //             //get products by company and count how many times they are liked
    //             List<Product> productsOfCompany = categoryServices.getProductsByCompanyId(companyId);
    //         }
    //     }
    //     }
    //     return null;
    // }



}
