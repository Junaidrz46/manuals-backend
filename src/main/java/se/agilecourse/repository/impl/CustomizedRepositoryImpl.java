package se.agilecourse.repository.impl;


import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import se.agilecourse.model.*;
import se.agilecourse.repository.CustomizedRepository;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;

public class CustomizedRepositoryImpl implements CustomizedRepository {

    @Autowired
    MongoTemplate mongoTemplate;

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
        GroupOperation getAverageRate = group(materialId)
                .avg("materiaRate").as("averageRate");
        Aggregation aggregation = Aggregation.newAggregation(getAverageRate);
        AggregationResults<AverageRatedMaterial> result = mongoTemplate.aggregate(
                aggregation, "userRateMaterials", AverageRatedMaterial.class);
        return result.getMappedResults().get(0).getAverageRate().toString();

    }




}
