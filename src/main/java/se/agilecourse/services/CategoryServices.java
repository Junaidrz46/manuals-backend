package se.agilecourse.services;

import se.agilecourse.exceptions.CompanyIdMismatchException;
import se.agilecourse.model.Company;
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
    Company saveCompany(Company company);
    Product saveProductByCompany(String categoryId,String companyId,Product product) throws CompanyIdMismatchException;

    Product saveProductByCategory(Product product , String CategoryId);

    List<Product> getProductsByCategoryId(String categoryId);// no need to change
    Optional<Product> getProductById(String Id);

    List<Product> getAllProuducts();

    Material saveMaterial(Material material);
    Material saveMaterialByProduct(Material material , String ProductId);
    Optional<Material> getMaterialById(String id);
    List<Material> getMaterialByProductId(String prouductId);
    List<Material> getAllMaterials();
    List<Product> getProductsByProductNo(String productNo);
    List<Product> getProductsByBrand(String brand);
    List<Product> getProductsByName(String productName);


}
