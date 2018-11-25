package se.agilecourse.repository.impl;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import se.agilecourse.model.Category;
import se.agilecourse.model.Product;
import se.agilecourse.repository.CustomizedRepository;

import java.util.List;

public class CustomizedRepositoryImpl implements CustomizedRepository {
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<Product> findProductsByCid(String cid) {
        Query query = new Query(Criteria.where("id").is(new ObjectId(cid)));
        Category category = mongoTemplate.findOne(query,Category.class,"categories");
        List<Product> list=category.getProducts();
        return list;
    }
}