package se.agilecourse.repository.impl;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import se.agilecourse.model.Category;
import se.agilecourse.model.Company;
import se.agilecourse.model.Material;
import se.agilecourse.model.Product;
import se.agilecourse.repository.CategoryRepository;
import se.agilecourse.repository.CompanyRepository;
import se.agilecourse.repository.CustomizedRepository;
import se.agilecourse.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomizedRepositoryImpl implements CustomizedRepository {
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<Product> findProductsByCategoryid(String categoryid) {
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




}
