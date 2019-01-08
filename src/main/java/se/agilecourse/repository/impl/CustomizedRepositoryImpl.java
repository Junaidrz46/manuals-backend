package se.agilecourse.repository.impl;


import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import se.agilecourse.model.*;
import se.agilecourse.repository.CustomizedRepository;
import se.agilecourse.services.impl.CategoryServicesImpl;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;

public class CustomizedRepositoryImpl implements CustomizedRepository {
    private final Logger logger = LoggerFactory.getLogger(CustomizedRepositoryImpl.class);
    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    MongoOperations mongoOperations;

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
    @Override
    public List<Product> getMostRecentlyAddedProducts(){
        Query query=new Query();
        query.limit(10);
        query.with(new Sort(Sort.Direction.DESC, "createDate"));
        return  mongoOperations.find(query, Product.class);

    }





}
