package se.agilecourse.services;

import se.agilecourse.model.Category;
import se.agilecourse.model.Company;
import se.agilecourse.model.Material;
import se.agilecourse.model.Product;
// import se.agilecourse.model.Brand;

import java.util.List;
import java.util.Optional;

public interface CategoryServices {

    Optional<Category> findByCategoryId(String Categoryid);
    List<Category> findAllCategories();
    Category saveCategory(Category category);
    // List<Product> getProductsByBrandId(String brandId);

    Product saveProductByCategory(Product product , String CategoryId);
    Product saveProduct(Product product);
    Optional<Product> getProductById(String Id);
    Optional<Product> getProductByProductNumber(String productNumber);
    List<Product> getProductsByCategory(String categoryName);
    List<Product> getAllProuducts();

    Product saveProductByCategoryandCompany(Product product , String CategoryId, String CompanyId);

    // Brand saveBrandByCategory(Brand brand,String categoryId);
    // public List<Brand> getBrandsByCategory(String categoryID);
    // Product saveProductByBrand(Product product , String BrandId);
    // List<Product> getProductsByBrand(String bandName);

    Material saveMaterial(Material material);
    Material saveMaterialByProduct(Material material , String ProductId);
    Optional<Material> getMaterialById(String id);
    List<Material> getMaterialByProductId(String prouductId);
    List<Material> getAllMaterials();



}
