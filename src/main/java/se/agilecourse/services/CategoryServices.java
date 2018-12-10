package se.agilecourse.services;

import se.agilecourse.exceptions.CompanyIdMismatchException;
import se.agilecourse.model.*;

import java.util.List;
import java.util.Optional;

public interface CategoryServices {

    Optional<Category> findById(String id);
    List<Category> findAllCategories();
    Category saveCategory(Category category);
    Company saveCompany(Company company);
    Product saveProductByCategoryAndCompany(String categoryId,String companyId,Product product) throws CompanyIdMismatchException;


    List<ProductMini> getProductsByCategoryId(String categoryId);// no need to change
    Optional<Product> getProductById(String Id);

    List<Product> getAllProuducts();

    Material saveMaterial(Material material);
    Material saveMaterialByProduct(Material material , String ProductId);
    Optional<Material> getMaterialById(String id);
    List<Material> getMaterialByProductId(String prouductId);
    List<Material> getAllMaterials();
    List<Product> getProductsByProductNo(String productNo);
    List<Product> getProductsByCompanyId(String companyId);
    List<Product> getProductsByName(String productName);
    List<Product> getProductsByThree(String condition);

    Optional<Company> getCompanyById(String CompanyId);
    Optional<Category> getCategoryById(String categoryId);

}
