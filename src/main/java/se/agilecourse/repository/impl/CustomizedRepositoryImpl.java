package se.agilecourse.repository.impl;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import se.agilecourse.model.Category;
import se.agilecourse.model.Company;
import se.agilecourse.model.Material;
import se.agilecourse.model.Product;
import se.agilecourse.repository.CustomizedRepository;
// import se.agilecourse.model.Brand;

import java.util.ArrayList;
import java.util.List;

import com.jayway.jsonpath.Option;

public class CustomizedRepositoryImpl implements CustomizedRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<Product> findProductsByCategoryId(String categoryId) {
        Query query = new Query(Criteria.where("id").is(new ObjectId(categoryId)));
        Category category = mongoTemplate.findOne(query, Category.class, "categories");
        List<Product> list = category.getProducts();
        return list;    }

    @Override
    public List<Product> findProducsByCompanyId(String companyId) {
        Query query = new Query(Criteria.where("id").is(new ObjectId(companyId)));
        Company company = mongoTemplate.findOne(query, Company.class, "companies");
        List<Product> list = company.getProducts();
        return list;    }

    @Override
    public List<Material> findMaterialsByProductId(String productId) {
                 Query query = new Query(Criteria.where("id").is(new ObjectId(productId)));
                 Product product = mongoTemplate.findOne(query, Product.class, "products");
                 List<Material> list = product.getMaterials();
         return list;  }

    @Override
    public List<Product> saveProductByCompanyIdandCategoryId(String companyId, String categoryId){
        Query queryForompany = new Query(Criteria.where("id").is(new ObjectId(companyId)));
        Query queryForCategory = new Query(Criteria.where("id").is(new ObjectId(categoryId)));
        Company company = mongoTemplate.findOne(queryForompany, Company.class, "companies");
        Category category = mongoTemplate.findOne(queryForCategory, Category.class, "categories");
        List<Product> product = new ArrayList<Product>();
        List<Product> listByCompany = company.getProducts();
        List<Product> listByCategory = category.getProducts();
        for(Product item : listByCategory) {
            if(listByCompany.contains(item)){
            product.add(item);
            }
        }
        return product;
    }


	public List<Product> findProductsByCompany(String companyName) {
        
        //   Aggregation agg = Aggregation.newAggregation(Product.class,
        //   Aggregation.match(Criteria.where("company").is(companyName)),
        //   Aggregation.group().push("name").as("id").push("attendees.contact.email").as(
        //   "emails"));
          
        //   AggregationResults<Product> results =
        //   mongoTemplate.aggregate(agg,"products",Product.class); List<Product>
        //   mappedResult = results.getMappedResults(); 
          return null;
        }

    public List<Product> findProductsByBrand(String brandName) {
                /*
                 * Aggregation agg = Aggregation.newAggregation(Product.class,
                 * Aggregation.match(Criteria.where("brand").is(brandName)));
                 * 
                 * Aggregation agg = Aggregation.newAggregation(Product.class,
                 * Aggregation.match(Criteria.where("brand").is(brandName)),
                 * Aggregation.group().push("name").as("id").push("attendees.contact.email").as(
                 * "emails"));
                 * 
                 * AggregationResults<Product> results =
                 * mongoTemplate.aggregate(agg,"products",Product.class); List<Product>
                 * mappedResult = results.getMappedResults(); return mappedResult;
                 */
        return null;
    }

    // @Override
    // public List<Product> findProductsByBrandId(String brandId) {
    // Query query = new Query(Criteria.where("id").is(new ObjectId(brandId)));
    // Brand brand = mongoTemplate.findOne(query, Brand.class,"brands");
    // List<Product> list=brand.getProducts();
    // return list;
    // }

    // public List<Product> findProductsByCategoryId(String categoryId) {
    //     Query query = new Query(Criteria.where("id").is(new ObjectId(categoryId)));
    //     Category category = mongoTemplate.findOne(query, Category.class, "categories");
    //     List<Product> list = category.getProducts();
    //     return list;

    // }

    // public List<Product> findProductsByCompanyId(String companyId) {
    //     Query query = new Query(Criteria.where("id").is(new ObjectId(companyId)));
    //     Company company = mongoTemplate.findOne(query, Company.class, "companies");
    //     List<Product> list = company.getProducts();
    //     return list;
    // }

    /*
     * public List<String> findBrandByCategoryid(String categoryid){ Aggregation agg
     * = Aggregation.newAggregation(
     * Aggregation.match(Criteria.where("id").is(categoryid)),
     * Aggregation.group("products.brand") );
     * 
     * AggregationResults<String> results = mongoTemplate.aggregate(agg,
     * "categories", String.class); List<String> mappedResult =
     * results.getMappedResults(); return mappedResult; }
     */

}
