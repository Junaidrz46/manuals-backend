package se.agilecourse.services;

import se.agilecourse.model.Category;
import se.agilecourse.model.Company;
import se.agilecourse.model.Material;
import se.agilecourse.model.Product;

import java.util.List;
import java.util.Optional;

public interface CategoryServices {

    Optional<Category> findById(String id);
    List<Category> findAllCategories();
    Category saveCategory(Category category);

    Material saveMaterialByProduct(Material material , String ProductId);
    Optional<Material> getMaterialById(String id);
    List<Material> getMaterialByProductId(String prouductId);
    List<Material> getAllMaterials();

    List<Product> getAllProuducts();// no need to change
    Optional<Product> getProductById(String Id);//no need to change

    Product saveProductByCategory(Product product , String CategoryId);//API maybe need to be deleted
    List<Product> getProductsByCategoryId(String categoryId);// no need to change


    //new API
    Product createProductByCategoryIdAndCompanyId
            (Product product, String categoryId, Company company);

    List<Product> getProductsByProductNo(String productNo);
    List<Product> getProductsByBrand(String brand);
    List<Product> getProductsByName(String productName);



}
