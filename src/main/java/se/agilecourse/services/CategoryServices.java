package se.agilecourse.services;

import se.agilecourse.exceptions.CompanyIdMismatchException;
import se.agilecourse.model.Company;
import se.agilecourse.model.Category;
import se.agilecourse.model.Material;
import se.agilecourse.model.Product;

import java.util.List;
import java.util.Optional;

public interface CategoryServices {

    Optional<Category> findById(String id);
    List<Category> findAllCategories();
    Category saveCategory(Category category);
    Company saveCompany(Company company);
    Product saveProductByCategoryAndCompany(String categoryId,String companyId,Product product) throws CompanyIdMismatchException;
    List<Product> getProductsByCategoryid(String cid);
    Product saveProductByCategory(Product product , String CategoryId);

    Product saveProduct(Product product);
    Optional<Product> getProductById(String Id);

    List<Product> getAllProuducts();

    Material saveMaterial(Material material);
    Material saveMaterialByProduct(Material material , String ProductId);
    Optional<Material> getMaterialById(String id);
    List<Material> getMaterialByProductId(String prouductId);
    List<Material> getAllMaterials();
    List<Product> getProductsByCompanyId(String CompanyId);

    // Company saveBrandByCategory(Company brand, String categoryId);
    // public List<Company> getBrandsByCategory(String categoryID);
    // Product saveProductByCompany(Product product , String BrandId);
    // List<Product> getProductsByCompany(String bandName);
    // List<Product> getProductsByCategory(String categoryName);
    // List<Product> getProductsByCompanyId(String CompanyId);


    //new API
    public Product createProductByCategoryIdAndCompanyId(Product product, String categoryId, Company company);
}
